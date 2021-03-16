package kr.aquino.aoc.xii;

public interface IShip {
    public void action(Instruction instruction);
    public int getManhattanDistance();
}
