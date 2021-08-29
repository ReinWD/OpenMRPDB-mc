package cat.nyaa.mprdb.core.resolver;

import cat.nyaa.mprdb.data.MServer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ResolverServers implements ResponseResolver<List<MServer>>{
    private static final Gson gson = new Gson();
    @Override
    public List<MServer> resolve(JsonObject msg) {
        List<MServer> mServers = new ArrayList<>();
        final JsonArray servers = msg.get("servers").getAsJsonArray();
        servers.forEach(server -> mServers.add(gson.fromJson(server.getAsString(), MServer.class)));
        return mServers;
    }
}
