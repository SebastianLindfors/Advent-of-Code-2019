import java.awt.*;
import java.sql.SQLOutput;
import java.util.List;

public class EHPRobot {

    int stepsTaken = 0;

    private int facingPointer = 0;
    private char[] possibleFacings = {'U','R','D','L'};

    private ElfComputer AICore;

    private RobotPath pathWalked = new RobotPath();

    public EHPRobot() {
        //Exists for ease of testing.
    }

    public EHPRobot(ElfComputer AI) {

        AICore = AI;

    }
    public EHPRobot(String filePath) {

        AICore = new ElfComputer(filePath);

    }

    public int executePaintAndMoveProgram() {

        List<Long> coreOutput = AICore.executeProgram();

//        String turnString = "";
//        String paintString = "";
//
//        if (coreOutput.get(0) == 0) {
//            paintString = "Paint Black";
//        }
//        else if (coreOutput.get(0) == 1) {
//            paintString = "Paint White";
//        }
//
//        if (coreOutput.get(1) == 0) {
//            turnString = "Turn Left";
//        }
//        else if (coreOutput.get(1) == 1) {
//            turnString = "Turn Right";
//        }
//        System.out.println("Output 1: " + paintString + ", Output 2: " + turnString);

        this.turn(Math.toIntExact(coreOutput.get(1)));
        this.stepForward();

        return(Math.toIntExact(coreOutput.get(0)));
    }

    public void stepForward() {

        Point forwardStep = determineNextPositionFromFacing();
        stepsTaken++;
        this.pathWalked.takeStep(forwardStep);

    }

    public void turn(int directive) {

        if (directive == 0) {
            if (facingPointer == 0) {
                facingPointer = 3;
            }
            else {
                facingPointer--;
            }
        }
        else if (directive == 1) {
            if (facingPointer == 3) {
                facingPointer = 0;
            }
            else {
                facingPointer++;
            }
        }
        else {
            throw new IllegalArgumentException("Turning directives must always be 1 or 0");
        }



    }

    public void setNewComputerInput(String newInput) {
        AICore.setUpInput(newInput);
    }

    public void clearAiOutput() {
        this.AICore.clearOutput();
    }

    public String getFullPath() {
        return this.pathWalked.pathString();
    }

    public char getCurrentFacing() {
        return possibleFacings[facingPointer];
    }

    public Point getCurrentPosition() {
        return pathWalked.getCurrentPosition();
    }

    public Point getLastPosition() {
        return pathWalked.getLastPosition();
    }

    public boolean hasAiHalted() {
        return AICore.isExecutionHalted();
    }

    private Point determineNextPositionFromFacing() {

       Point output = new Point();

        switch(possibleFacings[facingPointer]) {
            case 'U':
                output = new Point(this.pathWalked.getCurrentPosition().x ,
                                    this.pathWalked.getCurrentPosition().y + 1);
                break;
            case 'L':
                output = new Point(this.pathWalked.getCurrentPosition().x - 1 ,
                                        this.pathWalked.getCurrentPosition().y);
                break;
            case 'D':
                    output = new Point(this.pathWalked.getCurrentPosition().x ,
                                         this.pathWalked.getCurrentPosition().y - 1) ;
                    break;
            case 'R':
                output = new Point(this.pathWalked.getCurrentPosition().x + 1 ,
                                         this.pathWalked.getCurrentPosition().y);
                break;

        }
        return output;
    }


}

