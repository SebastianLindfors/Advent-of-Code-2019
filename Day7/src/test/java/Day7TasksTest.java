import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class Day7TasksTest {

    @Test
    public void getAllPermutationsTest() {
        int[] input = new int[] {1,2,3};
        int[][] expected = new int[][] {{1,2,3},{2,1,3},{3,1,2},{1,3,2},{2,3,1},{3,2,1}};

        Assertions.assertArrayEquals(expected, Day7Tasks.getAllPermutations(input));

    }


    @ParameterizedTest
    @CsvSource({"1, 1","2, 2","3, 6","4, 24","5, 120"})
    public void factorialTest(int input, int expected) {

        Assertions.assertEquals(expected,Day7Tasks.factorial(input));

    }



}
