import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ReactionTest {

    Map<String, Long> resources = new HashMap<>();

    @Test
    public void ReactionConstructorTest1_assertCorrectProducts() {

    Reaction testReaction = new Reaction("5 RSTBJ => 5 QKBQL");

    String expectedProduct = "QKBQL";
    int expectedProductQuantity = 5;

    assertEquals(expectedProduct, testReaction.getProduct());
    assertEquals(expectedProductQuantity, testReaction.getProductQuantities());

    }

    @Test
    public void ReactionConstructorTest1_assertCorrectReagents() {

        Reaction testReaction = new Reaction("5 RSTBJ => 5 QKBQL");

        String[] expectedReagents = new String[] {"RSTBJ"};
        int[] expectedReagentQuantities = new int[] {5};

        assertArrayEquals(expectedReagents, testReaction.getReagents());
        assertArrayEquals(expectedReagentQuantities, testReaction.getReagentQuantities());

    }

    @Test
    public void ReactionConstructorTest2_assertCorrectProduct() {

        Reaction testReaction = new Reaction("1 GLVHT, 1 PBCT, 5 ZWKGV, 1 QSVJ, 2 FWLTD, 3 CNVPB, 1 QGNL => 8 RNLTX");

        String expectedProduct = "RNLTX";
        int expectedProductQuantity = 8;

        assertEquals(expectedProduct, testReaction.getProduct());
        assertEquals(expectedProductQuantity, testReaction.getProductQuantities());

    }

    @Test
    public void ReactionConstructorTest2_assertCorrectReagent() {

        Reaction testReaction = new Reaction("1 GLVHT, 1 PBCT, 5 ZWKGV, 1 QSVJ, 2 FWLTD, 3 CNVPB, 1 QGNL => 8 RNLTX");

        String[] expectedReagents = new String[] {"GLVHT", "PBCT", "ZWKGV", "QSVJ", "FWLTD", "CNVPB", "QGNL"};
        int[] expectedReagentQuantities = new int[] {1, 1, 5, 1, 2, 3, 1};

        assertArrayEquals(expectedReagents, testReaction.getReagents());
        assertArrayEquals(expectedReagentQuantities, testReaction.getReagentQuantities());

    }

    @Test
    public void requiredReagentsPresentTest_resourcesAboveLimit_expectTrue() {

        Reaction testReaction = new Reaction("1 GLVHT, 1 PBCT, 5 ZWKGV, 1 QSVJ, 2 FWLTD, 3 CNVPB, 1 QGNL => 8 RNLTX");

        resources.put("GLVHT", 10L);
        resources.put("PBCT", 10L);
        resources.put("ZWKGV", 10L);
        resources.put("QSVJ", 10L);
        resources.put("FWLTD", 10L);
        resources.put("CNVPB", 10L);
        resources.put("QGNL", 10L);

        assertTrue(testReaction.requiredReagentsPresent(resources));

    }

    @Test
    public void requiredReagentsPresentTest_resourcesExactlyEqual_expectTrue() {

        Reaction testReaction = new Reaction("1 GLVHT, 1 PBCT, 5 ZWKGV, 1 QSVJ, 2 FWLTD, 3 CNVPB, 1 QGNL => 8 RNLTX");

        resources.put("GLVHT", 1L);
        resources.put("PBCT", 1L);
        resources.put("ZWKGV", 5L);
        resources.put("QSVJ", 1L);
        resources.put("FWLTD", 2L);
        resources.put("CNVPB", 3L);
        resources.put("QGNL", 1L);

        assertTrue(testReaction.requiredReagentsPresent(resources));

    }

    @Test
    public void requiredReagentsPresentTest_resourcesBelowLimit_expectFalse() {

        Reaction testReaction = new Reaction("1 GLVHT, 1 PBCT, 5 ZWKGV, 1 QSVJ, 2 FWLTD, 3 CNVPB, 1 QGNL => 8 RNLTX");

        resources.put("GLVHT", 1L);
        resources.put("PBCT", 1L);
        resources.put("ZWKGV", 1L);
        resources.put("QSVJ", 1L);
        resources.put("FWLTD", 1L);
        resources.put("CNVPB", 1L);
        resources.put("QGNL", 1L);

        assertFalse(testReaction.requiredReagentsPresent(resources));

    }

}