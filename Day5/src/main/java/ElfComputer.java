import java.util.Scanner;

public class ElfComputer {

    int[] originalState;
    int[] computerMemory;

    Scanner inputScanner = new Scanner(System.in);

    public ElfComputer(int[] program) {

        computerMemory = new int[program.length];
        originalState = new int[program.length];
        System.arraycopy(program,0,computerMemory,0, program.length);
        System.arraycopy(program,0,originalState,0, program.length);
    }


    public int[] executeProgram() {

        boolean haltExecution = false;
        int index = 0;

        while(!haltExecution) {
            int[] instructionsAndParameters = determineOpcodeAndParameters(index);

            //System.out.println("Opcode: " + instructionsAndParameters[0]);
            switch (instructionsAndParameters[0]) {
                case 1: //addition
                    computerMemory[instructionsAndParameters[3]] = instructionsAndParameters[1] + instructionsAndParameters[2];
                    index += 4;
                    break;
                case 2: //multiplication
                    computerMemory[instructionsAndParameters[3]] = instructionsAndParameters[1] * instructionsAndParameters[2];
                    index += 4;
                    break;
                case 3: //input
                    //System.out.println();
                    //System.out.print("The Elfputor needs your input: ");
                    computerMemory[instructionsAndParameters[1]] = inputScanner.nextInt();
                    index += 2;
                    break;
                case 4: //output
                    System.out.print(instructionsAndParameters[1]);
                    index += 2;
                    break;
                case 5: //jump-if-true
                    if (instructionsAndParameters[1] != 0) {
                        index = instructionsAndParameters[2];
                    }
                    else {
                        index += 3;
                    }
                    break;
                case 6: //Jump-if-false
                    if (instructionsAndParameters[1] == 0) {
                        index = instructionsAndParameters[2];
                    }
                    else {
                        index += 3;
                    }
                    break;
                case 7: //Less Than
                    if (instructionsAndParameters[1] < instructionsAndParameters[2]) {
                        computerMemory[instructionsAndParameters[3]] = 1;
                    }
                    else {
                        computerMemory[instructionsAndParameters[3]] = 0;
                    }
                    index += 4;
                    break;
                case 8: //Equals
                    if (instructionsAndParameters[1] == instructionsAndParameters[2]) {
                        computerMemory[instructionsAndParameters[3]] = 1;
                    }
                    else {
                        computerMemory[instructionsAndParameters[3]] = 0;
                    }
                    index += 4;
                    break;
                case 99:
                    haltExecution = true;
                    continue;
                default:
                    throw new IllegalArgumentException("Error: Opcode not Recognised");
            }
        }
        return computerMemory;
    }

    private int[] determineOpcodeAndParameters(int index) {

        int instruction = computerMemory[index];

        int[] parameters = {0,0,0,0,0};
        parameters[0] = instruction % 100;
        if (parameters[0] == 99) {
            return parameters;
        }
        int parameterNumber = -1;

        switch (parameters[0]) {
            case 1:
            case 2:
            case 7:
            case 8:
                parameterNumber = 2;
                parameters[3] = computerMemory[index + 3];
                break;
            case 3:
                parameterNumber = 0;
                parameters[1] = computerMemory[index + 1];
                break;
            case 4:
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
        //System.out.println("Opcode: " + parameters[0] + " Number of arguments: " + parameterNumber);

        instruction /= 100;
        for (int j = 1; j <= parameterNumber; j++) {
            parameters[j] = instruction % 10;
            //System.out.println(j +": " + parameters[j]);
            instruction /= 10;
        }


        //System.out.println("Parameters:");
        for (int i = 1; i <= parameterNumber; i++) {
            //System.out.print(i + ": ");

            if (parameters[i] == 0) {
                parameters[i] = computerMemory[computerMemory[index + i]];
                //System.out.println(parameters[i]);
            }
            else if (parameters[i] == 1) {
                parameters[i] = computerMemory[index + i];
                //System.out.println(parameters[i]);
            }
            else {
                throw new IllegalArgumentException("Invalid Instructions Detected");
            }
        }
        return parameters;
    }

    public void resetElfComputer() {
        System.arraycopy(originalState,0,computerMemory,0, computerMemory.length);
    }




}
