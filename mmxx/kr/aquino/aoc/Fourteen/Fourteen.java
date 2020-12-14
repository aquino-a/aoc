package kr.aquino.aoc.Fourteen;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class Fourteen {
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/Fourteen/input.txt");
        var docking = new Docking();
        Consumer<Instruction> consumer = arguments.SetText(1, "1").equals("1") ? docking::action : docking::actionPartTwo;

        var list = IOUtility.ReadFile(path).stream().map(Instruction::new).collect(Collectors.toList());
        list.forEach(consumer);
        var answer = docking.sum();

        System.out.println(String.format("Answer: %d", answer));
    }
}
