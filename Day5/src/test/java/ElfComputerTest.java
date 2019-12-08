import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ElfComputerTest {


    @Test
    public void advancedMultiplicationTest() {
        int[] input = {1002,4,3,4,33};
        int[] expected = {1002,4,3,4,99};
        ElfComputer testComputer = new ElfComputer(input);

        int[] actual = testComputer.executeProgram();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void compareToEightTest_expectTrue() {

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream in = new ByteArrayInputStream(("8" + System.lineSeparator()).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        int[] input = {3,9,8,9,10,9,4,9,99,-1,8};
        ElfComputer testComputer = new ElfComputer(input);

        testComputer.executeProgram();
        Assertions.assertEquals("1", out.toString());

        System.setIn(originalIn);
        System.setOut(originalOut);

    }

    @Test
    public void compareToEightTest_expectFalse() {

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream in = new ByteArrayInputStream(("5" + System.lineSeparator()).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        int[] input = {3,9,8,9,10,9,4,9,99,-1,8};
        ElfComputer testComputer = new ElfComputer(input);

        testComputer.executeProgram();
        Assertions.assertEquals("0", out.toString());

        System.setIn(originalIn);
        System.setOut(originalOut);

    }



}


