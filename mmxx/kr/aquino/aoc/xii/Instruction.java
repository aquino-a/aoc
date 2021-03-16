package kr.aquino.aoc.xii;

import java.util.regex.Pattern;

public class Instruction {
    
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^(N|S|E|W|L|R|F)(\\d+)$");

    private final Type type;
    private final int value;

    public Instruction(String text){
        var m = INSTRUCTION_PATTERN.matcher(text);
        m.find();
        type = Enum.valueOf(Type.class, m.group(1));
        value = Integer.parseInt(m.group(2));
    }

    public Instruction(Type type, int value){
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public enum Type {
        N,S,E,W,L,R,F
    }
}
