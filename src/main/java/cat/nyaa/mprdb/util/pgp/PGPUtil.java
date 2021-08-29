package cat.nyaa.mprdb.util.pgp;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.util.io.Streams;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.DocumentSignatureType;
import org.pgpainless.algorithm.HashAlgorithm;
import org.pgpainless.decryption_verification.ConsumerOptions;
import org.pgpainless.decryption_verification.DecryptionStream;
import org.pgpainless.encryption_signing.ProducerOptions;
import org.pgpainless.encryption_signing.SigningOptions;
import org.pgpainless.key.protection.SecretKeyRingProtector;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PGPUtil {
    public static String signMsg(String msg, PGPSecretKeyRing secretKey, SecretKeyRingProtector protector){
        final InputStream is = new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8));
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Streams.pipeAll(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)),
                    PGPainless.encryptAndOrSign()
                        .onOutputStream(outputStream)
                        .withOptions(
                                ProducerOptions.sign(
                                        new SigningOptions().overrideHashAlgorithm(HashAlgorithm.SHA1)
                                        .addInlineSignature(protector, secretKey, DocumentSignatureType.CANONICAL_TEXT_DOCUMENT)

                                ).setAsciiArmor(true)
                        )
            );
        } catch (IOException | PGPException e) {
            throw new RuntimeException("error encrypting message", e);
        }
        return outputStream.toString();
    }

    public static boolean verifyMsg(String msg, String pubKey){
        PGPPublicKeyRing pgpPublicKeys;
        try {
            pgpPublicKeys = PGPainless.readKeyRing()
                    .publicKeyRing(pubKey);
        } catch (IOException e) {
            throw new RuntimeException("error parsing pubkey: " + pubKey, e);
        }

        try {
            return PGPainless.decryptAndOrVerify()
                    .onInputStream(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)))
                    .withOptions(new ConsumerOptions()
                            .addVerificationCert(pgpPublicKeys)
                    ).getResult().isVerified();
        } catch (PGPException | IOException e) {
            throw new RuntimeException("error validating message: " + "msg" +" with pubkey: " + pubKey, e);
        }
    }

    /**
     * unwrap PGP SIGNED MESSAGE, only message itself
     * @param msg PGP signed message
     * @return message itself
     */
    public static String unwrapMsg(String msg){
        try {
            final DecryptionStream decryptionStream = PGPainless.decryptAndOrVerify()
                    .onInputStream(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)))
                    .withOptions(
                            new ConsumerOptions()
                    );
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Streams.pipeAll(decryptionStream, byteArrayOutputStream);
            return byteArrayOutputStream.toString();
        } catch (PGPException | IOException e) {
            throw new RuntimeException("error reading message: " + msg, e);
        }
    }
}
