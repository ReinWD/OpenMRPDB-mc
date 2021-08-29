package cat.nyaa.mprdb.api;

import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.pgpainless.key.generation.KeySpec;

import java.io.File;
import java.io.IOException;

public interface MPGPInterface {
    /**
     * generate a PGPSecretKeyRing with default KeySpec, and write it into a specific file.
     * @see {@link #generatePGPKeyring(File, KeySpec, String, String, String)}
     * @param file file to store the PGPSecretKeyring. will throw IOException if it's parent directory not exist
     * @param userId user ID
     * @param email email
     * @param passPhrase passphrase
     * @return generated PGPSecretKeyRing object. If returned, it must be successfully wrote to the file.
     */
    PGPSecretKeyRing generatePGPKeyring(File file, String userId, String email, String passPhrase) throws IOException;

    /**
     * generate a PGPSecretKeyRing, and write it into a specific file.
     * this method allow customizing KeySpec(e.g. algorithm, length, etc.).
     * @see {@link org.pgpainless.key.generation.KeySpec}
     * @see {@link #generatePGPKeyring(File, String, String, String)}
     * @param file file to store the PGPSecretKeyring. will throw IOException if it's parent directory not exist
     * @param keySpec PGPainless' KeySpec object. using in keyring generating.
     * @param userId user ID
     * @param email email
     * @param passPhrase passphrase
     * @return generated PGPSecretKeyRing object. If returned, it must be successfully wrote to the file.
     */
    PGPSecretKeyRing generatePGPKeyring(File file, KeySpec keySpec, String userId, String email, String passPhrase) throws IOException;


}
