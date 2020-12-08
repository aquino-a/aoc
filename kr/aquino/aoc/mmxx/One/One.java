package kr.aquino.aoc.mmxx.One;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kr.aquino.aoc.mmxx.IOUtility;

public class One {

    public static int[] FindAddNumbers(int target, IntStream stream) {
        var sortedNums = stream.sorted().toArray();

        for (int i = 0, j = sortedNums.length - 1; i < j;) {

            var sum = sortedNums[i] + sortedNums[j];
            if (sum == target) {
                return new int[] { sortedNums[i], sortedNums[j] };
            } else if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            } else
                break;
        }
        throw new IllegalArgumentException("Target not found.");
    }

    public static int[] FindThreeAddNumbers(int target, IntStream stream) {
        var sortedNums = stream.sorted().toArray();
        var hs = IntStream.of(sortedNums).map(i -> target - i).boxed().collect(Collectors.toCollection(HashSet::new));

        for (int i = 0; i < sortedNums.length; i++) {
            for (int j = 0; j < sortedNums.length; j++) {
                var sum = sortedNums[i] + sortedNums[j];
                if (hs.contains(sum)) {
                    return new int[] { sortedNums[i], sortedNums[j], target - sum };
                }
            }
        }
        throw new IllegalArgumentException("Target not found.");
    }

    


    public static IntStream GetStream(String url) throws MalformedURLException, IOException {
        return IOUtility.ReadFile(url).stream().mapToInt(s -> Integer.parseInt(s));
    }

    public static void main(String[] args){
        String url = "input.txt";
        int target = 2020;
        BiFunction<Integer, IntStream, int[]> func =  One::FindAddNumbers;
        if(args.length > 0){
            target = Integer.parseInt(args[0]);
        }
        if(args.length > 1)
            url = args[1];

        if(args.length > 2){
            if(args[2].equals("2"))
                func = One::FindThreeAddNumbers;
        }


        try {
            var pair = func.apply(target, GetStream(url));
            var product = 1;
            for (int i = 0; i < pair.length; i++) {
                System.out.println(String.format("Number %d: %d", i + 1, pair[i]));
                product *= pair[i];
            }
            System.out.println(String.format("Product: %d", product));
            System.out.println();

        } catch (Exception e) {
            System.out.println("Not found.");
            e.printStackTrace();
        }


    }

    
}
