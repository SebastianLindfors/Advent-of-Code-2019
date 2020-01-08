import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

class RepairRobotTest {

   RepairRobot testRobot = new RepairRobot();

   @Test
   public void issueMoveOrderTest_move1() {

       int direction = 1;

       testRobot.issueMoveOrder(direction);

       Point expected = new Point(0,1);
       Point actual = testRobot.getCurrentPosition();

       assertEquals(expected, actual);

   }




}