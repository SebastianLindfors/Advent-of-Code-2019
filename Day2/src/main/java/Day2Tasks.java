import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2Tasks {

    public static void main(String[] args) {

        String rawData = "";

        try {
            rawData = loadDataFromDisk("src\\main\\resources\\Day2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] parsedData = rawData.split(",");
        int[] completedData = new int[parsedData.length];
        for (int i = 0; i < parsedData.length; i++) {
            completedData[i] = Integer.parseInt(parsedData[i].strip());
        }

        int[] result = ElfComputer.executeProgram(completedData);
        System.out.println("Answer (part 1): " + result[0]);


        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                completedData[1] = i;
                completedData[2] = j;
                try {
                    result = ElfComputer.executeProgram(completedData);
                    if (result[0] == 19690720) {
                        System.out.println("Answer (part 2): Noun:" + i + ", Verb: " + j);
                        break;
                    }
                }
                catch (IllegalArgumentException iae) {
                    System.out.println("Crash: " + i + ", " + j);
                    continue;
                }
            }
        }


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
