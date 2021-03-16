package kr.aquino.aoc.xv;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class xv {
    
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/xv/input.txt");
        var target = Integer.parseInt(arguments.SetText(1, "30000000"));

        var list = Arrays.stream(IOUtility.ReadFile(path).get(0).split(","))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .collect(Collectors.toCollection(LinkedList<Integer>::new));
        var answer = findNth(target, list);

        System.out.println(String.format("Answer: %d", answer));
    }

    private static int findNth(int i, LinkedList<Integer> list) {
        var seen = IntStream.range(0, list.size())
                            .boxed()
                            .collect(Collectors.toMap(in -> list.get(in), in -> in));;
        list.add(0);
        var limit = i - list.size();
        for (int j = 0, previousLast = 0, last = 0; j < limit; j++) {
            previousLast = last;
            last = play(list, seen);
            seen.put(previousLast, list.size() - 2);
        }
        return list.getLast();
    }

    private static int play(LinkedList<Integer> list, Map<Integer, Integer> seen) {
        if(seen.containsKey(list.getLast())){
            var lastIndex = seen.get(list.getLast());
            var count = (list.size() - 1) - lastIndex;
            list.addLast(count);
        }
        else {
            list.addLast(0);
        }
        return list.getLast();
    }
}
