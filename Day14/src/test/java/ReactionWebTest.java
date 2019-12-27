import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReactionWebTest {

    List<Reaction> testReactions = new ArrayList<>();

    @BeforeEach
    public void setUpTestReactions() {
        testReactions.add(new Reaction("10 ORE => 10 A"));
        testReactions.add(new Reaction("1 ORE => 1 B"));
        testReactions.add(new Reaction("7 A, 1 B => 1 C"));
        testReactions.add(new Reaction("7 A, 1 C => 1 D"));
        testReactions.add(new Reaction("7 A, 1 D => 1 E"));
        testReactions.add(new Reaction("7 A, 1 E => 1 FUEL"));
    }


    @Test
    void computeReagentsTest_computeFUELReagents_expects7A1E() {

       ReactionWeb testWeb = new ReactionWeb(testReactions);

       Map<String, Long> actual = testWeb.computeReagents("FUEL", 1);
       long expectedA = 7;
       long expectedE = 1;


       assertEquals(expectedA, (long) actual.get("A"));
       assertEquals(expectedE, (long) actual.get("E"));

    }

    @Test
    void computeReagentsTest_compute2EReagents_expects14A2D() {

        ReactionWeb testWeb = new ReactionWeb(testReactions);

        Map<String, Long> actual = testWeb.computeReagents("E", 2);
        long expectedA = 14;
        long expectedD = 2;


        assertEquals(expectedA, (long) actual.get("A"));
        assertEquals(expectedD, (long) actual.get("D"));

    }

    @Test
    void computeReagentsTest_computeIllegalReagents_expectsException() {

        ReactionWeb testWeb = new ReactionWeb(testReactions);

        assertThrows(IllegalArgumentException.class, () -> {
            testWeb.computeReagents("DOESNOTEXIST", 1);
        });
    }

    @Test
    public void assignReagentLevelTest_testORE_expect0() {

        ReactionWeb testWeb = new ReactionWeb(testReactions);

        int expectedORELevel = 0;
        int actual = testWeb.getReagentLevel("ORE");

        assertEquals(expectedORELevel, actual);

    }

    @Test
    public void assignReagentLevelTest_testA_expect1() {

        ReactionWeb testWeb = new ReactionWeb(testReactions);

        int expectedALevel = 1;
        int actual = testWeb.getReagentLevel("A");

        assertEquals(expectedALevel, actual);

    }

    @Test
    public void assignReagentLevelTest_testFUEL_expect5() {

        ReactionWeb testWeb = new ReactionWeb(testReactions);

        int expectedFUELLevel = 5;
        int actual = testWeb.getReagentLevel("FUEL");

        assertEquals(expectedFUELLevel, actual);

    }

    @Test
    public void computeOreCostTest_compute1A_expect10() {

        ReactionWeb testWeb = new ReactionWeb(testReactions);

        int expectedACost = 10;
        long actual = testWeb.getOreCost("A", 1);

        assertEquals(expectedACost, actual);

    }

    @Test
    public void computeOreCostTest_computeFUEL_expect31() {

        ReactionWeb testWeb = new ReactionWeb(testReactions);

        int expectedACost = 31;
        long actual = testWeb.getOreCost("FUEL" , 1);

        assertEquals(expectedACost, actual);

    }


}