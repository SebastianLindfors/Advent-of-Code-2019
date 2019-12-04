import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day4Test {

    @Test
    public void LikeAdjacentToLikeTest_expectsTrue() {
        String testString = "112233";
        Assertions.assertTrue(Day4.LikeAdjacentToLike(testString));
    }

    @Test
    public void LikeAdjacentToLikeTest_expectsFalse() {
        String testString = "123456";
        Assertions.assertFalse(Day4.LikeAdjacentToLike(testString));
    }

    @Test
    public void LikeAdjacentToOneLikeTest_expectsTrue() {
        String testString = "577888";
        Assertions.assertTrue(Day4.LikeAdjacentToOneLike(testString));
    }

    @Test
    public void LikeAdjacentToOneLikeTest_expectsFalse() {
        String testString = "567888";
        Assertions.assertFalse(Day4.LikeAdjacentToOneLike(testString));
    }


    @Test
    public void onlyIncreasingNumbersTest_expectsTrue() {
        int testNumber = 123456;
        Assertions.assertTrue(Day4.onlyIncreasingNumbers(testNumber));
    }

    @Test
    public void onlyIncreasingNumbersTest_expectsFalse() {
        int testNumber = 987654;
        Assertions.assertFalse(Day4.onlyIncreasingNumbers(testNumber));
    }

}
