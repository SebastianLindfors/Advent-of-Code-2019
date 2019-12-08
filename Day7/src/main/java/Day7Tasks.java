import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day7Tasks {

    public static void main(String[] args) {

        String fileName = "src\\main\\resources\\Day7.txt";

        ElfComputer amp1 = new ElfComputer(fileName);
        ElfComputer amp2 = new ElfComputer(fileName);
        ElfComputer amp3 = new ElfComputer(fileName);
        ElfComputer amp4 = new ElfComputer(fileName);
        ElfComputer amp5 = new ElfComputer(fileName);

        ElfComputer[] ampArray = new ElfComputer[] {amp1, amp2, amp3, amp4, amp5};

        String[] input = new String[5];

        int [] possiblePhaseSettings = {0,1,2,3,4};
        int maximumOutput = Integer.MIN_VALUE;
        int output = 0;
        for (int[] phaseSettings : getAllPermutations(possiblePhaseSettings)) {
            input[0] = phaseSettings[0] + System.lineSeparator() + "0";
            ampArray[0].setUpInput(input[0]);
            for (int i = 1; i < 5; i++) {
                input[i] = phaseSettings[i] + System.lineSeparator() + ampArray[i - 1].executeProgram().get(0).toString();
                ampArray[i].setUpInput(input[i]);
                ampArray[i - 1].resetElfComputer();
            }
            output = ampArray[4].executeProgram().get(0);
            ampArray[4].resetElfComputer();

            if (output > maximumOutput) {
                maximumOutput = output;
            }
        }
        System.out.println("The maximum possible output without feedback loop is " + maximumOutput);

        possiblePhaseSettings = new int[] {5,6,7,8,9};
        maximumOutput = Integer.MIN_VALUE;
        output = 0;
        for (int[] phaseSettings : getAllPermutations(possiblePhaseSettings)) {
            for (int i = 0; i < 5; i++) {
                input[i] = phaseSettings[i] + "";
                ampArray[i].setUpInput(input[i]);
                ampArray[i].executeProgram();
            }
            List<Integer> amp5Out= new ArrayList<>();
            amp5Out.add(0);
            while (!ampArray[4].isExecutionHalted()) {
                input[0] = amp5Out.get(0).toString();
                ampArray[0].setUpInput(input[0]);
                for (int i = 1; i < 5; i++) {
                    input[i] = ampArray[i-1].executeProgram().get(0).toString();
                    ampArray[i].setUpInput(input[i]);
                }
                amp5Out = ampArray[4].executeProgram();
            }

            for (int i = 0; i < 5; i++) {
                ampArray[i].resetElfComputer();
            }
            output = amp5Out.get(0);

            if (output > maximumOutput) {
                maximumOutput = output;
            }
        }
        System.out.println("The maximum possible output with a feedback loop is " + maximumOutput);

    }






    public static int[][] getAllPermutations(int[] elements) {

        int n = elements.length;
        int[][] output = new int[factorial(n)][n];
        int outputIndex = 0;


        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }

        output[outputIndex++] = arrayCopy(elements);

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements, i % 2 == 0 ?  0: indexes[i], i);
                output[outputIndex++] = arrayCopy(elements);
                indexes[i]++;
                i = 0;
            }
            else {
                indexes[i] = 0;
                i++;
            }
        }
        return output;
    }


    public static int factorial(int n) {
        return IntStream.rangeClosed(1, n)
                .reduce(1, (int x, int y) -> x * y);
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static int[] arrayCopy(int[] toCopy) {

        int[] output = new int[toCopy.length];
        System.arraycopy(toCopy,0,output,0,toCopy.length);
        return output;

    }




}


