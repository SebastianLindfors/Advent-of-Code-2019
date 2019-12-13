import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EHPRobotTest {

    @Test
    public void turn_leftOnce_expectsLeft() {

        EHPRobot testRobot = new EHPRobot();
        char expected = 'L';

        testRobot.turn(0);

        assertEquals(expected, testRobot.getCurrentFacing());

    }

    @Test
    public void turn_RightOnce_expectsRight() {

        EHPRobot testRobot = new EHPRobot();
        char expected = 'R';

        testRobot.turn(1);

        assertEquals(expected, testRobot.getCurrentFacing());

    }

    @Test
    public void turn_NoTurn_expectsUp() {

        EHPRobot testRobot = new EHPRobot();
        char expected = 'U';

        assertEquals(expected, testRobot.getCurrentFacing());

    }

    @Test
    public void turn_LeftTwice_expectsDown() {

        EHPRobot testRobot = new EHPRobot();
        char expected = 'D';

        testRobot.turn(0);
        testRobot.turn(0);

        assertEquals(expected, testRobot.getCurrentFacing());

    }

    @Test
    public void turn_RightTrice_expectsLeft() {

        EHPRobot testRobot = new EHPRobot();
        char expected = 'L';

        testRobot.turn(1);
        testRobot.turn(1);
        testRobot.turn(1);

        assertEquals(expected, testRobot.getCurrentFacing());

    }

    @Test
    public void turn_LeftThenRight_expectsUp() {

        EHPRobot testRobot = new EHPRobot();
        char expected = 'U';

        testRobot.turn(0);
        testRobot.turn(1);

        assertEquals(expected, testRobot.getCurrentFacing());

    }

    @Test
    public void turn_InvalidInput_expectsException() {

        EHPRobot testRobot = new EHPRobot();

        assertThrows(IllegalArgumentException.class,() -> {
                testRobot.turn(3); } );

    }

    @Test
    void stepForwardTest_StepOnce_expectsZEROONE() {

        EHPRobot testRobot = new EHPRobot();
        Point expected = new Point(0,1);

        testRobot.stepForward();

        assertEquals(expected, testRobot.getCurrentPosition());

    }
    @Test
    void stepForwardTest_StepTwice_expectsZERORWO() {

        EHPRobot testRobot = new EHPRobot();
        Point expected = new Point(0,2);

        testRobot.stepForward();
        testRobot.stepForward();

        assertEquals(expected, testRobot.getCurrentPosition());

    }

    @Test
    void stepForwardTest_StepTurnRightStep_expectsONEONE() {

        EHPRobot testRobot = new EHPRobot();
        Point expected = new Point(1,1);

        testRobot.stepForward();
        testRobot.turn(1);
        testRobot.stepForward();

        assertEquals(expected, testRobot.getCurrentPosition());

    }

    @Test
    void stepForwardTest_StepTurnLeftStep_expectsNEGATIVEONEONE() {

        EHPRobot testRobot = new EHPRobot();
        Point expected = new Point(-1,1);

        testRobot.stepForward();
        testRobot.turn(0);
        testRobot.stepForward();

        assertEquals(expected, testRobot.getCurrentPosition());

    }

}