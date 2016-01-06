package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Spider
 */
public class Request{

    private Map<String, String> data;
    private HashMap<String,Input> dataInput;

    public Request(Map<String, String> data) {
        this.data = new HashMap<String, String>();
        this.data = data;
    }
    
    public Request()
    {
        HashMap<String,String> hashMap = new HashMap<>();
        this.data = hashMap;
    }

    public String getData(String key)
    {
        return this.data.get(key);
    }
    
    public void setData(String key,String value)
    {
        this.data.put(key, value);
    }
    
    public HashMap<String, String> getHashMapData()
    {
        Set<Entry<String,String>> dataSet = this.data.entrySet();
        
        HashMap<String,String> returnSet = new HashMap<>();
        
        for (Entry<String, String> entry : dataSet)
        {
            returnSet.put(entry.getKey(), entry.getValue());
        }
        
        if (returnSet.isEmpty())
            return null;
        else
            return returnSet;
    }
    
    public HashMap<String, Input> getAllHashMapDataInputs()
    {
        Set<Entry<String,Input>> dataSet = this.dataInput.entrySet();
        
        HashMap<String,Input> returnSet = new HashMap<>();
        
        for (Entry<String, Input> entry : dataSet)
        {
            returnSet.put(entry.getKey(), entry.getValue());
        }
        
        if (returnSet.isEmpty())
            return null;
        else
            return returnSet;
    }
    
    public void setHashMapValueToInput()
    {
        this.data = null;
        
        this.dataInput = new HashMap<>();
    }
    
    public void setDataInput(String key, Input value)
    {
        if (!key.isEmpty())
            this.dataInput.put(key, value);        
    }
    
    public Input getDataInput(String key)
    {
        if(!key.isEmpty())
            return this.dataInput.get(key);
        
        return null;
            
    }
}