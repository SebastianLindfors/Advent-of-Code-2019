import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Day3 {

    public static void main(String[] args) {
        String rawData = "";

        try {
            rawData = loadDataFromDisk("src\\main\\resources\\Day3.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String[] splitData = rawData.split("\n");

        Wire wire1 = new Wire(splitData[0]);
        Wire wire2 = new Wire(splitData[1]);

        List<Point2D> intersections = computeIntersectionsOfWires(wire1,wire2);

        Integer answer = computeSmallestManhattanDistance(intersections);
        Integer answer2 = computeSmallestWireDistance(intersections, wire1, wire2);

        System.out.println("Shortest Manhattan Distance to a point of intersection is " + answer);
        System.out.println("Shortest Wire distance to a point of intersection is " + answer2 + " steps.");



    }

    public static int computeManhattanDistance(Point2D p1, Point2D p2) {
        return (int) (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
    }

    public static int computeWireDistanceToPoint(Point2D point, Wire w1, Wire w2) {

        int wire1Distance = 0;
        int wire2Distance = 0;

        boolean wire1Found = false;
        boolean wire2Found = false;

        Point2D.Double w1startPoint = new Point2D.Double(0,0);
        Point2D.Double w1endPoint = new Point2D.Double(0,0);
        Line2D w1line;

        Point2D.Double w2startPoint = new Point2D.Double(0,0);
        Point2D.Double w2endPoint = new Point2D.Double(0,0);
        Line2D w2line;

        for (int i = 0; i < Math.min(w1.wireDistances.length,w2.wireDistances.length); i++) {

            String w1Direction = w1.wireDirections[i];
            int w1Distance = w1.wireDistances[i];

            String w2Direction = w2.wireDirections[i];
            int w2Distance = w2.wireDistances[i];

            if (!wire1Found) {
                switch (w1Direction) {

                    case "U":
                        w1endPoint.y = w1startPoint.y + w1Distance;
                        w1line = new Line2D.Double(w1startPoint,w1endPoint);
//                        System.out.println("W1 Start: " + w1startPoint.x + ", " + w1startPoint.y + " End: " + w1endPoint.x + ", " + w1endPoint.y + " Target: " + point.getX() + ", " + point.getY());
                        w1startPoint.y = w1endPoint.y;
                        break;
                    case "R":
                        w1endPoint.x = w1startPoint.x + w1Distance;
                        w1line = new Line2D.Double(w1startPoint,w1endPoint);
//                        System.out.println("W1 Start: " + w1startPoint.x + ", " + w1startPoint.y + " End: " + w1endPoint.x + ", " + w1endPoint.y + " Target: " + point.getX() + ", " + point.getY());
                        w1startPoint.x = w1endPoint.x;
                        break;
                    case "D":
                        w1endPoint.y = w1startPoint.y - w1Distance;
                        w1line = new Line2D.Double(w1startPoint,w1endPoint);
//                        System.out.println("W1 Start: " + w1startPoint.x + ", " + w1startPoint.y + " End: " + w1endPoint.x + ", " + w1endPoint.y + " Target: " + point.getX() + ", " + point.getY());
                        w1startPoint.y = w1endPoint.y;
                        break;
                    case "L":
                        w1endPoint.x = w1startPoint.x - w1Distance;
//                        System.out.println("W1 Start: " + w1startPoint.x + ", " + w1startPoint.y + " End: " + w1endPoint.x + ", " + w1endPoint.y + " Target: " + point.getX() + ", " + point.getY());
                        w1line = new Line2D.Double(w1startPoint,w1endPoint);
                        w1startPoint.x = w1endPoint.x;
                        break;
                    default:
                        System.out.println("Error: Unknown Direction");
                        throw new IllegalArgumentException("Unknown Direction");
                }
                if (w1line.ptLineDist(point) == 0) {
                    wire1Distance += w1Distance - w1endPoint.distance(point);
                    wire1Found = true;
//                    System.out.println("Wire 1 Distance Located! Distance to point: " + w1endPoint.distance(point));

                }
                else {
                    wire1Distance += w1Distance;
                }
            }
            if (!wire2Found) {
                switch (w2Direction) {
                    case "U":
                        w2endPoint.y = w2startPoint.y + w2Distance;
                        w2line = new Line2D.Double(w2startPoint,w2endPoint);
//                        System.out.println("W2 Start: " + w2startPoint.x + ", " + w2startPoint.y + " End: " + w2endPoint.x + ", " + w2endPoint.y + " Target: " + point.getX() + ", " + point.getY());
                        w2startPoint.y = w2endPoint.y;
                        break;
                    case "R":
                        w2endPoint.x = w2startPoint.x + w2Distance;
                        w2line = new Line2D.Double(w2startPoint,w2endPoint);
//                        System.out.println("W2 Start: " + w2startPoint.x + ", " + w2startPoint.y + " End: " + w2endPoint.x + ", " + w2endPoint.y + " Target: " + point.getX() + ", " + point.getY());
                        w2startPoint.x = w2endPoint.x;
                        break;
                    case "D":
                        w2endPoint.y = w2startPoint.y - w2Distance;
                        w2line = new Line2D.Double(w2startPoint,w2endPoint);
//                        System.out.println("W2 Start: " + w2startPoint.x + ", " + w2startPoint.y + " End: " + w2endPoint.x + ", " + w2endPoint.y + " Target: " + point.getX() + ", " + point.getY());
                        w2startPoint.y = w2endPoint.y;
                        break;
                    case "L":
                        w2endPoint.x = w2startPoint.x - w2Distance;
//                        System.out.println("W2 Start: " + w2startPoint.x + ", " + w2startPoint.y + " End: " + w2endPoint.x + ", " + w2endPoint.y + " Target: " + point.getX() + ", " + point.getY());
                        w2line = new Line2D.Double(w2startPoint,w2endPoint);
                        w2startPoint.x = w2endPoint.x;
                        break;
                    default:
                        System.out.println("Error: Unknown Direction");
                        throw new IllegalArgumentException("Unknown Direction");
            }
            if (w2line.ptLineDist(point) == 0) {
                wire2Distance += w2Distance - w2endPoint.distance(point);
                wire2Found = true;
//                System.out.println("Wire 2 Distance Located! Distance to point: " + w2endPoint.distance(point));

            }
            else {
                wire2Distance += w2Distance;
            }
            }
        }
        return wire1Distance + wire2Distance;
    }

    public static Integer computeSmallestManhattanDistance(List<Point2D> points) {

        return points.stream()
                .map(x ->(int) Math.abs(x.getX()) + (int) Math.abs(x.getY()))
                .min(Integer::compare)
                .get();

    }

    public static int computeSmallestManhattanDistance2(List<Point2D> points) {

        Point2D.Double origo = new Point2D.Double(0,0);
        int shortestDistance = Integer.MAX_VALUE;
        for (Point2D point : points) {
            int distance = computeManhattanDistance(point, origo);
            if (distance < shortestDistance)
                shortestDistance = distance;
        }

        return shortestDistance;

    }

    public static int computeSmallestWireDistance(List<Point2D> points, Wire wire1, Wire wire2) {


        int shortestDistance = Integer.MAX_VALUE;
        for (Point2D point : points) {
            int distance = computeWireDistanceToPoint(point, wire1, wire2);
            if (distance < shortestDistance)
                shortestDistance = distance;
        }

        return shortestDistance;
    }

    public static List<Point2D> computeIntersectionsOfWires(Wire w1, Wire w2) {

        List<Point2D> intersections = new ArrayList<>();
        for (Line2D horizontalLine : w1.horizontalLines) {
            for (Line2D verticalLine : w2.verticalLines) {
                if (horizontalLine.intersectsLine(verticalLine)) {
                    intersections.add(new Point((int) verticalLine.getX1(), (int) horizontalLine.getY1()));
                }
            }
        }
        for (Line2D verticalLine : w1.verticalLines) {
            for (Line2D horizontalLine : w2.horizontalLines) {
                if (verticalLine.intersectsLine(horizontalLine)) {
                    intersections.add(new Point((int) verticalLine.getX1(), (int) horizontalLine.getY1()));
                }
            }
        }
        intersections.remove(new Point(0,0));
        return intersections;
    }

    public static String loadDataFromDisk(String fileName) throws IOException {

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }

    }





}
