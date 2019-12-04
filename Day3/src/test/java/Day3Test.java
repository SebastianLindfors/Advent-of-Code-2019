import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.ArrayList;

public class Day3Test {




    @Test
    public void manhattanDistance_ComputationTest() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(5,5);
        int expected = 10;

        int actual = Day3.computeManhattanDistance(p1,p2);

        Assertions.assertEquals(expected, actual, "Expected the points to be 10 units apart, was " + actual);
    }

    @Test
    public void computeIntersectionOfWiresTest1() {
        Wire w1 = new Wire("R8,U5,L5,D3");
        Wire w2 = new Wire("U7,R6,D4,L4");

        List<Point2D> expected = new ArrayList<Point2D>();
        expected.add(new Point2D.Double(6,5));
        expected.add(new Point2D.Double(3,3));


        List actual = Day3.computeIntersectionsOfWires(w1,w2);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void computeIntersectionOfWiresTest2() {
        Wire w1 = new Wire("R5,U9");
        Wire w2 = new Wire("U8,R8");

        List<Point2D> expected = new ArrayList<Point2D>();
        expected.add(new Point2D.Double(5,8));

        List actual = Day3.computeIntersectionsOfWires(w1,w2);

        Assertions.assertEquals(expected,actual);
    }



    @Test
    public void computeSmallestManhattanDistance() {
        List<Point2D> input = new ArrayList<Point2D>();
        input.add(new Point(6,5));
        input.add(new Point(3,3));

        Integer expected = 3 + 3;

        Integer actual = Day3.computeSmallestManhattanDistance(input);


        Assertions.assertEquals(expected,actual);

    }

    @Test
    public void computeWireToAnswerTest1() {

        Wire wire1 = new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72");
        Wire wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83");

        List<Point2D> intersections = Day3.computeIntersectionsOfWires(wire1,wire2);

        int actual = Day3.computeSmallestManhattanDistance2(intersections);

        Assertions.assertEquals(159, actual);

    }

    @Test
    public void computeWireToAnswerTest2() {

        Wire wire1 = new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
        Wire wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

        List<Point2D> intersections = Day3.computeIntersectionsOfWires(wire1,wire2);

        int actual = Day3.computeSmallestManhattanDistance2(intersections);

        Assertions.assertEquals(135, actual);
    }

    @Test
    public void computeWireDistanceTest1() {

        Wire wire1 = new Wire("R8,U5,L5,D3");
        Wire wire2 = new Wire("U7,R6,D4,L4");
        Point2D.Double point = new Point2D.Double(3,3);
        int actual = Day3.computeWireDistanceToPoint(point, wire1, wire2);

        Assertions.assertEquals(40, actual);
    }

    @Test
    public void computeWireDistanceTest2() {

        Wire wire1 = new Wire("R8,U5,L5,D3");
        Wire wire2 = new Wire("U7,R6,D4,L4");
        Point2D.Double point = new Point2D.Double(6,5);
        int actual = Day3.computeWireDistanceToPoint(point, wire1, wire2);

        Assertions.assertEquals(30, actual);
    }

    @Test
    public void computeWireToAnswerTest3() {

        Wire wire1 = new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
        Wire wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

        List<Point2D> intersections = Day3.computeIntersectionsOfWires(wire1,wire2);

        int actual = Day3.computeSmallestWireDistance(intersections, wire1, wire2);

        Assertions.assertEquals(410, actual);
    }

    @Test
    public void computeWireToAnswerTest4() {

        Wire wire1 = new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72");
        Wire wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83");

        List<Point2D> intersections = Day3.computeIntersectionsOfWires(wire1,wire2);

        int actual = Day3.computeSmallestWireDistance(intersections, wire1, wire2);

        Assertions.assertEquals(610, actual);

    }

}
