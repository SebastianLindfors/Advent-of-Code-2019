import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ElfComputer {

    public static int[] executeProgram(int[] program) {

        boolean haltExecution = false;
        int index = 0;
        int[] output = new int[program.length];
        System.arraycopy(program,0,output,0, program.length);


        while(!haltExecution) {
            int opcode = output[index];

            switch (opcode) {
                case 1:
                    output[output[index + 3]] = output[output[index + 1]] + output[output[index + 2]];
                    index += 4;
                    break;
                case 2:
                    output[output[index + 3]] = output[output[index + 1]] * output[output[index + 2]];
                    index += 4;
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




}
