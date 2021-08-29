package cat.nyaa.mprdb.core.resolver;

import cat.nyaa.mprdb.data.MSubmitDetail;
import cat.nyaa.mprdb.util.pgp.PGPUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ResolverSubmitDetail implements ResponseResolver<MSubmitDetail>{
    private static final Gson gson = new Gson();
    @Override
    public MSubmitDetail resolve(JsonObject msg) {
        return gson.fromJson(PGPUtil.unwrapMsg(msg.get("content").getAsString()), MSubmitDetail.class);
    }
}
