package cat.nyaa.mprdb.core;

import cat.nyaa.mprdb.api.MResponse;
import cat.nyaa.mprdb.core.resolver.ResponseResolver;
import cat.nyaa.mprdb.data.MResponseStatus;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MResponseImpl<T> implements MResponse<T> {
    private MResponseStatus status;
    private T responseMsg;
    private String errReason;
    private boolean error = false;
    private static final Gson gson = new Gson();

    private static final Map<Class<?>, ResponseResolver<?>> resolverMap = new HashMap<>();

    static {

    }

    private MResponseImpl(String json, Class<T> type) {
        final JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        status = MResponseStatus.valueOf(jsonObject.get("status").getAsString());
        if (!Objects.equals(status, MResponseStatus.OK)){
            error = true;
            errReason = jsonObject.get("reason").getAsString();
            return;
        }
        responseMsg = ((ResponseResolver<T>)resolverMap.get(type)).resolve(jsonObject);
    }

    public static<E> MResponseImpl<E> ofJson(String json, Class<E> type){
        MResponseImpl<E> objectMResponse = new MResponseImpl<>(json, type);
        return objectMResponse;
     }

    @Override
    public MResponseStatus getStatus() {
        return null;
    }

    @Override
    public T getResponse() {
        if (error){
            throw new IllegalStateException("invalid response: " +getErrMsg());
        }
        return responseMsg;
    }

    @Override
    public boolean isErr() {
        return error;
    }

    @Override
    public String getErrMsg() {
        return errReason;
    }

}
