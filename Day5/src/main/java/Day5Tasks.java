

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day5Tasks {

    public static void main(String[] args) {

        String rawData = "";
        try {
            rawData = loadDataFromDisk("src\\main\\resources\\Day5.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] parsedData = rawData.split(",");
        int[] completedData = new int[parsedData.length];
        for (int i = 0; i < parsedData.length; i++) {
            completedData[i] = Integer.parseInt(parsedData[i].strip());
        }

        ElfComputer partOneComputer = new ElfComputer(completedData);

        int[] result = partOneComputer.executeProgram();

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
