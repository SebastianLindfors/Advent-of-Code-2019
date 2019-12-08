import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class Day8Tasks {

    public static void main(String[] args) {

        String fileName = "src\\main\\resources\\Day8.txt";
        String rawData = "";
        try {
            rawData = loadDataFromDisk(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        char[] pixelData = rawData.toCharArray();

        int rows = 6, columns = 25;
        int layers = pixelData.length / (rows * columns) ;

        int[][][] image = new int[layers][rows][columns];
        int[][] layerStats = new int[layers][3];

        Map<Character, Integer> countingMap = new HashMap<>();

        int lowestZeroCount = Integer.MAX_VALUE;
        int lowestZeroLayer = -1;

        int imageIndex = 0;
        for (int k = 0; k < layers; k++) {
            countingMap.put('0', 0);
            countingMap.put('1', 0);
            countingMap.put('2', 0);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    image[k][i][j] = Integer.parseInt(String.valueOf(pixelData[imageIndex]));
                    if (countingMap.containsKey(pixelData[imageIndex])) {
                        countingMap.put(pixelData[imageIndex], countingMap.get(pixelData[imageIndex]) + 1);
                    }
                    else {
                        System.out.println("Warning: Unknown pixel value: " + pixelData[imageIndex]);
                    }
                    imageIndex++;
                }
            }



            //System.out.println("Layer: " + k + " Zeroes Found: " + countingMap.get('0'));

            layerStats[k][0] = countingMap.get('0');
            layerStats[k][1] = countingMap.get('1');
            layerStats[k][2] = countingMap.get('2');

            if (layerStats[k][0] < lowestZeroCount) {
                lowestZeroCount = layerStats[k][0];
                lowestZeroLayer = k;
            }
        }

        System.out.println("Cheksum for image: " + layerStats[lowestZeroLayer][1] * layerStats[lowestZeroLayer][2]);

        String imageString = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < layers; k++) {
                    if (image[k][i][j] == 0) {
                        imageString += " ";
                        break;
                    }
                    else if (image[k][i][j] == 1) {
                        imageString += "#";
                        break;
                    }
                }

            }
            imageString += "\n";
        }
        System.out.println(imageString);




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


