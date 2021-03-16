package kr.aquino.aoc.iv;

import java.util.HashMap;
import java.util.Map;

public class Passport {
    
    private final Map<String, String> fields;

    public Passport(){
        fields = new HashMap<>();
    }

    public void addFields(String text){
        var data = text.split("[ :]");
        for (int i = 1; i < data.length; i += 2) {
            fields.put(data[i -1], data[i]);
        } 
    }

    public boolean hasField(String field){
        return fields.containsKey(field);
    }

    public String getField(String field){
        return fields.get(field);
    }
    

}
