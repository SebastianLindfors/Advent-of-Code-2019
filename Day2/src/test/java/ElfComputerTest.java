import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ElfComputerTest {
    // @ParameterizedTest
   // @CsvSource("[1,0,0,0,99], [2,0,0,0,99]")

    @Test
    public void computeTestAddition1_to_2() {
        int[] input = {1,0,0,0,99};
        int[] expected = {2,0,0,0,99};
        ElfComputer testComputer = new ElfComputer(input);

        int[] actual = testComputer.executeProgram();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void computeTestMultiplication2_to_6() {
        int[] input = {2,3,0,3,99};
        int[] expected = {2,3,0,6,99};
        ElfComputer testComputer = new ElfComputer(input);

        int[] actual = testComputer.executeProgram();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void computeTestMultiplication0_to_9801() {
        int[] input = {2,4,4,5,99,0};
        int[] expected = {2,4,4,5,99,9801};
        ElfComputer testComputer = new ElfComputer(input);

        int[] actual = testComputer.executeProgram();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void computeTestSeveralInstructions() {
        int[] input = {1,1,1,4,99,5,6,0,99};
        int[] expected = {30,1,1,4,2,5,6,0,99};
        ElfComputer testComputer = new ElfComputer(input);

        int[] actual = testComputer.executeProgram();
        Assertions.assertArrayEquals(expected, actual);
    }

}
