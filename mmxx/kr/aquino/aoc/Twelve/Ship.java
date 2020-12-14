package kr.aquino.aoc.Twelve;

public class Ship implements IShip{

    private int x = 0, y = 0;
    private Direction currentDirection = Direction.E;
    private int currentDirectionIndex = 1;
    private static final Direction[] directions = Direction.values();

    public void action(Instruction instruction){
        switch(instruction.getType()){
            case N:
                moveY(instruction.getValue());
                break;
            case S:
                moveY(-instruction.getValue());
                break;
            case E:
                moveX(instruction.getValue());
                break;
            case W:
                moveX(-instruction.getValue());
                break;
            case F:
                action(new Instruction(
                        Enum.valueOf(Instruction.Type.class, currentDirection.toString()),
                        instruction.getValue()));
                break;
            case L:
                rotate(-instruction.getValue());
                break;
            case R:
                rotate(instruction.getValue());
                break;
            default:
                break;
        }
    }

    private void rotate(int degrees) {
        var start = currentDirectionIndex;
        var index = (start + (degrees / 90)) % 4;
        if(index < 0)
            index = directions.length + index;
        
        currentDirectionIndex = index;
        currentDirection = directions[index];
    }

    private void moveX(int amount) {
        x += amount;
    }

    private void moveY(int amount) {
        y += amount;
    }

    public int getManhattanDistance(){
        return Math.abs(x) + Math.abs(y);
    }

    public enum Direction {
        N,E,S,W
    }
    
}
