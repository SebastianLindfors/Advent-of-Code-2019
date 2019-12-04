import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

public class Wire {

    List<Line2D> horizontalLines = new ArrayList<>();
    List<Line2D> verticalLines = new ArrayList<>();

    String[] wireDirections;
    int[] wireDistances;



    public Wire(String directions) {

        String[] cardinalDirections = directions.split(",");
        directionsToLines(cardinalDirections);

        wireDirections = new String[cardinalDirections.length];
        wireDistances = new int[cardinalDirections.length];

        for (int i = 0; i < cardinalDirections.length; i++) {
            wireDirections[i] = cardinalDirections[i].substring(0,1);
            wireDistances[i] = Integer.parseInt(cardinalDirections[i].substring(1).strip());
        }


    }

    private void directionsToLines(String[] wireSection) {

        Point2D.Double startPoint = new Point2D.Double(0,0);
        Point2D.Double endPoint = new Point2D.Double (0,0);

        for (String section : wireSection) {
            String direction = section.substring(0,1);
            Integer distance = Integer.parseInt(section.substring(1).strip());

            switch (direction) {

                case "U":
                    endPoint.y = startPoint.y + distance;
                    verticalLines.add(new Line2D.Double(startPoint,endPoint));
                    startPoint.y = endPoint.y;
                    break;
                case "R":
                    endPoint.x = startPoint.x + distance;
                    horizontalLines.add(new Line2D.Double(startPoint,endPoint));
                    startPoint.x = endPoint.x;
                    break;
                case "D":
                    endPoint.y = startPoint.y - distance;
                    verticalLines.add(new Line2D.Double(startPoint,endPoint));
                    startPoint.y = endPoint.y;
                    break;
                case "L":
                    endPoint.x = startPoint.x - distance;
                    horizontalLines.add(new Line2D.Double(startPoint,endPoint));
                    startPoint.x = endPoint.x;
                    break;
                default:
                    System.out.println("Error: Unknown Direction");
                    throw new IllegalArgumentException("Unknown Direction");
            }
        }

    }


}
