package kr.aquino.aoc.Nine;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.LongFunction;
import java.util.stream.IntStream;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class Nine {
    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 4);
        var path = arguments.SetText(0, "kr/aquino/aoc/Nine/input.txt");
        var number = Integer.parseInt(arguments.SetText(1, "25"));
        BiFunction<Integer, Long[], Long> func = arguments.SetText(2, "1").equals("1") ? Nine::findAnomaly : Nine::findContiguousSet;
        
        var nums = IOUtility.ReadFile(path).stream().mapToLong(s -> Long.parseLong(s)).boxed().toArray(Long[]::new);
        var answer = func.apply(number, nums);

        System.out.println(String.format("Answer: %d", answer));
    }

    private static Long findAnomaly(Integer preamble, Long[] nums) {
        for (int i = preamble; i < nums.length; i++) {
            if(!isNormal(preamble, nums, i)){
                return nums[i];
            }     
        }
        throw new IllegalArgumentException("no anomaly");
    }

    private static boolean isNormal(int preamble, Long[] nums, int i) {
        for (int j = i - preamble; j < i; j++) {
            for (int k = i - preamble; k < i; k++) {
                if(nums[j] + nums[k] == nums[i])
                    return true;
            }
        }
        return false;
    }

    private static Long findContiguousSet(Integer target, Long[] nums){
        var sum = 0l;
        for (int i = 0, j = 1; i < nums.length && j < nums.length;) {
            sum = IntStream.range(i, j + 1).mapToLong(num -> nums[num]).sum();
            if(sum == target){
                var range = IntStream.range(i, j + 1).mapToLong(num -> nums[num]).sorted().toArray();
                return range[0] + range[range.length - 1];
            }
            else if(sum > target && i + 1 < j){
                i++;
                continue;
            } 
            else {
                j++;
                continue;
            }
        }
        throw new IllegalArgumentException("contiguous set not found");

    }
}
