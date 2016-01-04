package util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Spider
 */
public class Request {

    public Map<String, String> data;

    public Request(Map<String, String> data) {
        this.data = new HashMap<>();
        this.data = data;
    }

    public String getData(String key) {
        return this.data.get(key);
    }
}
