package kr.aquino.aoc.viii;

public class InfiniteLoopException extends RuntimeException{

    public final int accumulator;

    public InfiniteLoopException(int accumulator){
        this.accumulator = accumulator;
    }
}
