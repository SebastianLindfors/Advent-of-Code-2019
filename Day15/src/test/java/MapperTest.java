import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

class MapperTest {

    Mapper testMapper = new Mapper();

    @Test
    public void updateFloorLayoutTest_updateNothingTest00_expect1() {

        Point testPoint = new Point(0,0);

        int expected = 1;
        int actual = testMapper.getFloorCoOrdinate(testPoint);

        assertEquals(expected, actual);

    }

    @Test
    public void updateFloorLayoutTest_update011_expect1() {

        Point testPoint = new Point(0,1);

        testMapper.updateFloorLayout(testPoint, 1);

        int expected = 1;
        int actual = testMapper.getFloorCoOrdinate(testPoint);

        assertEquals(expected, actual);

    }

    @Test
    public void updateFloorLayoutTest_updateNothingTest01_expectNegative1() {

        Point testPoint = new Point(0,1);

        int expected = -1;
        int actual = testMapper.getFloorCoOrdinate(testPoint);

        assertEquals(expected, actual);

    }





}