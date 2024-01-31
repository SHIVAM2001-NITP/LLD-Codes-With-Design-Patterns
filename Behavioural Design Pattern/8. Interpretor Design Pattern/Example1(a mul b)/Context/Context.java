package Context;

import java.util.HashMap;
import java.util.Map;

public class Context {
    Map<String,Integer> contextMap=new HashMap<>();
    public void put(String strVariable,int intVal){
        contextMap.put(strVariable,intVal);
    }

    public int get(String strVariable){
        return contextMap.get(strVariable);
    }

}
