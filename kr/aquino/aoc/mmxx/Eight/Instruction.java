package kr.aquino.aoc.mmxx.Eight;

import java.util.regex.Pattern;

public class Instruction {

    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(acc|nop|jmp) ([+-])(\\d+)");

    private InstructionType type;
    public final int num;

    public Instruction(String text){
        var m = INSTRUCTION_PATTERN.matcher(text);
        m.find();
        this.type = Enum.valueOf(InstructionType.class, m.group(1));
        var bareNum = Integer.parseInt(m.group(3));
        if(m.group(2).equals("-"))
            num = bareNum * -1;
        else num = bareNum;
    }

    public InstructionType getType(){
        return type;
    }

	public void flip() {
        if(type.equals(InstructionType.jmp))
            type = InstructionType.nop;
        else if(type.equals(InstructionType.nop))
            type = InstructionType.jmp;
	}

}
