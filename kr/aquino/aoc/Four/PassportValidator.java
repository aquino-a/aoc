package kr.aquino.aoc.Four;

import java.util.Map;

public class PassportValidator {

    private final Map<String, FieldValidator> requiredFields;

    public PassportValidator(Map<String, FieldValidator> requiredFields){
        this.requiredFields = requiredFields;
    }

    public boolean isValid(Passport p){
        for (String f : requiredFields.keySet()) {
            if(!(p.hasField(f) && requiredFields.get(f).isValid(f, p.getField(f))))
                return false;
        }
        return true;
    }
    
}
