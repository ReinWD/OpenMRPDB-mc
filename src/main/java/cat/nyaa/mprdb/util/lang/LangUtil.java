package cat.nyaa.mprdb.util.lang;

import java.util.HashMap;
import java.util.Map;

public class LangUtil {
    //TODO: configurable language support
    //TODO: replace hard coded msg
    private static Map<String, String> keyMap = new HashMap<>();

    static {
//        put("", "");
        put("response.", "");
//        put("", "");
    }

    private static void put(String k, String v){
        keyMap.put(k,v);
    }

    public static String format(String str, Object... args){
        final String s = keyMap.get(str);
        return s == null ? "MISSING_LANG<"+str+">" : String.format(s, args);
    }
}
