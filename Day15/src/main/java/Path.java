import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Path {

    Map<Point, Integer>  pointDirection= new HashMap<>();

    Point currentPoint;




    public void addPointToPath(Point newPoint) {

        Integer direction;
        if (newPoint.x == currentPoint.x + 1) {
            direction = 2;
        }
        else if (newPoint.x == currentPoint.x - 1) {
            direction = 4;
        }
        else if (newPoint.y == currentPoint.y + 1) {
            direction = 1;
        }
        else if (newPoint.y == currentPoint.y - 1) {
            direction = 3;
        }
        else {
            throw new IllegalArgumentException("New point must be adjacent to older point in path.");
        }

        pointDirection.put(currentPoint, direction);

        Integer undecidedDirection = -1;

        pointDirection.put(newPoint, undecidedDirection);

    }

}
