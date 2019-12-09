import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class Day9TasksTest {

    @Test
    public void Day9ProgramUpdateTest1() {
        String fileName = "src\\test\\resources\\Day9Test1.txt";

        ElfComputer boostComputer1 = new ElfComputer(fileName);
        String expected = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";

        String outputString = "";
        for (long out : boostComputer1.executeProgram()) {
            outputString += out + ",";
        }
        outputString = outputString.substring(0,outputString.length() - 1);

        System.out.println(expected);
        System.out.println(outputString);

        Assertions.assertEquals(expected, outputString  );
    }

    @Test
    public void Day9ProgramUpdateTest2() {
        String fileName = "src\\test\\resources\\Day9Test2.txt";

        ElfComputer testComputer2 = new ElfComputer(fileName);
        List<Long> output = testComputer2.executeProgram();

        long expected = 34915192L * 34915192;

        for (long out : output) {
            System.out.println("PO: " + out);
        }

        Assertions.assertEquals(expected, (long) output.get(0));
    }

    @Test
    public void Day9ProgramUpdateTest3() {
        String fileName = "src\\test\\resources\\Day9Test3.txt";

        ElfComputer testComputer3 = new ElfComputer(fileName);
        List<Long> output = testComputer3.executeProgram();

        long expected = 1125899906842624L;

        for (long out : output) {
            System.out.println("PO: " + out);
        }


    }



}
