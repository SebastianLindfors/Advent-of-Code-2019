import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ElfComputer {

    boolean haltExecution = false;

    Map<Long, Long> memory = new HashMap<>();
    private Map<Long,Long> backupMemory = new HashMap<>();

    private List<Long> systemOutput = new ArrayList<>();

    long[] originalState;
    long[] programMemory;

    int SystemIndex = 0;
    int relativeBase = 0;

    Scanner inputScanner = new Scanner(System.in);

    public ElfComputer(long[] program) {

        for (long i = 0; i < program.length; i++) {
            memory.put(i, program[(int) i]);
            backupMemory.put(i, program[(int) i]);
            //System.out.println("Building program memory: " + i + " : " +program[(int) i]);
        }

        inputScanner = new Scanner(System.in);
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

        for (long i = 0; i < program.length; i++) {
            memory.put(i, program[(int) i]);
            backupMemory.put(i, program[(int) i]);
        }

        inputScanner = new Scanner(System.in);
    }


    public List<Long> executeProgram() {

        while(!haltExecution) {

            int[] opCodeAndParamModes = determineOpcodeAndParameters();
            //System.out.println("Opcode: " + instructionsAndParameters[0]);
            switch (Math.toIntExact(opCodeAndParamModes[0])) {
                case 1: //addition
                    opCode1(opCodeAndParamModes[1], opCodeAndParamModes[2], opCodeAndParamModes[3]);
                    break;
                case 2: //multiplication
                    opCode2(opCodeAndParamModes[1], opCodeAndParamModes[2], opCodeAndParamModes[3]);
                    break;
                case 3: //input
                    if (!inputScanner.hasNextInt()) {
                        return systemOutput;
                    }
                    opCode3(opCodeAndParamModes[1]);
                    break;
                case 4: //output
                    opCode4(opCodeAndParamModes[1]);
                    break;
                case 5: //jump-if-true
                    opCode5(opCodeAndParamModes[1], opCodeAndParamModes[2]);
                    break;
                case 6: //Jump-if-false
                    opCode6(opCodeAndParamModes[1], opCodeAndParamModes[2]);
                    break;
                case 7: //Less Than
                    opCode7(opCodeAndParamModes[1], opCodeAndParamModes[2], opCodeAndParamModes[3]);
                    break;
                case 8: //Equals
                    opCode8(opCodeAndParamModes[1], opCodeAndParamModes[2], opCodeAndParamModes[3]);
                    break;
                case 9: //Change relative base
                    opCode9(opCodeAndParamModes[1]);
                    break;
                case 99:
                    haltExecution = true;
                    //System.out.println("END OF FILE!");
                    return systemOutput;
                default:
                    throw new IllegalArgumentException("Error: Opcode not Recognised");
            }
        }
        System.out.println("This code should not be reached");
        return systemOutput;
    }

    private int[] determineOpcodeAndParameters() {

        int opCode;
        int paramMode1, paramMode2, paramMode3;

        //System.out.println("Starting opcode and parameter analysis! System Index: " + SystemIndex + ": " + memory.get((long)SystemIndex));
        long instruction = memory.get((long) SystemIndex);
        //System.out.println("Instruction: " + instruction);

        opCode = Math.toIntExact(instruction % 100);
        instruction /= 100;
        //System.out.println("OpCode: " + opCode + " Remainder of instruction: " + instruction);

        List<Integer> paramModes = new ArrayList<>();
        while (instruction != 0) {
            paramModes.add(Math.toIntExact(instruction % 10));
            instruction /= 10;
        }

        switch (opCode) {
            case 1:
            case 2:
            case 7:
            case 8:
                if (paramModes.size() != 0) {
                    paramMode1 = paramModes.get(0);
                    paramModes.remove(0);
                } else {
                    paramMode1 = 0;
                }
                if (paramModes.size() != 0) {
                    paramMode2 = paramModes.get(0);
                    paramModes.remove(0);
                } else {
                    paramMode2 = 0;
                }
                if (paramModes.size() != 0) {
                    paramMode3 = paramModes.get(0);
                    paramModes.remove(0);
                } else {
                    paramMode3 = 1;
                }
                //System.out.println("OpCode: " + opCode + " PM1: " + paramMode1 + " PM2: " + paramMode2 + " PM3: " + paramMode3);
                return new int[]{opCode, paramMode1, paramMode2, paramMode3};
            case 5:
            case 6:
                if (paramModes.size() != 0) {
                    paramMode1 = paramModes.get(0);
                    paramModes.remove(0);
                } else {
                    paramMode1 = 0;
                }
                if (paramModes.size() != 0) {
                    paramMode2 = paramModes.get(0);
                    paramModes.remove(0);
                } else {
                    paramMode2 = 0;
                }
                //System.out.println("OpCode: " + opCode + " PM1: " + paramMode1 + " PM2: " + paramMode2);
                return new int[]{opCode, paramMode1, paramMode2};
            case 3:
            case 4:
            case 9:
                if (paramModes.size() != 0) {
                    paramMode1 = paramModes.get(0);
                    paramModes.remove(0);
                } else {
                    paramMode1 = 0;
                }
                //System.out.println("OpCode: " + opCode + " PM1: " + paramMode1);
                return new int[]{opCode, paramMode1};
            case 99:
                return new int[]{opCode};
            default:
                throw new IllegalArgumentException("Unknown Instruction: " + opCode);
        }
    }

    public void resetElfComputer() {
        memory = new HashMap<>(backupMemory);
        //System.out.println("Memory reset!");
        SystemIndex = 0;
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

    public void clearOutput() {
        systemOutput = new ArrayList<>();
    }

    public void directMemoryEdit(long position, long value) {
        memory.put(position, value);
    }

    public long[] getProgramMemory() {

        long[]output = new long[memory.keySet().size()];
        int memoryPointer = 0;
        for (long value: memory.keySet()) {
            output[memoryPointer++] = memory.get(value);
        }
        return output;
    }

    public boolean isExecutionHalted() {
        return haltExecution;
    }

    private void opCode1(int paramMode1, int paramMode2, int paramMode3) {
        long term1 = readValue(paramMode1, 1);
        long term2 = readValue(paramMode2, 2);

        long resultPointer = inputValue(paramMode3, 3);

        memory.put(resultPointer, term1 + term2);
        //System.out.println("Writing value " + term1 + " + " + term2 + " (" + (term1+term2) + ") to memory position " + resultPointer +".");

        SystemIndex += 4;
    }

    private void opCode2(int paramMode1, int paramMode2, int paramMode3) {
        long term1 = readValue(paramMode1, 1);
        long term2 = readValue(paramMode2, 2);

        long resultPointer = inputValue(paramMode3, 3);

        memory.put(resultPointer, term1 * term2);
        //System.out.println("Writing value " + term1 + " * " + term2 + " (" + (term1*term2) + ") to memory position " + resultPointer +".");

        SystemIndex += 4;
    }

    private void opCode3(int paramMode1) {

        long resultPointer = inputValue(paramMode1, 1);
        long inputValue = inputScanner.nextLong();

        memory.put(resultPointer, inputValue);
        //System.out.println("Writing input value " + inputValue + " to memory position " + resultPointer);

        SystemIndex += 2;
    }

    private void opCode4(int paramMode1) {

        long term1 = readValue(paramMode1, 1);

        systemOutput.add(term1);
        //System.out.println("Writing value " + term1 + " to memory to output");

        SystemIndex += 2;

    }

    private void opCode5(int paramMode1, int paramMode2) {

        long testValue = readValue(paramMode1, 1);
        long resultPointer = readValue(paramMode2, 2);
        //System.out.print("Does " + testValue + " equal zero? ");

        if (testValue != 0) {
            //System.out.println("No, jumping to Index: " + resultPointer);
            SystemIndex = Math.toIntExact(resultPointer);
        }
        else {
            SystemIndex += 3;
            //System.out.println("Yes, moving on normally.");
        }

    }

    private void opCode6(int paramMode1, int paramMode2) {

        long testValue = readValue(paramMode1, 1);
        long resultPointer = readValue(paramMode2, 2);
        //System.out.print("Does " + testValue + " equal zero? ");

        if (testValue == 0) {
            SystemIndex = Math.toIntExact(resultPointer);
            //System.out.println("Yes, jumping to Index: " + resultPointer);
        }
        else {
            SystemIndex += 3;
            //System.out.println("No, moving on normally.");
        }

    }

    private void opCode7(int paramMode1, int paramMode2, int paramMode3) {

        long term1 = readValue(paramMode1, 1);
        long term2 = readValue(paramMode2, 2);

        long resultPointer = inputValue(paramMode3, 3);
        //System.out.print("Is " + term1 + " less than " + term2 + "? ");

        if (term1 < term2) {
            memory.put(resultPointer, 1L);
            //System.out.println("Yes, writing 1 to memory position " + resultPointer + ".");
        }
        else {
            memory.put(resultPointer, 0L);
            //System.out.println("No, writing 0 to memory position " + resultPointer + ".");
        }

        SystemIndex += 4;

    }

    private void opCode8(int paramMode1, int paramMode2, int paramMode3) {

        long term1 = readValue(paramMode1, 1);
        long term2 = readValue(paramMode2, 2);

        long resultPointer = inputValue(paramMode3, 3);
        //System.out.print("Is " + term1 + " equal to " + term2 + "? ");

        if (term1 == term2) {
            memory.put(resultPointer, 1L);
            //System.out.println("Yes, writing 1 to memory position " + resultPointer + ".");
        }
        else {
            memory.put(resultPointer, 0L);
            //System.out.println("No, writing 0 to memory position " + resultPointer + ".");
        }

        SystemIndex += 4;

    }

    private void opCode9(int paramMode1) {

        long term1 = readValue(paramMode1, 1);

        relativeBase += term1;
        //System.out.println("Adding " + term1 + " to relative base.");

        SystemIndex += 2;

    }



    private long readValue(int paramMode, int offset) {
        long term;
        if (paramMode == 0) {
            if (memory.containsKey(memory.get((long) SystemIndex + offset))) {
                term = memory.get(memory.get((long) SystemIndex + offset));
            }
            else {
                term = 0;
            }

        }
        else if (paramMode == 1) {
            //System.out.print("Reading memory postion " + SystemIndex + offset);
            if (memory.containsKey((long) SystemIndex + offset)) {
                //System.out.println(" containing value: " + memory.get((long) SystemIndex + offset));
                term = memory.get((long) SystemIndex + offset);
            }
            else {
                term = 0;
                //System.out.println((" containing uninitiated value: 0"));
            }

        }
        else if(paramMode == 2) {
            if (memory.containsKey(memory.get((long) SystemIndex + offset) + relativeBase)) {
                term = memory.get(memory.get((long) SystemIndex + offset) + relativeBase);
            }
            else {
                term = 0;
            }

        }
        else {
            throw new IllegalArgumentException("Unknown Parameter Mode: " + paramMode);
        }
        return term;
    }

    private long inputValue(int paramMode, int offset) {
        long value;
        if (paramMode == 2) {
            value = memory.get((long) (SystemIndex + offset)) + relativeBase;
        }
        else {
            //System.out.println("Reading memory position: " + (SystemIndex + offset) + " containing " + memory.get((long) (SystemIndex + offset)));
            value = memory.get((long) (SystemIndex + offset));
        }
        return value;
    }

}
