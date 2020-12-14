package kr.aquino.aoc.Twelve;

import java.io.IOException;
import java.util.stream.Collectors;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class Twelve {
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/Twelve/test/test.txt");
        var ship = arguments.SetText(1, "1").equals("1") ? new Ship() : new WaypointShip();

        var instrs = IOUtility.ReadFile(path).stream().map(Instruction::new).collect(Collectors.toList());
        instrs.forEach(i -> ship.action(i));

        var answer = ship.getManhattanDistance();
        System.out.println(String.format("Answer: %d", answer));
    }

}
