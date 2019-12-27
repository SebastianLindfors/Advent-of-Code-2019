import java.awt.*;

public class RepairRobot {


    ElfComputer robotComputer = new ElfComputer("src\\main\\resources\\Day15.txt");

    Point currentPosition;

    public RepairRobot() {

        currentPosition = new Point(0,0);

    }

    public void issueMoveOrder(int direction) {

        setUpComputerInput(direction);
        long moveResult = robotComputer.executeProgram().get(0);


    }

    private Point setupAndReturnTargetCoordinate(int direction) {

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

}
