package kr.aquino.aoc.Ten;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class Ten {
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 4);
        var path = arguments.SetText(0, "kr/aquino/aoc/Ten/input.txt");
        Function<List<Long>, Long> func = arguments.SetText(1, "1").equals("1") ? Ten::findDifferencesMultiplied : Ten::findTotalWays;
        
        var nums = IOUtility.ReadFile(path).stream().mapToLong(Long::parseLong).sorted().boxed().collect(Collectors.toList());
        nums.add(0, 0l);
        nums.add(nums.get(nums.size() - 1) + 3);
        var answer = func.apply(nums);
        
        System.out.println(String.format("Answer: %d", answer));
    }

    private static long findDifferencesMultiplied(List<Long> nums) {
        var diffs = new HashMap<Integer, Integer>();
        diffs.put(1, 0);
        diffs.put(3, 0);
        var previous = 0l;
        for (int i = 0; i < nums.size(); i++) {
            var diff = nums.get(i) - previous;
            if(diff == 1){
                diffs.replace(1, diffs.get(1) + 1);
            } else if(diff == 3){
                diffs.replace(3, diffs.get(3) + 1);
            }
            previous = nums.get(i);
        }
        return diffs.get(1) * diffs.get(3);
    }

    private static long findTotalWays(List<Long> nums){
        var possibilities = new long[nums.size()];
        possibilities[0] = 1;
        for (int i = 1; i < possibilities.length; i++) {
            for (int j = i - 1; j >= 0 && nums.get(j) + 3 >= nums.get(i) ; j--) {
                possibilities[i] += possibilities[j];
            }
        }
        return possibilities[possibilities.length - 1];
    }
}   
