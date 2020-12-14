package kr.aquino.aoc.Twelve;

public interface IShip {
    public void action(Instruction instruction);
    public int getManhattanDistance();
}
