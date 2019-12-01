import java.io.*;
import java.util.stream.IntStream;

public class FuelUseCounter {

    public static void main(String[] args) {
        String rawData = "";
        try {
            rawData = loadDataFromDisk("Day1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = rawData.split("\n");
        int[] moduleWeights = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            moduleWeights[i] = Integer.parseInt(lines[i].strip());
        }
        int result = sumOfFuelNeededForModules(moduleWeights);
        int result2 = sumOfFuelNeededForModulesWithFuel(moduleWeights);
        System.out.println("Fuel Needed: " + result);
        System.out.println("Fuel Needed (considering weight of fuel): " + result2);


    }



    static int computeFuelNeededPerModule(int moduleWeight) {
        return (moduleWeight/3) - 2;
    }

    static int computeFuelNeededPerModuleWithFuel (int moduleWeight) {

        int totalFuel = 0;
        int additionalWeightAdded = moduleWeight;
        do {
            additionalWeightAdded = computeFuelNeededPerModule(additionalWeightAdded);

            totalFuel += additionalWeightAdded;
        } while (additionalWeightAdded > 8);

        return totalFuel;
    }

    static int sumOfFuelNeededForModules(int[] listOfModuleWeights) {
        return IntStream.of(listOfModuleWeights)
                .map(FuelUseCounter::computeFuelNeededPerModule)
                .sum();
    }

    static int sumOfFuelNeededForModulesWithFuel(int[] listOfModuleWeights) {
        return IntStream.of(listOfModuleWeights)
                .map(FuelUseCounter::computeFuelNeededPerModuleWithFuel)
                .sum();
    }

    public static String loadDataFromDisk(String fileName) throws IOException {

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }

    }

}


