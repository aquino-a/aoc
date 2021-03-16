package kr.aquino.aoc.iii;

import java.util.List;


public class Slopes {

    private final char tree;
    private final char[][] slopes;

    public Slopes(List<String> slopes, char open, char tree){
        this.slopes = slopes.stream().map(s -> s.toCharArray()).toArray(char[][]::new);
        this.tree = tree;
    } 

    public int FindTrees(int startx, int starty, int x, int y){
        var count = 0;
        for (int i = starty, j = startx; i < slopes.length; i += y, j += x, j %= slopes[0].length) {
            if(slopes[i][j] == tree)
                count++;
        }
        return count;
    }
    
}
