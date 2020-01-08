import java.awt.*;

public class RepairRobot {


    ElfComputer robotComputer = new ElfComputer("src\\main\\resources\\Day15.txt");

    Mapper floorMap = new Mapper();

    private Point currentPosition;
    private Point targetPosition;

    public RepairRobot() {

        currentPosition = new Point(0,0);

    }

    public void issueMoveOrder(int direction) {

        Point targetPoint =  setupAndReturnMovementCoordinate(direction);
        int moveResult = Math.toIntExact(robotComputer.executeProgram().get(0));

        switch (moveResult) {
            case 0:
                floorMap.updateFloorLayout(targetPoint, moveResult);
                break;
            case 1:
                floorMap.updateFloorLayout(targetPoint, moveResult);
                this.currentPosition = targetPoint;
                break;
            case 2:
                floorMap.updateFloorLayout(targetPoint, moveResult);
                this.currentPosition = targetPoint;
                this.targetPosition = targetPoint;
                break;
        }




    }

    private Point setupAndReturnMovementCoordinate(int direction) {

        switch (direction) {
            case 1:
                robotComputer.setUpInput("1");
                return new Point(currentPosition.x, currentPosition.y + 1);
            case 2:
                robotComputer.setUpInput("2");
                return new Point(currentPosition.x, currentPosition.y - 1);
            case 3:
                robotComputer.setUpInput("3");
                return new Point(currentPosition.x + 1, currentPosition.y);
            case 4:
                robotComputer.setUpInput("4");
                return new Point(currentPosition.x - 1, currentPosition.y);
            default:
                throw new IllegalArgumentException("Unknown directional order: " + direction);

        }
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public Point getTargetPosition() {

        if (targetPosition == null) {
            return new Point(0,0);
        }

        return targetPosition;
    }
}
