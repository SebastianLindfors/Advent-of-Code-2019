import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day14Tasks {

    public static void main(String[] args) {

        String fileName = "src\\main\\resources\\Day14Test3.txt";

        String reactionString = "";
        try {
            reactionString = loadDataFromDisk(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String[] reactions = reactionString.split("\n");

        List<Reaction> listOfAllReactions = new ArrayList<>();
        for (String reaction : reactions) {
            listOfAllReactions.add(new Reaction(reaction));
            System.out.println(new Reaction(reaction).descriptorString);
        }

        ReactionWeb fuelWeb = new ReactionWeb(listOfAllReactions);
        System.out.println("Fuel Reagent Level: " + fuelWeb.getReagentLevel("FUEL"));

        List<Map<String, Long>> reactionEconomy = fuelWeb.getOreCost("FUEL", 1);
        long orePerFuel = reactionEconomy.get(0).get("ORE");
        System.out.println("Ore needed to make one fuel: " + orePerFuel );

//        for (int i = 0; i < reactionEconomy.size(); i++) {
////            System.out.println("Reagent Level: " + i);
////            for (int j = 0; j < reactionEconomy.get(i).size(); j++) {
////                System.out.println("\t" + reactionEconomy.get(i).keySet().toArray()[j] + " : "
////                        +  reactionEconomy.get(i).get(reactionEconomy.get(i).keySet().toArray()[j]));
////            }
////        }
        long fuelProduced = 1;
        long oldFuelProduced = -1;
        List<Map<String, Long>> newRE = fuelWeb.getOreCost("FUEL", fuelProduced);
        long oreToFuel = newRE.get(0).get("ORE");;
        while (oreToFuel < Math.pow(10,12)) {
            //System.out.println("Ore Used: " + oreToFuel + " Fuel Produced: "  + fuelProduced);

            double percentage = oreToFuel / Math.pow(10,12);
            double newGuess = fuelProduced/percentage;
            //System.out.println(percentage + " " + newGuess);

            long newFuelProduced = (long) newGuess;
            if (fuelProduced == newFuelProduced) {
                break;
            }
            oldFuelProduced = fuelProduced;
            fuelProduced = newFuelProduced;
            newRE = fuelWeb.getOreCost("FUEL", fuelProduced);
            oreToFuel = newRE.get(0).get("ORE");
            //System.out.println("New Ore Used: " + oreToFuel + " New Fuel Produced: "  + fuelProduced);
        }

        System.out.println("For One Trillion Ore you get " + fuelProduced +  " units of Fuel");




    }



    public static String loadDataFromDisk(String fileName) throws IOException {

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }

    }




}
