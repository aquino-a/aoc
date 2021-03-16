package kr.aquino.aoc.iii;

import kr.aquino.aoc.IOUtility;
import kr.aquino.aoc.Arguments;

public class iii {

    public static void main(String[] args) {
        var arguments = new Arguments(args, 3);
        var path = arguments.SetText(0, "kr/aquino/aoc/iii/input.txt");
        var valueType = arguments.SetText(1, "1");
        int[][] values;
        if(valueType.equals("1"))
            values = new int[][]{ {3,1} };
        else values = new int[][]{ {1,1},{3,1},{5,1},{7,1},{1,2} };

        try {
            var slopes = new Slopes(IOUtility.ReadFile(path), '.', '#');
            var product = 1L;
            for (int i = 0; i < values.length; i++) {
                var count = slopes.FindTrees(0, 0, values[i][0],values[i][1]);
                product *= count;
            }
            System.out.println(String.format("Tree Count: %d", product));

        } catch (Exception e) {
            System.out.println("Not Found");
            e.printStackTrace();
        }


    }
    
}
