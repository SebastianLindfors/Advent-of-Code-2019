import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReactionWeb {

    List<Reaction> listOfAllReactions;

    List<String> reagents = new ArrayList<>();

    Map<String, Integer> reagentLevels = new HashMap<>();



    public ReactionWeb ( List<Reaction> listOfAllReactions) {

        this.listOfAllReactions = listOfAllReactions;

        reagentLevels.put("ORE", 0);

        List<String> unassignedReagents = new ArrayList<>();

        for (Reaction reaction : this.listOfAllReactions) {
            reagents.add(reaction.getProduct());
            unassignedReagents.add(reaction.getProduct());
        }

        assignReagentLevel("FUEL");






    }


    public Map<String, Long> computeReagents(String product, long quantity) {

        Map<String, Long> output = new HashMap<>();

        for (Reaction reaction : listOfAllReactions) {
            if (reaction.getProduct().equals(product)) {

                long n;

                if (quantity % reaction.getProductQuantities() == 0) {
                    n = quantity / reaction.getProductQuantities();
                }
                else {
                    n = quantity / reaction.getProductQuantities() + 1;
                }
                //System.out.println("Quantity: " + quantity + ", Produced: " + reaction.getProductQuantities() + ", Production Factor:" + n);

                for (int i = 0; i < reaction.getReagents().length; i++) {
                    output.put(reaction.getReagents()[i], (long) reaction.getReagentQuantities()[i] * n);
                }
                return output;
            }
        }

        throw new IllegalArgumentException("Could not find product in reactions!");
    }

    public List<Map<String, Long>> getOreCost(String reagent, long primaryQuantity) {

        int primaryReagentLevel = getReagentLevel(reagent);
        List<Map<String, Long>> masterCostList = new ArrayList<>();
        for (int i = 0; i <= primaryReagentLevel; i++) {
            masterCostList.add(new HashMap<>());
        }


        masterCostList.get(primaryReagentLevel).put(reagent, primaryQuantity);
        for (int i = primaryReagentLevel; i > 0; i--) {
            for (String intermediateReagent : masterCostList.get(i).keySet()) {
                Map<String, Long> cost = computeReagents(intermediateReagent, masterCostList.get(i).get(intermediateReagent));
                for (String futureReagent : cost.keySet()) {
                    //System.out.println("FR: "  +futureReagent);
                    if (masterCostList.get(getReagentLevel(futureReagent)).containsKey(futureReagent)) {
                        masterCostList.get(getReagentLevel(futureReagent)).put(futureReagent, masterCostList.get(getReagentLevel(futureReagent)).get(futureReagent) + cost.get(futureReagent));
                    }
                    else {
                        masterCostList.get(getReagentLevel(futureReagent)).put(futureReagent, cost.get(futureReagent));
                    }
                }

            }

        }

        return masterCostList;
    }

    public int getReagentLevel(String reagent) {
        return reagentLevels.get(reagent);
    }

    private int assignReagentLevel(String reagent) {

        for (Reaction reaction : listOfAllReactions) {
           if (reaction.getProduct().equals(reagent)) {
               int higestReactionLevel = -1;
               for (String reactionReagent : reaction.getReagents()) {
                   int reactionReagentLevel;
                   if (reagentLevels.containsKey(reactionReagent)) {
                       reactionReagentLevel = reagentLevels.get(reactionReagent);
                   }
                   else {
                       reactionReagentLevel = assignReagentLevel(reactionReagent);
                   }
                   if (reactionReagentLevel > higestReactionLevel) {
                       higestReactionLevel = reactionReagentLevel;
                   }
               }
               reagentLevels.put(reagent, higestReactionLevel + 1);
           }
        }
        return reagentLevels.get(reagent);
    }


}
