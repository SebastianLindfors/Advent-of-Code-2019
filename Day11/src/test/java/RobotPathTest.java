import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RobotPathTest {

    @Test
    void takeStepTest_oneStep_expectsONEZERO() {

        RobotPath testPath = new RobotPath();
        Point stepTo = new Point(1,0);

        testPath.takeStep(stepTo);


        assertEquals(stepTo, testPath.getCurrentPosition());
        assertEquals(1, testPath.pathTaken.size());
    }

    @Test
    void takeStepTest_TwoSteps_expectsTWOZERO() {

        RobotPath testPath = new RobotPath();
        Point stepTo1 = new Point(1,0);
        Point stepTo2 = new Point(2,0);

        testPath.takeStep(stepTo1);
        testPath.takeStep(stepTo2);

        assertEquals(stepTo2, testPath.getCurrentPosition());
        assertEquals(2, testPath.pathTaken.size());
    }

    @Test
    void takeStepTest_IllegalStep_expectsTWOZERO() {

        RobotPath testPath = new RobotPath();
        Point stepTo2 = new Point(2,0);

        assertThrows(IllegalArgumentException.class, () -> {
            testPath.takeStep(stepTo2);
        });
    }


}