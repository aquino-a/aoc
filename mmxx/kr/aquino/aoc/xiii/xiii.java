package kr.aquino.aoc.xiii;

import java.io.IOException;
import java.util.function.Function;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class xiii {
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/xiii/input.txt");
        Function<Note, Long> func = arguments.SetText(1, "1").equals("1") ? Note::partOne : Note::partTwo;

        var list = IOUtility.ReadFile(path);
        var note = new Note(list.get(0), list.get(1));
        var answer = func.apply(note);
        
        System.out.println(String.format("Answer: %d", answer));
    }
}
