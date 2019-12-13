import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AsteroidMap {

    final double DELTA = 0.0000001;

    int mapXsize = -1;
    int mapYsize = -1;

    List<Asteroid> listOfAsteroids = new ArrayList<>();

    Asteroid bestBaseCandidate = null;

    public void addAsteroid(Asteroid a) { listOfAsteroids.add(a); }

    public double computeRelativeKValue(Asteroid a, Asteroid b) {
        //System.out.println(a.position.x + " " + a.position.y + " " + b.position.x + " " + b.position.y);
        if (Math.abs(a.position.x - b.position.x) < DELTA) {
            if (a.position.y > b.position.y) return Double.NEGATIVE_INFINITY;
            else if (a.position.y < b.position.y) return Double.POSITIVE_INFINITY;
            else {
                return Double.NaN;
            }
        }
        else return (a.position.y - b.position.y) / (a.position.x - b.position.x);
    }

    public double[] computeAllRelativeKValues(Asteroid a, List<Asteroid> asteroids) {

        double[] output = new double[asteroids.size()];
        int counter = 0;
        for (Asteroid b : asteroids) {
            output[counter++] = computeRelativeKValue(a,b);
        }
        Arrays.sort(output);

        return output;

    }

    public int computeOtherVisibleAsteroids(Asteroid a) {

        List<Asteroid> lefties = getRelativeLeftASteroids(a);
        List<Asteroid> righties = getRelativeRightASteroids(a);

        double[] leftkValues = computeAllRelativeKValues(a, lefties);
        double[] rightkValues = computeAllRelativeKValues(a, righties);

        double[] leftunique = Arrays.stream(leftkValues).distinct().toArray();
        double[] rightunique = Arrays.stream(rightkValues).distinct().toArray();

        int numberOfAsteroids = leftunique.length + rightunique.length;
        a.setOtherVisibleAsteroids(numberOfAsteroids);

        return numberOfAsteroids;

    }

    public List<Asteroid> activeateLaserProtocoll(Asteroid a) {

        List<Asteroid> lefties = getRelativeLeftASteroids(a);
        List<Asteroid> righties = getRelativeRightASteroids(a);

        double[] leftkValues = computeAllRelativeKValues(a, lefties);
        double[] rightkValues = computeAllRelativeKValues(a, righties);

        Line2D.Double laserSigth;
        List<List<Asteroid>> linesOfDestruction = new ArrayList<>();
        for (int i = 0; i < rightkValues.length; i++) {
            System.out.println("RV: " + rightkValues[i]);
            List<Asteroid> currentList = new ArrayList<>();
            if (rightkValues[i] == Double.POSITIVE_INFINITY) {
                laserSigth = new Line2D.Double(a.position, new Point2D.Double(a.position.x, a.position.y - 1));
            }
            else if (rightkValues[i] == Double.NEGATIVE_INFINITY) {
                laserSigth = new Line2D.Double(a.position, new Point2D.Double(a.position.x, a.position.y + 1));
            }
            else{
                laserSigth = new Line2D.Double(a.position, new Point2D.Double(a.position.x + 1, a.position.y + rightkValues[i]));
            }
            Iterator<Asteroid> iterator = righties.iterator();
            while  (iterator.hasNext()) {
                Asteroid b = iterator.next();
                if (laserSigth.ptLineDistSq(b.position) == 0) {
                    b.computeDistanceToTarget(a);
                    currentList.add(b);
                    iterator.remove();
                }
            }
            if (currentList.size() > 0) {
                currentList.sort(Asteroid::compareTo);
                linesOfDestruction.add(currentList);
            }
            //System.out.println(rightkValues[i]);
        }
        for (int i = 0; i < leftkValues.length; i++) {
            System.out.println("LV: " + leftkValues[i]);
            List<Asteroid> currentList = new ArrayList<>();
            if (leftkValues[i] == Double.POSITIVE_INFINITY) {
                laserSigth = new Line2D.Double(a.position, new Point2D.Double(a.position.x, a.position.y + 1));
            }
            else if (leftkValues[i] == Double.NEGATIVE_INFINITY) {
                laserSigth = new Line2D.Double(a.position, new Point2D.Double(a.position.x, a.position.y - 1));
            }
            else{
                laserSigth = new Line2D.Double(a.position, new Point2D.Double(a.position.x + 1, a.position.y + leftkValues[i]));
            }

            Iterator<Asteroid> iterator = lefties.iterator();
            while  (iterator.hasNext()) {
                Asteroid b = iterator.next();
                if (laserSigth.ptLineDistSq(b.position) == 0) {
                    b.computeDistanceToTarget(a);
                    currentList.add(b);
                    iterator.remove();
                }
            }
            if (currentList.size() > 0) {
                currentList.sort(Asteroid::compareTo);
                linesOfDestruction.add(currentList);
            }
            //System.out.println(leftkValues[i]);
        }
        List<Asteroid> orderOfDestruction = new ArrayList<>();
        while (linesOfDestruction.size() > 0) {
            Iterator<List<Asteroid>> iterator = linesOfDestruction.iterator();
            while (iterator.hasNext()) {
                List<Asteroid> laserLine = iterator.next();
                orderOfDestruction.add(laserLine.get(0));
                laserLine.remove(0);
                if (laserLine.size() == 0) {
                    iterator.remove();
                }
            }
        }

        return orderOfDestruction;



    }

    public List<Asteroid> getRelativeLeftASteroids(Asteroid a) {

        List<Asteroid> lefties = new ArrayList<>();
        for (Asteroid b : listOfAsteroids) {
            if (!(a == b)) {
                if (a.position.x > b.position.x) {
                    lefties.add(b);
                }
            }
        }
        return lefties;
    }

    public List<Asteroid> getRelativeRightASteroids(Asteroid a) {

        List<Asteroid> righties = new ArrayList<>();
        for (Asteroid b : listOfAsteroids) {
            if (!(a == b)) {
                if (a.position.x <= b.position.x) {
                    righties.add(b);
                }
            }
        }
        return righties;
    }


    public void asteroidCensus() {

        int maximumAsteroids = -1;
        int numberOfAsteroids = 0;
        for (Asteroid a: listOfAsteroids) {
           numberOfAsteroids = computeOtherVisibleAsteroids(a);
           if (numberOfAsteroids > maximumAsteroids) {
               maximumAsteroids = numberOfAsteroids;
               bestBaseCandidate = a;
           }
        }

    }

    public static String loadDataFromDisk(String fileName) throws IOException {

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }

    }

    public void buildMapFromFile(String fileName) {

        String mapData = "";
        try {
            mapData = loadDataFromDisk(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String[] splitMapData = mapData.split("\n");
        for (int i = 0; i < splitMapData.length; i++) {
            char[] mapArray = splitMapData[i].toCharArray();
            for (int j = 0; j < mapArray.length; j++) {
                if (mapArray[j] == '#') {
                    this.addAsteroid(new Asteroid(j,i));
                    if (i > this.mapYsize) {
                        this.mapYsize = i;
                    }
                    if (j > this.mapXsize) {
                        this.mapXsize = j;
                    }
                }
            }
        }



    }













}
