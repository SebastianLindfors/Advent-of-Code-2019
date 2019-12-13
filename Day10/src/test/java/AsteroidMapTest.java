import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

public class AsteroidMapTest {

    @Test
    public void AsteroidMapTest_1() {

        AsteroidMap testMap = new AsteroidMap();

        testMap.addAsteroid(new Asteroid(0,0));
        testMap.addAsteroid(new Asteroid(5,0));
        testMap.addAsteroid(new Asteroid(-5,0));
        testMap.addAsteroid(new Asteroid(0,5));
        testMap.addAsteroid(new Asteroid(0,-5));

        Assertions.assertEquals(4,testMap.computeOtherVisibleAsteroids(testMap.listOfAsteroids.get(0)));


    }

    @Test
    public void AsteroidMapTest_2() {

        AsteroidMap testMap = new AsteroidMap();

        testMap.addAsteroid(new Asteroid(0, 0));
        testMap.addAsteroid(new Asteroid(5, 0));
        testMap.addAsteroid(new Asteroid(10, 0));
        testMap.addAsteroid(new Asteroid(-5, 0));
        testMap.addAsteroid(new Asteroid(-10, 0));
        testMap.addAsteroid(new Asteroid(0, 5));
        testMap.addAsteroid(new Asteroid(0, 10));
        testMap.addAsteroid(new Asteroid(0, -5));
        testMap.addAsteroid(new Asteroid(0, -10));

        Assertions.assertEquals(4, testMap.computeOtherVisibleAsteroids(testMap.listOfAsteroids.get(0)));
    }

    @Test
    public void AsteroidMapTest_3() {

        AsteroidMap testMap = new AsteroidMap();

        testMap.addAsteroid(new Asteroid(0, 0));
        testMap.addAsteroid(new Asteroid(5, 0));
        testMap.addAsteroid(new Asteroid(5, 5));
        testMap.addAsteroid(new Asteroid(-5, 0));
        testMap.addAsteroid(new Asteroid(-5, -5));
        testMap.addAsteroid(new Asteroid(0, 5));
        testMap.addAsteroid(new Asteroid(-5, 5));
        testMap.addAsteroid(new Asteroid(0, -5));
        testMap.addAsteroid(new Asteroid(5, -5));

        Assertions.assertEquals(8, testMap.computeOtherVisibleAsteroids(testMap.listOfAsteroids.get(0)));
    }

    @Test
    public void AsteroidMap_loadFromFileTest1() {

        String fileName = "src\\test\\resources\\Day10Test.txt";
        AsteroidMap testMap = new AsteroidMap();
        testMap.buildMapFromFile(fileName);

        Point2D.Double expected = new Point2D.Double(5,8);

        testMap.asteroidCensus();
        System.out.println(testMap.bestBaseCandidate.position.x + ", " + testMap.bestBaseCandidate.position.y + " | Number of visible asteroids: " + testMap.bestBaseCandidate.getOtherVisibleAsteroids());
        Assertions.assertEquals(expected, testMap.bestBaseCandidate.position);
    }

    @Test
    public void AsteroidMap_loadFromFileTest2() {

        String fileName = "src\\test\\resources\\Day10Test2.txt";
        AsteroidMap testMap = new AsteroidMap();
        testMap.buildMapFromFile(fileName);

        Point2D.Double expected = new Point2D.Double(1,2);

        testMap.asteroidCensus();
        System.out.println(testMap.bestBaseCandidate.position.x + ", " + testMap.bestBaseCandidate.position.y + " | Number of visible asteroids: " + testMap.bestBaseCandidate.getOtherVisibleAsteroids());
        Assertions.assertEquals(expected, testMap.bestBaseCandidate.position);
    }

    @Test
    public void AsteroidMap_loadFromFileTest3() {

        String fileName = "src\\test\\resources\\Day10Test3.txt";
        AsteroidMap testMap = new AsteroidMap();
        testMap.buildMapFromFile(fileName);

        Point2D.Double expected = new Point2D.Double(6,3);

        testMap.asteroidCensus();
        System.out.println(testMap.bestBaseCandidate.position.x + ", " + testMap.bestBaseCandidate.position.y + " | Number of visible asteroids: " + testMap.bestBaseCandidate.getOtherVisibleAsteroids());
        Assertions.assertEquals(expected, testMap.bestBaseCandidate.position);
    }

    @Test
    public void AsteroidMap_loadFromFileTest4() {

        String fileName = "src\\test\\resources\\Day10Test4.txt";
        AsteroidMap testMap = new AsteroidMap();
        testMap.buildMapFromFile(fileName);

        Point2D.Double expected = new Point2D.Double(11,13);


        testMap.asteroidCensus();
        System.out.println(testMap.bestBaseCandidate.position.x + ", " + testMap.bestBaseCandidate.position.y + " | Number of visible asteroids: " + testMap.bestBaseCandidate.getOtherVisibleAsteroids());
        Assertions.assertEquals(expected, testMap.bestBaseCandidate.position);



    }

    @Test
    public void AsteroidMap_loadFromFileTest5() {

        String fileName = "src\\test\\resources\\Day10Test5.txt";
        AsteroidMap testMap = new AsteroidMap();
        testMap.buildMapFromFile(fileName);

        Point2D.Double expected = new Point2D.Double(3,4);


        testMap.asteroidCensus();
        System.out.println(testMap.bestBaseCandidate.position.x + ", " + testMap.bestBaseCandidate.position.y + " | Number of visible asteroids: " + testMap.bestBaseCandidate.getOtherVisibleAsteroids());
        Assertions.assertEquals(expected, testMap.bestBaseCandidate.position);

    }

    @Test public void asteroidMap_laserProtocollTest() {

        String fileName = "src\\test\\resources\\Day10Test5.txt";
        AsteroidMap testMap = new AsteroidMap();
        testMap.buildMapFromFile(fileName);
        testMap.asteroidCensus();
        List<Asteroid> ood = testMap.activeateLaserProtocoll(testMap.bestBaseCandidate);

        for (Asteroid a: ood) {
            System.out.println(a.position.x + ", " + a.position.y);
        }

//        System.out.println(ood.get(0).position);
//        System.out.println(ood.get(1).position);
//        System.out.println(ood.get(2).position);
//        System.out.println(ood.get(9).position);
//        System.out.println(ood.get(19).position);
//        System.out.println(ood.get(49).position);
//        System.out.println(ood.get(99).position);


    }





}
