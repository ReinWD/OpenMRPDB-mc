package cat.nyaa.mprdb.core;

import cat.nyaa.mprdb.api.MPluginInterface;
import cat.nyaa.mprdb.api.MResponse;
import cat.nyaa.mprdb.data.MServer;

import java.util.List;
import java.util.UUID;

public class Action implements MPluginInterface {

    @Override
    public MResponse<UUID> register(String serverName) {
        return null;
    }

    @Override
    public MResponse<UUID> newSubmit(UUID uuid, UUID playerUuid, double points, String comment, long timestamp) {
        return null;
    }

    @Override
    public MResponse<UUID> deleteServer(UUID serverUuid) {
        return null;
    }

    @Override
    public MResponse<UUID> deleteSubmit(UUID uuidToDel) {
        return null;
    }

    @Override
    public List<MServer> getAllServers(int offset, int limit) {
        return null;
    }
}
