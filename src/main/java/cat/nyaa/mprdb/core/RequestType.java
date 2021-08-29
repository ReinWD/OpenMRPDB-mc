package cat.nyaa.mprdb.core;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RequestType {
    String[] value();
    Class<?> cls = Object.class;
}
