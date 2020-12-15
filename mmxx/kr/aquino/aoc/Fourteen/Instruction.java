package kr.aquino.aoc.Fourteen;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Instruction {

    public final Type type;
    public final Bitmask mask;
    public final long address;
    public final long value;

    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^((?<mask>mask)|(?<mem>(mem)\\[(?<memNum>\\d++)\\])) = (?<content>\\w+)$");

    public Instruction(String text){
        var m = INSTRUCTION_PATTERN.matcher(text);
        m.find();
        var mask = m.group("mask");
        if(mask != null){
            this.type = Type.mask;
            this.address = -1;
            this.value = -1;
            this.mask = createMask(m.group("content"));
        }
        else {
            this.type = Type.mem;
            this.address = Integer.parseInt(m.group("memNum"));
            this.value = Integer.parseInt(m.group("content"));
            this.mask = null;
        }

    }

    private Bitmask createMask(String maskString) {
        var on = 0l;
        var off = 0l;
        var floaterIndexes = new ArrayList<Integer>();
        var cs = maskString.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            on <<= 1;
            off <<= 1;
            
            switch (cs[i]) {
                case '1':
                    on |= 1;
                    off |= 1;
                    break;
                case '0':
                    off |= 0;
                    break;
                case 'X':
                    floaterIndexes.add(cs.length - 1 - i);
                    off |= 1;
                default:
                    break;
            }
        }
        return new Bitmask(on, off, floaterIndexes);
    }

    public enum Type {
        mask, mem
    }
    
}
