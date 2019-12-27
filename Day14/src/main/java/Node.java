import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

    long oreUsed;
    int nodeLevel;

    List<Reaction> possibleReactions = new ArrayList<>();
    List<Reaction> allReactions;

    Map<String, Long> resources = new HashMap<>();

    Node parrentNode;
    Reaction parentReaction;


    public Node(List<Reaction> listOfAllReactions) { //Constructs a root node

        oreUsed = 0;
        nodeLevel = 0;

        allReactions = listOfAllReactions;

        resources.put("ORE", Long.MAX_VALUE);
        resources.put("FUEL", 0L);
        for (Reaction reaction : listOfAllReactions) {
            for (String reactant : reaction.reagents) {
                if (!(resources.containsKey(reactant))) {
                    resources.put(reactant, 0L);
                }
            }
        }

        for (Reaction reaction : listOfAllReactions) {
            if (reaction.requiredReagentsPresent(resources)) {
                possibleReactions.add(reaction);
            }
        }

    }

    private Node (Node parentNode, Reaction parentReaction) {

        nodeLevel = parentNode.getNodeLevel() + 1;
        oreUsed = parentNode.oreUsed;

        this.parrentNode = parentNode;
        this.parentReaction = parentReaction;

        resources = copyMap(parentNode.resources);
        for (int i = 0; i < parentReaction.reagents.length; i++) {
            String currentReagent = parentReaction.reagents[i];
            int currentReagentCost = parentReaction.reagentQuantities[i];

            resources.put(currentReagent,resources.get(currentReagent) - currentReagentCost);
            if (currentReagent.equals("ORE")) {
                oreUsed += currentReagentCost;
            }
        }
        resources.put(parentReaction.product, resources.get(parentReaction.product) + parentReaction.productQuantities);

        allReactions = parentNode.allReactions;
        for (Reaction reaction : allReactions) {
            if (reaction.requiredReagentsPresent(resources)) {
                possibleReactions.add(reaction);
            }
        }




    }

    public Node createReactionChildNode(Reaction reaction) {
        return new Node(this, reaction);
    }

    public List<Node> createAllPossibleReactionChildren() {

        List<Node> outputList = new ArrayList<>();
        for (Reaction reaction: possibleReactions) {
            outputList.add(this.createReactionChildNode(reaction));
        }
        return outputList;
    }

    public List<Reaction> getPossibleReactions() {
        return possibleReactions;
    }

    public int getNodeLevel() {
        return nodeLevel;
    }

    public long getOreUsed() {
        return oreUsed;
    }

    public long getCurrentResourceLevel(String resource) {
        return resources.get(resource);
    }

    private Map<String, Long> copyMap (Map<String, Long> original) {

        Map<String, Long> copy = new HashMap<>();
        for (String key: original.keySet()) {
            copy.put(key, original.get(key));
        }
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }

        Node node = (Node) o;

        if (node.oreUsed == this.oreUsed && node.getNodeLevel() == this.getNodeLevel()) {
            if (node.resources.equals(this.resources) && node.possibleReactions.equals(this.possibleReactions)) {
                if (node.parrentNode.equals(this.parrentNode) && node.parentReaction.equals(this.parentReaction)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hashValue = 0;

        for (String key: resources.keySet()) {
            hashValue += key.hashCode()*resources.get(key)+7;
        }
        hashValue += parentReaction.hashCode()*1000;

        return hashValue;

    }
}
