package kr.aquino.aoc.xii;

public class WaypointShip implements IShip{

    private int x = 0, y = 0;
    private final Waypoint waypoint = new Waypoint();


    public void action(Instruction instruction){
        switch(instruction.getType()){
            case N:
            case S:
            case E:
            case W:
            case L:
            case R:
                waypoint.modify(instruction);
                break;
            case F:
                move(instruction.getValue());
                break;
            default:
                break;
        }
    }

    private void move(int value) {
        x += waypoint.x * value;
        y += waypoint.y * value;
    }

    public int getManhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }
}
