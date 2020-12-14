package kr.aquino.aoc.Fourteen;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Instruction {

    public final Type type;
    public final Bitmask mask;
    public final int address;
    public final int value;

//     mask = 1001X0X00110011X01X1000110100011000X
// mem[5228] = 409649
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
//     mask = 1001X0X00110011X01X1000110100011000X

    private Bitmask createMask(String maskString) {
        var on = 0l;
        var off = 0l;
        var floater = 0l;
        var cs = maskString.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            on <<= 1;
            off <<= 1;
            
            switch (cs[i]) {
                case '1':
                    on |= 1;
                    off |= 1;
                    floater |= 1;
                    break;
                case '0':
                    off |= 0;
                    break;
                case 'X':
                    floater |= 0;
                    off |= 1;
                default:
                    
                    break;
            }
        }
        return new Bitmask(on, off);
    }

    public enum Type {
        mask, mem
    }
    
}
