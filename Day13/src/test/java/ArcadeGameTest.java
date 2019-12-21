import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ArcadeGameTest {

    ArcadeGame testArcadeGame = new ArcadeGame("src\\test\\resources\\Day13Test.txt");

    @Test
    void getGameStateData_getUnsetPoint_expectsException() {

        assertThrows(IllegalArgumentException.class, () -> testArcadeGame.getGameStatePointData(1, 1));

    }

    @Test
    void updateGameState_update111_expects1() {

        testArcadeGame.updateGameState(1,1,1);
        int expected = 1;

        assertEquals(expected,testArcadeGame.getGameStatePointData(1,1));

    }

    @Test
    void updateGameState_updateNegative111_expectsException() {

        assertThrows(IllegalArgumentException.class, () -> testArcadeGame.updateGameState(-1,1,1));

    }
    @Test
    void updateGameState_update1Negative11_expectsException() {

        assertThrows(IllegalArgumentException.class, () -> testArcadeGame.updateGameState(1,-1,1));

    }
    @Test
    void updateGameState_update118_expectsException() {

        assertThrows(IllegalArgumentException.class, () -> testArcadeGame.updateGameState(1,1,8));

    }

    @Test
    void screenItemCountsTest_noUpdate_expects0() {

        int expected = 0;

        assertEquals(expected, testArcadeGame.getScreenItemCount(1));

    }

    @Test
    void screenItemCountsTest_update111_expects1() {

        testArcadeGame.updateGameState(1,1,1);
        int expected = 1;

        assertEquals(expected, testArcadeGame.getScreenItemCount(1));

    }

    @Test
    void screenItemCountsTest_update111update110_expects1expects0() {

        testArcadeGame.updateGameState(1,1,1);
        testArcadeGame.updateGameState(1,1,0);
        int expected1 = 0;
        int expected2 = 1;

        assertEquals(expected1, testArcadeGame.getScreenItemCount(1));
        assertEquals(expected2, testArcadeGame.getScreenItemCount(0));

    }

    @Test
    void createOutputStringTest_makeFiveByFive() {

        testArcadeGame.updateGameState(0,0,1);
        testArcadeGame.updateGameState(1,0,2);
        testArcadeGame.updateGameState(2,0,2);
        testArcadeGame.updateGameState(3,0,2);
        testArcadeGame.updateGameState(4,0,1);

        testArcadeGame.updateGameState(0,1,1);
        testArcadeGame.updateGameState(1,1,2);
        testArcadeGame.updateGameState(2,1,2);
        testArcadeGame.updateGameState(3,1,2);
        testArcadeGame.updateGameState(4,1,1);

        testArcadeGame.updateGameState(0,2,1);
        testArcadeGame.updateGameState(1,2,2);
        testArcadeGame.updateGameState(2,2,2);
        testArcadeGame.updateGameState(3,2,2);
        testArcadeGame.updateGameState(4,2,1);

        testArcadeGame.updateGameState(0,3,1);
        testArcadeGame.updateGameState(1,3,0);
        testArcadeGame.updateGameState(2,3,4);
        testArcadeGame.updateGameState(3,3,0);
        testArcadeGame.updateGameState(4,3,1);

        testArcadeGame.updateGameState(0,4,1);
        testArcadeGame.updateGameState(1,4,0);
        testArcadeGame.updateGameState(2,4,3);
        testArcadeGame.updateGameState(3,4,0);
        testArcadeGame.updateGameState(4,4,1);

        System.out.println(testArcadeGame.createGameBoardString());

        String expected =   "|###|" + "\n" +
                            "|###|" + "\n" +
                            "|###|" + "\n" +
                            "| o |" + "\n" +
                            "| _ |" + "\n";

        assertEquals(expected, testArcadeGame.createGameBoardString());

    }

    @Test
    public void updateGameStateFromList_update111_expects1() {

        List<Long> testList = new ArrayList<>();
        testList.addAll(Arrays.asList(1L, 1L, 1L));

        testArcadeGame.updateGameStateFromList(testList);
        int expected = 1;

        assertEquals(expected,testArcadeGame.getGameStatePointData(1,1));

    }

    @Test
    public void updateGameStateFromList_update111update121_expects1expects1() {

        List<Long> testList = new ArrayList<>();
        testList.addAll(Arrays.asList(1L, 1L, 1L, 1L, 2L, 1L));

        testArcadeGame.updateGameStateFromList(testList);
        int expected1 = 1;
        int expected2 = 1;

        assertEquals(expected1,testArcadeGame.getGameStatePointData(1,1));
        assertEquals(expected2,testArcadeGame.getGameStatePointData(1,2));

    }

    @Test
    void updateGameState_updateWithIllegalList_expectsException() {

        List<Long> testList = new ArrayList<>();
        testList.addAll(Arrays.asList(1L, 1L, 1L, 1L, 2L, 1L, 1L));

        assertThrows(IllegalArgumentException.class, () -> testArcadeGame.updateGameStateFromList(testList));

    }




}