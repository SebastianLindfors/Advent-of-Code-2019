import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoonTest {

    @Test
    void computeTotalLunarEnergyTest1_noVelocity_expects0() {


        Moon testMoon = new Moon(1,1,1);

        double actual = testMoon.computeTotalLunarEnergy();
        double expected = 0;

        assertEquals(actual, expected);

    }

    @Test
    void computeTotalLunarEnergyTest2_withVelocity_expects9() {

        Moon testMoon = new Moon(1,1,1);
        testMoon.setVelocity(1,1,1);

        double actual = testMoon.computeTotalLunarEnergy();
        double expected = 9;

        assertEquals(actual, expected);
    }

    @Test
    void moveMoonTest1_expects222() {

        Moon testMoon = new Moon(1,1,1);
        testMoon.setVelocity(1,1,1);

        testMoon.moveMoon();
        Point3D actual = testMoon.getPosition();
        Point3D expected = new Point3D(2,2,2);

        assertEquals(expected, actual);
    }

    @Test
    void moveMoonTest2_expects234() {

        Moon testMoon = new Moon(1,1,1);
        testMoon.setVelocity(1,2,3);

        testMoon.moveMoon();
        Point3D actual = testMoon.getPosition();
        Point3D expected = new Point3D(2,3,4);

        assertEquals(expected, actual);
    }

    @Test
    void updateVelocityTest1_expects222() {

        Moon testMoon = new Moon(1,1,1);
        testMoon.setVelocity(1,1,1);
        Moon testMoon2 = new Moon(10,10,10);

        testMoon.updateVelocity(testMoon2);

        Point3D actual = testMoon.getVelocity();
        Point3D expected = new Point3D(2,2,2);

        assertEquals(expected, actual);
    }

    @Test
    void updateVelocityTest2_expects022() {

        Moon testMoon = new Moon(1,1,1);
        testMoon.setVelocity(1,1,1);
        Moon testMoon2 = new Moon(-10,10,10);

        testMoon.updateVelocity(testMoon2);

        Point3D actual = testMoon.getVelocity();
        Point3D expected = new Point3D(0,2,2);

        assertEquals(expected, actual);
    }

    @Test
    void updateVelocityTest3_expects202() {

        Moon testMoon = new Moon(1,1,1);
        testMoon.setVelocity(1,1,1);
        Moon testMoon2 = new Moon(10,-10,10);

        testMoon.updateVelocity(testMoon2);

        Point3D actual = testMoon.getVelocity();
        Point3D expected = new Point3D(2,0,2);

        assertEquals(expected, actual);
    }

    @Test
    void updateVelocityTest4_expects220() {

        Moon testMoon = new Moon(1,1,1);
        testMoon.setVelocity(1,1,1);
        Moon testMoon2 = new Moon(10,10,-10);

        testMoon.updateVelocity(testMoon2);

        Point3D actual = testMoon.getVelocity();
        Point3D expected = new Point3D(2,2,0);

        assertEquals(expected, actual);
    }

    @Test
    public void addOtherMoonTest1() {

        Moon testMoon = new Moon(0,0,0);
        Moon testMoon1 = new Moon(1,1,1);
        Moon testMoon2 = new Moon(2,2,2);
        Moon testMoon3 = new Moon(3,3,3);

        testMoon.addOtherMoon(testMoon1);
        testMoon.addOtherMoon(testMoon2);
        testMoon.addOtherMoon(testMoon3);

        List<Moon> expected = new ArrayList<>();
        expected.add(testMoon1);
        expected.add(testMoon2);
        expected.add(testMoon3);

        assertEquals(expected, testMoon.getOtherMoons());

    }

    @Test
    public void computeNextVelocityTest1_oneOtherMoon_expects111() {

        Moon testMoon = new Moon(0,0,0);
        Moon testMoon1 = new Moon(10,10,10);


        testMoon.addOtherMoon(testMoon1);
        testMoon.computeNextVelocity();
        Point3D expected = new Point3D(1,1,1);

        assertEquals(expected, testMoon.getVelocity());

    }

    @Test
    public void computeNextVelocityTest2_twoOtherMoon_expects000() {

        Moon testMoon = new Moon(0,0,0);
        Moon testMoon1 = new Moon(10,10,10);
        Moon testMoon2 = new Moon(-10,-10,-10);


        testMoon.addOtherMoon(testMoon1);
        testMoon.addOtherMoon(testMoon2);
        testMoon.computeNextVelocity();
        Point3D expected = new Point3D(0,0,0);

        assertEquals(expected, testMoon.getVelocity());

    }

}