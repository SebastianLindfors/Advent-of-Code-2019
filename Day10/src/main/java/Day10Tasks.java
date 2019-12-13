public class Day10Tasks {

    public static void main(String[] args) {

    String fileName = "src\\main\\resources\\Day10.txt";

    AsteroidMap realMap = new AsteroidMap();
    realMap.buildMapFromFile(fileName);

    realMap.asteroidCensus();

    System.out.println(realMap.bestBaseCandidate.position.x + ", " + realMap.bestBaseCandidate.position.y );
    System.out.println("Number of asteroids visible from best candidate base is: " + realMap.bestBaseCandidate.getOtherVisibleAsteroids());

    }
}
