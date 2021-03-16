package kr.aquino.aoc.v;

import java.util.Set;

public class BoardingPass {

    private final int seatId;
    private final int rows;
    private final int columns;
    private final String text;
    private final Set<Character> first = Set.of('F', 'L');
    private final Set<Character> second = Set.of('B', 'R');

    public BoardingPass(int rows, int columns, String text){
        this.text = text;
        this.rows = rows;
        this.columns = columns;
        seatId = calculateSeatId(text);
    }

    private int calculateSeatId(String text) {
        var rowNum = calculateNum(0, rows - 1, text.substring(0, 7));
        var colNum = calculateNum(0, columns - 1, text.substring(7));
        return ( rowNum * 8 ) + colNum;
    }

    private int calculateNum(int min, int max, String text) {
        if(min == max)
            return min;
        var mid = (min + max) / 2;
        if(first.contains(text.charAt(0))){
            return calculateNum(min, mid, text.substring(1));
        }
        else if(second.contains(text.charAt(0))){
            return calculateNum(mid + 1, max, text.substring(1));
        }
        else return -1;
    }

    public int getSeatId() {
        return seatId;
    }
    
}
