import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day6 {

    public static void main(String[] args) {

        String rawData = "";
        try {
            rawData = loadDataFromDisk("src\\main\\resources\\Day6.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] orbitData = rawData.split("\n");
        System.out.println(orbitData.length);

        Map<String,OMC> listOfOrbits = new HashMap<String,OMC>();
        for (String orbitInfo : orbitData) {

            String[] OMCNames = orbitInfo.split("\\)");
            String orbiteeName = OMCNames[0];
            String orbiterName = OMCNames[1];

            if (listOfOrbits.containsKey(orbiteeName) && listOfOrbits.containsKey(orbiterName)) {
                //Empty but more readable for now.
            }
            else if (listOfOrbits.containsKey(orbiteeName)) {
                listOfOrbits.put(orbiterName, new OMC(orbiterName));
            }
            else if (listOfOrbits.containsKey(orbiterName)) {
                listOfOrbits.put(orbiteeName, new OMC(orbiteeName));

            }
            else {
                listOfOrbits.put(orbiterName, new OMC(orbiterName));
                listOfOrbits.put(orbiteeName, new OMC(orbiteeName));
            }
            listOfOrbits.get(orbiteeName).addOrbitingBody(listOfOrbits.get(orbiterName));
            listOfOrbits.get(orbiterName).setOrbits(listOfOrbits.get(orbiteeName));

        }
        int totalOrbits = 0;
        for (String name : listOfOrbits.keySet()) {
              totalOrbits += listOfOrbits.get(name).computeOrbitalDepth();
        }

        System.out.println("The total number of direct and indirect orbits in the map data is: " + totalOrbits);

        //-------------------------------Part 2 --------------------------------------------------------------//

        OMC myOrbit = listOfOrbits.get("YOU").orbits;
        OMC santasOrbit = listOfOrbits.get("SAN").orbits;

        int transfers = 0;
        int minimumTransfersNeeded = -1;
        Map<OMC, Integer> visited = new HashMap<>();
        visited.put(myOrbit, transfers);
        visited.put(santasOrbit, transfers);

        while(true) {
            if (visited.containsKey(myOrbit.orbits)) {
                minimumTransfersNeeded = visited.get(myOrbit) + visited.get(myOrbit.orbits) + 1;
                break;
            }
            else if (visited.containsKey(santasOrbit.orbits)) {
                minimumTransfersNeeded = visited.get(santasOrbit) + visited.get(santasOrbit.orbits) + 1;
                break;
            }
            else {
                myOrbit = myOrbit.orbits;
                santasOrbit = santasOrbit.orbits;
                transfers++;

                visited.put(myOrbit, transfers);
                visited.put(santasOrbit, transfers);

            }
        }
        System.out.println("The minimum number of transfers to reach the same orbit as santa is: " + minimumTransfersNeeded);




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
