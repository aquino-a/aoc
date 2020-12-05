package kr.aquino.aoc.Two;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class PasswordPolicy {

    private final static Pattern PASSWORD_POLICY_PATTERN = Pattern.compile("^(\\d{1,3})-(\\d{1,3}) ([a-z]): ([a-z]+)$");

    private final int min;
    private final int max;

    private final char searchChar;
    private final String text;


    public PasswordPolicy(String input){
        var m = PASSWORD_POLICY_PATTERN.matcher(input);
        m.find();
        min = Integer.parseInt(m.group(1));
        max = Integer.parseInt(m.group(2));
        searchChar = m.group(3).charAt(0);
        text = m.group(4);
    }

    public boolean IsValidOne() {
        var chars = text.toCharArray();
        var count = IntStream.range(0, text.length())
                    .map(i -> chars[i])
                    .filter(c -> c == searchChar)
                    .count();
        return count >= min && count <= max;
    }

    public boolean IsValidTwo(){
        var firstChar = min - 1 < text.length() ? text.charAt(min - 1) : ' ';
        var secondChar = max - 1 < text.length() ? text.charAt(max - 1) : ' ';
        return firstChar == searchChar ^ secondChar == searchChar;
    }




    
}
