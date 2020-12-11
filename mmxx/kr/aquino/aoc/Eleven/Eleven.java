package kr.aquino.aoc.Eleven;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import kr.aquino.aoc.Arguments;
import kr.aquino.aoc.IOUtility;

public class Eleven {

    private static final char EMPTY_SEAT = 'L';
    private static final char OCCUPIED_SEAT = '#';
    private static final char FLOOR = '.';

    public static void main(String[] args) throws IOException {
        var arguments = new Arguments(args, 2);
        var path = arguments.SetText(0, "kr/aquino/aoc/Eleven/test/test.txt");
        Function<Integer[][], Integer> func = arguments.SetText(1, "1").equals("1") ?
                Eleven::findOccupiedSeats : Eleven::findViewableSeats;

        var chart = IOUtility.ReadFile(path).stream()
                            .map(s -> s.chars().boxed().toArray(Integer[]::new))
                            .toArray(Integer[][]::new);

        var answer = func.apply(chart);
        System.out.println(String.format("Answer: %d", answer));
    }

    private static int findOccupiedSeats(Integer[][] chart) {
        return findSeats(chart, Eleven::findAdjacentOccupied, 4);
    }

    private static int findViewableSeats(Integer[][] chart){
        return findSeats(chart, Eleven::findViewableOccupied, 5);
    }

    private static int findAdjacentOccupied(SeatsParameter seatsParameter) {
        var chart = seatsParameter.chart;
        var i = seatsParameter.i;
        var j = seatsParameter.j;

        var occupied = 0;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if(i + k < 0 || i + k >= chart.length)
                    continue;
                else if(j + l < 0 || j + l >= chart[i].length)
                    continue;
                else if(k == 0 && l == 0)
                    continue;
                else if(chart[i + k][j + l] == OCCUPIED_SEAT)
                    occupied++;
            }
        }
        return occupied;
    }

    private static int findViewableOccupied(SeatsParameter seatsParameter) {
        var chart= seatsParameter.chart;
        var i = seatsParameter.i;
        var j = seatsParameter.j;
        
        var occupied = 0;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                for (int m = 0; m < chart[i].length; m++) {
                    var extendedK = k + (m * k);
                    var extendedL = l + (m * l);
                    
                    if(i + extendedK < 0 || i + extendedK >= chart.length)
                        break;
                    else if(j + extendedL < 0 || j + extendedL >= chart[i].length)
                        break;
                    else if(extendedK == 0 && extendedL == 0)
                        break;
                    else if(chart[i + extendedK][j + extendedL] == OCCUPIED_SEAT){
                        occupied++;
                        break;
                    }
                    else if(chart[i + extendedK][j + extendedL] == EMPTY_SEAT)
                        break;
                }   
            }
        }
        return occupied;
    }
    
    private static int findSeats(Integer[][] chart, Function<SeatsParameter, Integer> func, int limit){
        var newChart = new Integer[chart.length][chart[0].length];
        while(true){
            var changes = 0;
            var totalOccupiedCount = 0;
    
            for (int i = 0; i < chart.length; i++) {
                for (int j = 0; j < chart[0].length; j++) {
                    if(chart[i][j] == FLOOR){
                        newChart[i][j] = (int) FLOOR;
                        continue;
                    }
                    else if(chart[i][j] == OCCUPIED_SEAT)
                        totalOccupiedCount++;
    
                    var occupied = func.apply(new SeatsParameter(chart, i, j));
                    
                    if(occupied == 0 && chart[i][j] == EMPTY_SEAT){
                        totalOccupiedCount++;
                        newChart[i][j] = (int) OCCUPIED_SEAT;
                        changes++;
                    }
                    else if(occupied >= limit && chart[i][j] == OCCUPIED_SEAT){
                        totalOccupiedCount--;
                        newChart[i][j] = (int) EMPTY_SEAT;
                        changes++;
                    }
                    else newChart[i][j] = chart[i][j];
                }
            }
    
            if(changes == 0){
                return totalOccupiedCount;
            }
            else {
                var temp = chart;
                chart = newChart;
                newChart = temp;
            }
        }
    }
    
}
