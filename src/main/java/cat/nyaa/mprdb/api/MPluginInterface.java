package cat.nyaa.mprdb.api;

import cat.nyaa.mprdb.data.MServer;

import java.util.List;
import java.util.UUID;

public interface MPluginInterface {
    MResponse<UUID> register(String serverName);
    MResponse<UUID> newSubmit(UUID uuid, UUID playerUuid, double points, String comment, long timestamp);
    MResponse<UUID> deleteServer(UUID serverUuid);
    MResponse<UUID> deleteSubmit(UUID uuidToDel);
    List<MServer> getAllServers(int offset, int limit);

}
