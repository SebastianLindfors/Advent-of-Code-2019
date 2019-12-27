import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

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
    public void rootNodeConstructorTest_createRootNode_testNodeLevelTestOreUsed_expect00() {

        Node testRootNode = new Node(testReactions);
        int expectedNodeLevel = 0;
        long expectedOreUsed = 0L;

        assertEquals(expectedNodeLevel, testRootNode.getNodeLevel());
        assertEquals(expectedOreUsed, testRootNode.getOreUsed());
    }

    @Test
    public void rootNodeConstructorTest_createRootNode_testPossibleReactions_expectListSize2() {

        Node testRootNode = new Node(testReactions);

        List<Reaction> expectedList = new ArrayList<>();
        expectedList.add(new Reaction("10 ORE => 10 A"));
        expectedList.add(new Reaction("1 ORE => 1 B"));

        assertEquals(expectedList, testRootNode.getPossibleReactions());

    }


    @Test
    void createReactionChildNodeTest_Reaction10ORETo10A_expectNodeLevel1_expect10reUsed() {

        Node testRootNode = new Node(testReactions);

        Node testChildNode = testRootNode.createReactionChildNode(new Reaction("10 ORE => 10 A"));

        long expectedOreUsed = 10L;
        int expectedNodeLevel = 1;

        assertEquals(expectedOreUsed, testChildNode.getOreUsed());
        assertEquals(expectedNodeLevel, testChildNode.getNodeLevel());

    }

    @Test
    void createReactionChildNodeTest_Reaction10ORETo10A_expect10A() {

        Node testRootNode = new Node(testReactions);

        Node testChildNode = testRootNode.createReactionChildNode(new Reaction("10 ORE => 10 A"));

        long expectedALevel = 10L;

        assertEquals(expectedALevel, testChildNode.getCurrentResourceLevel("A"));

    }

    @Test
    void createAllPossibleReactionChildrenTest_createAllRootChildNOdes_expectListSize2() {

        Node testRootNode = new Node(testReactions);

        Node testChildNode1 = testRootNode.createReactionChildNode(new Reaction("10 ORE => 10 A"));
        Node testChildNode2 = testRootNode.createReactionChildNode(new Reaction("1 ORE => 1 B"));
        List<Node> expectedNodeList = new ArrayList<>();
        expectedNodeList.add(testChildNode1);
        expectedNodeList.add(testChildNode2);

        assertEquals(expectedNodeList, testRootNode.createAllPossibleReactionChildren());

    }

    @Test
    void getNodeLevelTest_createChildNodeCreategrandChildNoide_expected0expect1expect2() {

        Node testRootNode = new Node(testReactions);
        Node testChildNode1 = testRootNode.createReactionChildNode(new Reaction("10 ORE => 10 A"));
        Node testChildNode2 = testChildNode1.createReactionChildNode(new Reaction("10 ORE => 10 A"));

        int expected1 = 0;
        int expected2 = 1;
        int expected3 = 2;

        assertEquals(expected1, testRootNode.getNodeLevel());
        assertEquals(expected2, testChildNode1.getNodeLevel());
        assertEquals(expected3, testChildNode2.getNodeLevel());


    }
}