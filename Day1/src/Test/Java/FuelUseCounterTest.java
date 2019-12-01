import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

class FuelUseCounterTest {

    @ParameterizedTest
    @CsvSource({"12, 2", "14, 2", "1969, 654", "100756, 33583"})
    public void computeFuelNeededPerModuleTest(int input, int expected) {

        int actual = FuelUseCounter.computeFuelNeededPerModule(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"14, 2", "1969, 966", "100756, 50346"})
    public void computeFuelNeededPerModuleWithFuelTest(int input, int expected) {

        int actual = FuelUseCounter.computeFuelNeededPerModuleWithFuel(input);
        assertEquals(expected, actual);
    }



    @Test
    public void sumOfFuelNeededForModuleTest() {
       int[] input = {12, 14, 1969, 100756};
       int expected = (2 + 2 + 654 + 33583);

       int actual = FuelUseCounter.sumOfFuelNeededForModules(input);
        assertEquals(expected, actual);
    }

}