package cat.nyaa.mprdb.core.resolver;

import com.google.gson.JsonObject;

public interface ResponseResolver<T> {
     T resolve(JsonObject msg);
}
