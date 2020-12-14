package kr.aquino.aoc.Fourteen;

import java.util.stream.LongStream;

public class Docking {

    private final long[] memory = new long[100_000];
    private Bitmask bitmask;

    public void action(Instruction instruction){
        switch (instruction.type) {
            case mask:
                bitmask = instruction.mask;
                break;
            case mem:
                memory[instruction.address] = mask(instruction.value);
            default:
                break;
        }
    }

    public void actionPartTwo(Instruction instruction){
        switch (instruction.type) {
            case mask:
                bitmask = instruction.mask;
                break;
            case mem:
                memory[instruction.address] = mask(instruction.value);
            default:
                break;
        }
    }

    private long mask(long value) {
        value |= bitmask.on;
        value &= bitmask.off;
        return value;
    }

    public long sum() {
        return LongStream.of(memory).sum();
    }
}
