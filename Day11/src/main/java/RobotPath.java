import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class RobotPath {

    private Point currentPosition;

    List<Point> pathTaken = new ArrayList<>();

    public RobotPath() {

        currentPosition = new Point(0,0);
    }

    public void takeStep(Point nextStep) {

        if (Math.abs(nextStep.x - currentPosition.x) > 1 || Math.abs(nextStep.y - currentPosition.y) > 1) {
            throw new IllegalArgumentException("Next step has to be adjacent to current position.");
        }

        pathTaken.add(currentPosition);
        currentPosition = nextStep;


    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public Point getLastPosition() {
        return pathTaken.get(pathTaken.size() - 1);
    }

    public String pathString() {

        int stepNumber = 0;
        String outputString = "";
        for (Point step : pathTaken) {
            outputString += "Step Number: " + stepNumber++ + " -> " + step.toString();
        }
        return outputString;
    }

}
