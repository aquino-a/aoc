package kr.aquino.aoc.Twelve;

public class Waypoint {
    
    int x = 10, y = 1, temp = -1;

	public void modify(Instruction instruction) {
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
            case L:
                rotateLeft(instruction.getValue());
                break;
            case R:
                rotateRight(instruction.getValue());
                break;
            default:
                break;
        }
    }

    private void rotateRight(int degrees) {
        var times = getRotationTimes(degrees);
        for (int i = 0; i < times; i++) {
            temp = x;
            x = y;
            y = -temp;
        }
    }
    
    private void rotateLeft(int degrees) {
        var times = getRotationTimes(degrees);
        for (int i = 0; i < times; i++) {
            temp = x;
            x = -y;
            y = temp;
        }
    }

    private int getRotationTimes(int degrees) {
        return degrees / 90;
    }

    private void rotate(int degrees) {
        // var start = currentDirectionIndex;
        // var index = (start + (degrees / 90)) % 4;
        // if(index < 0)
        //     index = directions.length + index;
        
        // currentDirectionIndex = index;
        // currentDirection = directions[index];
    }

    private void moveX(int amount) {
        x += amount;
    }

    private void moveY(int amount) {
        y += amount;
    }


}
