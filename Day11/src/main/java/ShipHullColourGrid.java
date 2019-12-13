import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ShipHullColourGrid {

    /** False = Black Colour
     *  True = White Colour
     */

    Map<Point, Boolean> colourGrid = new HashMap<>();
    Map<Point, Boolean> visited = new HashMap<>();

    int maxX = 3;
    int minX = -3;

    int maxY = 3;
    int minY = -3;

    public void paintPosition(Point position, boolean colour) {

        if (!colourGrid.containsKey(position)) {
            if (position.x > maxX) {
                maxX = position.x + 1;
            }
            else if(position.x < minX) {
                minX = position.x - 1;
            }

            if (position.y > maxY) {
                maxY = position.y + 1;
            }
            else if(position.y < minY) {
                minY = position.y - 1;
            }
        }
        colourGrid.put(position, colour);
    }

    public void visitedPoint(Point position) {
        visited.put(position, true);
    }

    public int blackOrWhite(Point position) {
        if (colourGrid.containsKey(position)) {
            if (colourGrid.get(position)) {
                return 1;
            }
            else return 0;
        }
        else return 0;
    }

    public int getVisitedSpaces() {
        return visited.keySet().size();
    }

    public String printPattern() {
        String outputString = "";
        for (int i = minY; i < maxY + 1; i++) {
            for (int j = minX; j < maxX + 1; j++) {
                Point currentPoint = new Point(j,i);
                if (colourGrid.containsKey(currentPoint)) {
                    if (colourGrid.get(currentPoint)) {
                        outputString += "#";
                    }
                    else {
                        outputString += ".";
                    }
                }
                else {
                    outputString += ".";
                }

            }
            outputString += "\n";
        }
        return outputString;
    }

}
