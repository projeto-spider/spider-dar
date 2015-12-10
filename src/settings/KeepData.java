package settings;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bleno Vale
 */
public class KeepData {

    private static final Map<String, String> data = new HashMap<>();

    public static void setData(String key, String value) {
        data.put(key, value);
    }

    public static String getData(String key) {
        return data.get(key);
    }
}
