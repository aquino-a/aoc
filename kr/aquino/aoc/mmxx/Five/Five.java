package kr.aquino.aoc.mmxx.Five;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;

import kr.aquino.aoc.mmxx.IOUtility;
import kr.aquino.aoc.mmxx.Arguments;

public class Five {
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/mmxx/Five/input.txt");
        Function<Integer[], Integer> func = arguments.SetText(1, "1").equals("1") ?
                    (ids) -> { 
                        return Arrays.stream(ids)
                                .reduce((first, second) -> second)
                                .get(); 
                        }
                            :
                    (ids) -> {
                        var count = ids[0];
                        for (Integer id : ids) {
                            if(!id.equals(count))
                                return count;
                            count++;
                        }
                        return -1;
                    };


        var seatId = func.apply(IOUtility.ReadFile(path).stream()
                .map(s -> new BoardingPass(128, 8, s))
                .map(bp -> bp.getSeatId()).sorted().toArray(Integer[]::new));
                    

        System.out.println(String.format("SeatId: %d", seatId));
        
        

    }
}
