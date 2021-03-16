package kr.aquino.aoc.iv;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FieldValidator {

    private final String fieldName;
    private final Predicate<String> validPattern;

    public FieldValidator(String fieldName, String regex){
        this.fieldName = fieldName;
        if(regex == null || regex.isBlank())
            validPattern = null;
        else validPattern = Pattern.compile(regex).asPredicate();
    }

    public boolean isValid(String field, String text){
        var valid = true;
        valid &= field.equals(fieldName);
        if(validPattern != null)
            valid &= validPattern.test(text);
        return valid;
    }

    public String getFieldName(){ return fieldName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldValidator that = (FieldValidator) o;
        return fieldName.equals(that.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName);
    }
}
