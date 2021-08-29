package cat.nyaa.mprdb.api;

import cat.nyaa.mprdb.data.MResponseStatus;

public interface MResponse<T> {
    MResponseStatus getStatus();
    T getResponse();

    //exists when error occur or status NG
    boolean isErr();
    String getErrMsg();
}
