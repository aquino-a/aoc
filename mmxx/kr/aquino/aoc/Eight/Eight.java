package kr.aquino.aoc.Eight;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class Eight {
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/Eight/input.txt");
        Function<Instruction[], Integer> func = arguments.SetText(1, "1").equals("1") ? Eight::accumulateWrap : Eight::accumulateTerminateWrap;

        var intrs = IOUtility.ReadFile(path).stream().map(s -> new Instruction(s)).toArray(Instruction[]::new);
        var accumulatorValue = func.apply(intrs);

        System.out.println(String.format("Count: %d", accumulatorValue));
    }

    private static int accumulateWrap(Instruction[] intrs){
        try {
            return accumulate(intrs);
        } catch (InfiniteLoopException e) {
            return e.accumulator;
        }
    }

    private static int accumulateTerminateWrap(Instruction[] intrs){
        var stack = new Stack<Integer>();
        for (int i = 0; i < intrs.length; i++) {
            if(intrs[i].getType().equals(InstructionType.jmp) || intrs[i].getType().equals(InstructionType.nop))
                stack.push(i);
        }
        var lastChanged = -1;
        while(true){
            try {
                return accumulate(intrs);
            } catch (InfiniteLoopException e) {
                if(lastChanged != -1)
                    intrs[lastChanged].flip();
                lastChanged = stack.pop();
                intrs[lastChanged].flip();
            }
        }
    }

    private static int accumulate(Instruction[] intrs) {
        var history = new HashSet<Integer>();
        var accumulator = 0;

        for (int position = 0; position < intrs.length;) {
            if(history.contains(position))
                throw new InfiniteLoopException(accumulator);
            else history.add(position);

            switch (intrs[position].getType()) {
                case acc: 
                    accumulator += intrs[position].num;
                    position++;
                    continue;
                case jmp:
                    position += intrs[position].num;
                    continue;
                case nop:
                    position++;
                    continue;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return accumulator;
    }
}
