import javafx.geometry.Point3D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12Tasks {

    public static void main(String[] args) {

        String fileName = "src\\main\\resources\\Day12.txt";

        String moonData = "";
        try {
            moonData = loadDataFromDisk(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        String[] moonNames = {"Io", "Europa", "Ganymede", "Callisto"};
        int numberOfMoons = 0;
        String[] moons = moonData.split("\n");

        List<Moon> allTheMoons = new ArrayList<>();
        for (String moonString : moons) {
            Pattern pattern = Pattern.compile("x=([^,]+),.*y=([^,]+),.*z=([^>]+)>");
            Matcher moonMatcher = pattern.matcher(moonString);
            if (moonMatcher.find()) {
                //System.out.println(moonMatcher.group(1) + " " + moonMatcher.group(2) + " " + moonMatcher.group(3)  );
                allTheMoons.add(new Moon(moonNames[numberOfMoons++], Integer.parseInt(moonMatcher.group(1)), Integer.parseInt(moonMatcher.group(2)), Integer.parseInt(moonMatcher.group(3))));
            }
            else {
                System.out.println("NO MATCH!");
            }
        }

        Map<String, Integer> moonInStartPosition = new HashMap<>();
        moonInStartPosition.put("X", 0);
        moonInStartPosition.put("Y", 0);
        moonInStartPosition.put("Z", 0);

        for (Moon moon : allTheMoons) {
            for (Moon otherMoon : allTheMoons) {
                if (!(moon == otherMoon)) {
                    moon.addOtherMoon(otherMoon);
                }
            }
        }

        long timeUnits = 0;

        boolean xCycleFound = false;
        boolean yCycleFound = false;
        boolean zCycleFound = false;

        long xCycle = -1;
        long yCycle = -1;
        long zCycle = -1;

        while (!(xCycleFound) || !(yCycleFound) || !(zCycleFound) || timeUnits < 1000) {

            timeUnits++;

            for (Moon moon : allTheMoons) {
                moon.computeNextVelocity();

            }
            for (Moon moon : allTheMoons) {
                moon.moveMoon();
            }

            for (Moon moon : allTheMoons) {
                if (moon.getVelocity().getX() == 0) {
                    if (moon.getPosition().getX() == moon.getStartingPosition().getX()) {
                        moonInStartPosition.put("X",moonInStartPosition.get("X") + 1);
                    }
                }
                if (moon.getVelocity().getY() == 0) {
                    if (moon.getPosition().getY() == moon.getStartingPosition().getY()) {
                        moonInStartPosition.put("Y",moonInStartPosition.get("Y") + 1);
                    }
                }
                if (moon.getVelocity().getZ() == 0) {
                    if (moon.getPosition().getZ() == moon.getStartingPosition().getZ()) {
                        moonInStartPosition.put("Z",moonInStartPosition.get("Z") + 1);
                    }
                }
            }

            if (moonInStartPosition.get("X") == 4 && !(xCycleFound)) {
                xCycle = timeUnits;
                xCycleFound = true;
            }
            else {
                moonInStartPosition.put("X", 0);
            }
            if (moonInStartPosition.get("Y") == 4 && !(yCycleFound)) {
                yCycle = timeUnits;
                yCycleFound = true;
            }
            else {
                moonInStartPosition.put("Y", 0);
            }
            if (moonInStartPosition.get("Z") == 4 && !(zCycleFound)) {
                zCycle = timeUnits;
                zCycleFound = true;
            }
            else {
                moonInStartPosition.put("Z", 0);
            }

//            if (timeUnits % 10 == 0 && timeUnits < 101) {
//                System.out.println("Time:" + timeUnits);
//                for (Moon moon : allTheMoons) {
//                    System.out.println(moon.getName() + " " + moon.getPosition() + moon.getVelocity());
//                }
//            }

            if (timeUnits == 1000) {
                double totalLunarEnergyInSystem = 0;
                for (Moon moon : allTheMoons) {
                    totalLunarEnergyInSystem += moon.computeTotalLunarEnergy();
                }
                System.out.println("The total amount of lunar energy in the system after 1000 time units is " + totalLunarEnergyInSystem);
            }

        }

        Map<Long, Integer> xPrimes = primeFactors(xCycle);
        Map<Long, Integer> yPrimes = primeFactors(yCycle);
        Map<Long, Integer> zPrimes = primeFactors(zCycle);
        Map<Long, Integer> commonPrimes = new HashMap<>();

        for (long key: xPrimes.keySet()) {
            //System.out.println(key + " : " + xPrimes.get(key));
            if (commonPrimes.containsKey(key)) {
                if (xPrimes.get(key) > commonPrimes.get(key)) {
                    commonPrimes.put(key, xPrimes.get(key));
                }
            }
            else {
                commonPrimes.put(key, xPrimes.get(key));
            }
        }
        for (long key: yPrimes.keySet()) {
           // System.out.println(key + " : " + yPrimes.get(key));
            if (commonPrimes.containsKey(key)) {
                if (yPrimes.get(key) > commonPrimes.get(key)) {
                    commonPrimes.put(key, yPrimes.get(key));
                }
            }
            else {
                commonPrimes.put(key, yPrimes.get(key));
            }
        }
        for (long key: zPrimes.keySet()) {
            //System.out.println(key + " : " + zPrimes.get(key));
            if (commonPrimes.containsKey(key)) {
                if (zPrimes.get(key) > commonPrimes.get(key)) {
                    commonPrimes.put(key, zPrimes.get(key));
                }
            }
            else {
                commonPrimes.put(key, zPrimes.get(key));
            }
        }

        long totalproduct = 1;
        for (long key: commonPrimes.keySet()) {
            //System.out.println(key + " : " + commonPrimes.get(key));
            totalproduct *= Math.pow(key,commonPrimes.get(key));
        }
        System.out.println("Time units until system repeats: " + totalproduct);






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

    private static Map<Long, Integer> primeFactors(long n) {

        Map<Long, Integer> output = new HashMap<>();
        for(long i = 2; i < n; i++) {
            while(n % i == 0) {
                if (output.containsKey(i)) {
                    output.put(i,output.get(i) + 1);
                }
                else {
                    output.put(i,1);
                }
                n = n/i;
            }
        }
        if(n >= 2) {
            if (output.containsKey(n)) {
                output.put(n,output.get(n) + 1);
            }
            else {
                output.put(n,1);
            }
        }
        return output;
    }

}



