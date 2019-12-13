import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        String[] moons = moonData.split("\n");

        List<Moon> allTheMoons = new ArrayList<>();
        for (String moonString : moons) {
            Pattern pattern = Pattern.compile("x=([^,]+),.*y=([^,]+),.*z=([^>]+)>");
            Matcher moonMatcher = pattern.matcher(moonString);
            if (moonMatcher.find()) {
                System.out.println(moonMatcher.group(1) + " " + moonMatcher.group(2) + " " + moonMatcher.group(3)  );
                allTheMoons.add(new Moon(Integer.parseInt(moonMatcher.group(1)),Integer.parseInt(moonMatcher.group(2)),Integer.parseInt(moonMatcher.group(3))));
            }
            else {
                System.out.println("NO MATCH!");
            }
        }

        for (Moon moon : allTheMoons) {
            for (Moon otherMoon : allTheMoons) {
                if (!(moon == otherMoon)) {
                    moon.addOtherMoon(otherMoon);
                }
            }
        }


        Boolean haltSystem = false;
        long timeUnits = 0;


        while (!haltSystem) {
            for (Moon moon : allTheMoons) {
                moon.computeNextVelocity();
            }
            for (Moon moon : allTheMoons) {
                moon.moveMoon();
            }
            double totalLunarEnergyInSystem = 0;
            for (Moon moon : allTheMoons) {
                totalLunarEnergyInSystem += moon.computeTotalLunarEnergy();
            }
            ;

            if (timeUnits++ == 10000000) {
                haltSystem = true;
            }






        }

        double totalLunarEnergyInSystem = 0;
        for (Moon moon : allTheMoons) {
            totalLunarEnergyInSystem += moon.computeTotalLunarEnergy();
        }
        System.out.println("The total amount of lunar energy in the system after 1000 time units is " + totalLunarEnergyInSystem);




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

    public static int calulation(int dv, int v0, int n) {

        return 5+(n*v0) + (n*(n+1)*dv)/2;
    }



}
