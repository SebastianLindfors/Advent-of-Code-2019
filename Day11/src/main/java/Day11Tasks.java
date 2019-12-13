import org.w3c.dom.ls.LSOutput;

import java.awt.*;

public class Day11Tasks {

    public static void main(String[] arg) {

        String fileName = "src\\main\\resources\\Day11.txt";

        EHPRobot hullPaintingRobot = new EHPRobot(fileName);

        ShipHullColourGrid shipHull = new ShipHullColourGrid();
        shipHull.paintPosition(new Point(0,0), true);

        while(!hullPaintingRobot.hasAiHalted()) {
            hullPaintingRobot.setNewComputerInput(String.valueOf(shipHull.blackOrWhite(hullPaintingRobot.getCurrentPosition())));
            hullPaintingRobot.clearAiOutput();

            int paint = hullPaintingRobot.executePaintAndMoveProgram();
            shipHull.paintPosition(hullPaintingRobot.getLastPosition(), (paint == 1));
            shipHull.visitedPoint(hullPaintingRobot.getLastPosition());
            //System.out.println(shipHull.printPattern());
        }

        //System.out.println(hullPaintingRobot.getFullPath());
        System.out.println(shipHull.printPattern());
        System.out.println("The number of spaces painted at least once is " + shipHull.getVisitedSpaces());

    }





}
