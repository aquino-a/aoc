package kr.aquino.aoc.mmxx.Eight;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;

import kr.aquino.aoc.mmxx.Arguments;
import kr.aquino.aoc.mmxx.IOUtility;

public class Eight {
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/mmxx/Eight/input.txt");
        Function<Instruction[], Integer> func = arguments.SetText(1, "1").equals("1") ? Eight::accumulateWrap : Eight::accumulateTerminateWrap;

        var intrs = IOUtility.ReadFile(path).stream().map(s -> new Instruction(s)).toArray(Instruction[]::new);
        var accumulatorValue = func.apply(intrs);

        System.out.println(String.format("Count: %d", accumulatorValue));
    }

    private static int accumulateWrap(Instruction[] intrs){
        return accumulate(new HashSet<Integer>(), intrs, 0, 0);
    }

    private static int accumulate(Set<Integer> history, Instruction[] intrs, int position, int accumulator) {
        if(history.contains(position))
            return accumulator;
        else history.add(position);

        switch (intrs[position].getType()) {
            case acc: accumulator += intrs[position].num;
                return accumulate(history, intrs, position + 1, accumulator);
            case jmp:
                return accumulate(history, intrs, position + intrs[position].num, accumulator);
            case nop:
                return accumulate(history, intrs, position + 1, accumulator);
            default:
                throw new IllegalArgumentException();
        }
    }

    private static int accumulateTerminateWrap(Instruction[] intrs){
        var history = new HashSet<Integer>();
        var stack = new Stack<Integer>();
        var lastChanged = -1;

        try {
            return accumulateTerminate(history, stack, lastChanged, intrs, 0, 0, true);
        } catch (IllegalArgumentException e) {
            while(true){
                try {
                    return accumulateTerminate(history, stack, lastChanged, intrs, 0, 0, false);
                } catch (IllegalArgumentException ex) {
                    if(lastChanged != -1)
                        intrs[lastChanged].flip();
                    lastChanged = stack.pop();
                    intrs[lastChanged].flip();
                    history.clear();
                }
            }
        }
    }

    private static int accumulateTerminate(Set<Integer> history, Stack<Integer> order, int lastChanged, Instruction[] intrs, int position, int accumulator, boolean stack) {
        if(history.contains(position)){
           throw new IllegalArgumentException("infinite loop");
        }
        else if(position >= intrs.length)
            return accumulator;
        else {
            history.add(position);
            if(stack && (intrs[position].getType().equals(InstructionType.jmp) || intrs[position].getType().equals(InstructionType.nop)))
                order.push(position);
        }

        switch (intrs[position].getType()) {
            case acc: accumulator += intrs[position].num;
                return accumulateTerminate(history, order, lastChanged, intrs, position + 1, accumulator, stack);
            case jmp:
                return accumulateTerminate(history, order, lastChanged, intrs, position + intrs[position].num, accumulator, stack);
            case nop:
                return accumulateTerminate(history, order, lastChanged, intrs, position + 1, accumulator, stack);
            default:
                throw new IllegalArgumentException();
        }
    }
}
