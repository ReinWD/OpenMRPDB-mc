package cat.nyaa.mprdb.core.resolver;

import com.google.gson.JsonObject;

import java.util.UUID;

public class ResolverUuid implements ResponseResolver<UUID>{
    @Override
    public UUID resolve(JsonObject msg) {
        return UUID.fromString(msg.get("uuid").getAsString());
    }
}
