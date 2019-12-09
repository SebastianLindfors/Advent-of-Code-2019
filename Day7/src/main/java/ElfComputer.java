import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ElfComputer {

    boolean haltExecution = false;

    private Map<Long,Long> memoryBank = new HashMap<>()
            ;

    long[] originalState;
    long[] programMemory;

    long SystemIndex = 0;
    long relativeBase = 0;

    Scanner inputScanner = new Scanner(System.in);

    public ElfComputer(int[] program) {

        programMemory = new long[program.length];
        originalState = new long[program.length];
        System.arraycopy(program,0, programMemory,0, program.length);
        System.arraycopy(program,0,originalState,0, program.length);

        inputScanner = new Scanner(System.in);
    }

    public ElfComputer(int[] program, ByteArrayInputStream in) {

        programMemory = new long[program.length];
        originalState = new long[program.length];
        System.arraycopy(program,0, programMemory,0, program.length);
        System.arraycopy(program,0,originalState,0, program.length);

        inputScanner = new Scanner(in);
    }

    public ElfComputer(String fileName, ByteArrayInputStream in) {

        String rawData = "";
        try {
            rawData = loadDataFromDisk(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] parsedData = rawData.split(",");
        int[] program = new int[parsedData.length];
        for (int i = 0; i < parsedData.length; i++) {
            program[i] = Integer.parseInt(parsedData[i].strip());
        }

        programMemory = new long[program.length];
        originalState = new long[program.length];
        System.arraycopy(program,0, programMemory,0, program.length);
        System.arraycopy(program,0,originalState,0, program.length);

        inputScanner = new Scanner(in);
    }

    public ElfComputer(String fileName) {

        String rawData = "";
        try {
            rawData = loadDataFromDisk(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] parsedData = rawData.split(",");
        long[] program = new long[parsedData.length];
        for (int i = 0; i < parsedData.length; i++) {
            program[i] = Long.parseLong(parsedData[i].strip());
        }

        programMemory = new long[program.length];
        originalState = new long[program.length];
        System.arraycopy(program,0, programMemory,0, program.length);
        System.arraycopy(program,0,originalState,0, program.length);

        inputScanner = new Scanner(System.in);
    }


    public List<Long> executeProgram() {
        List<Long> output = new ArrayList<>();

        while(!haltExecution) {
            long[] instructionsAndParameters = determineOpcodeAndParameters((int) SystemIndex);

            //System.out.println("Opcode: " + instructionsAndParameters[0]);
            switch ((int) instructionsAndParameters[0]) {
                case 1: //addition
                    long sum =  instructionsAndParameters[1] + instructionsAndParameters[2];
                    writeDataToMemory(instructionsAndParameters[3], sum);
                    SystemIndex += 4;
                    break;
                case 2: //multiplication
                    long product =  instructionsAndParameters[1] * instructionsAndParameters[2];
                    writeDataToMemory(instructionsAndParameters[3], product);
                    SystemIndex += 4;
                    break;
                case 3: //input
                    if (!inputScanner.hasNextInt()) {
                        return output;
                    }
                    writeDataToMemory(instructionsAndParameters[1], inputScanner.nextInt());

                    SystemIndex += 2;
                    break;
                case 4: //output
                    output.add(instructionsAndParameters[1]);
                    SystemIndex += 2;
                    break;
                case 5: //jump-if-true
                    if (instructionsAndParameters[1] != 0) {
                        SystemIndex = instructionsAndParameters[2];
                    }
                    else {
                        SystemIndex += 3;
                    }
                    break;
                case 6: //Jump-if-false
                    if (instructionsAndParameters[1] == 0) {
                        SystemIndex = instructionsAndParameters[2];
                    }
                    else {
                        SystemIndex += 3;
                    }
                    break;
                case 7: //Less Than
                    if (instructionsAndParameters[1] < instructionsAndParameters[2]) {
                        writeDataToMemory(instructionsAndParameters[3], 1);
                    }
                    else {
                        writeDataToMemory(instructionsAndParameters[3], 0);
                    }
                    SystemIndex += 4;
                    break;
                case 8: //Equals
                    if (instructionsAndParameters[1] == instructionsAndParameters[2]) {
                        writeDataToMemory(instructionsAndParameters[3], 1);
                    }
                    else {
                        writeDataToMemory(instructionsAndParameters[3], 0);
                    }
                    SystemIndex += 4;
                    break;
                case 9: //Change relative base
                    relativeBase += instructionsAndParameters[1];
                    System.out.println("Relative base now: " + relativeBase);
                    SystemIndex += 2;
                    break;
                case 99:
                    haltExecution = true;
                    continue;
                default:
                    throw new IllegalArgumentException("Error: Opcode not Recognised");
            }
        }
        return output;
    }

    private long[] determineOpcodeAndParameters(int index) {

        long instruction = programMemory[index];

        long[] parameters = {0,0,0,0,0};
        parameters[0] = instruction % 100;
        if (parameters[0] == 99) {
            return parameters;
        }
        int parameterNumber = -1;

        switch ((int) parameters[0]) {
            case 1:
            case 2:
            case 7:
            case 8:
                parameterNumber = 3;
                break;
            case 3:
            case 4:
            case 9:
                parameterNumber = 1;
                break;
            case 5:
            case 6:
                parameterNumber = 2;
                break;
            case 99:
                parameterNumber = 0;
                break;
            default:
                throw new IllegalArgumentException("Unknown Instruction: " + parameters[0]);
        }
        System.out.println("Instruction: " + instruction + " Opcode: " + parameters[0] + " Number of arguments: " + parameterNumber);

        instruction /= 100;
        for (int j = 1; j <= parameterNumber; j++) {
            parameters[j] = instruction % 10;
            //System.out.println(j +": " + parameters[j]);
            instruction /= 10;
        }

        if (parameterNumber == 3) {
            if (parameters[3] == 0) {
                parameters[3] = 1;            }
        }
        System.out.println("Parameter Modes: ");
        for (int j = 1; j <= parameterNumber; j++) {
            System.out.println(j + ": " +parameters[j]);
        }


        System.out.println("Parameters:");
        for (int i = 1; i <= parameterNumber; i++) {
            System.out.print(i + ": ");

            if (parameters[i] == 0) {
                parameters[i] = readDataFromMemory(programMemory[index + i]);
                System.out.println(parameters[i]);
            }
            else if (parameters[i] == 1) {
                parameters[i] = readDataFromMemory(index + i);
                System.out.println(parameters[i]);
            }
            else if (parameters[i] == 2) {
                parameters[i] = readDataFromMemory( programMemory[index + i] + relativeBase);
            }
            else {
                throw new IllegalArgumentException("Invalid Instructions Detected");
            }
        }
        return parameters;
    }

    public void resetElfComputer() {
        System.arraycopy(originalState,0, programMemory,0, programMemory.length);
        SystemIndex = 0;
        relativeBase = 0;
        haltExecution = false;
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

    public void setUpInput(String newInput) {
        inputScanner = new Scanner(newInput);
    }

    public long[] getProgramMemory() {
        return programMemory;
    }

    public boolean isExecutionHalted() {
        return haltExecution;
    }

    public void writeDataToMemory(long position, long data) {

        if (position >= programMemory.length) {
            memoryBank.put(position, data);
            System.out.println("Writing value " + data + " to databank, position: " + position);
        }
        else {
            programMemory[(int) position] = data;
            System.out.println("Writing value " + data + " to program memory, position: " + position);
        }

    }

    public long readDataFromMemory(long position) {

        if (position >= programMemory.length) {
            if (memoryBank.containsKey(position)) {
                System.out.println("Reading from databank, position: " + position);
                return memoryBank.get(position);
            }
            else { return 0; }
        }
        else {
            System.out.println("Reading from program memory, position: " + position);
            return programMemory[(int) position];
        }

    }
}
