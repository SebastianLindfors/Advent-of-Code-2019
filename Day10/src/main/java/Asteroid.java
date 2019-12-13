import javax.management.BadAttributeValueExpException;
import java.awt.geom.Point2D;

public class Asteroid implements Comparable<Asteroid>{

    private int otherVisibleAsteroids = -1;

    public double distanceToTarget = -1;

    Point2D.Double position;


    public Asteroid(Point2D.Double position) {

        this.position = position;

    }

    public Asteroid(int x, int y) {

        this.position = new Point2D.Double(x,y);

    }

    public int getOtherVisibleAsteroids() {

        if (otherVisibleAsteroids == -1) {
            System.out.println("Warning: Value not computed for this objekt.");
        }
        return otherVisibleAsteroids;
    }

    public void setOtherVisibleAsteroids(int otherVisibleAsteroids) {
        this.otherVisibleAsteroids = otherVisibleAsteroids;
    }

    public void computeDistanceToTarget(Asteroid a) {
        distanceToTarget = this.position.distance(a.position);
    }

    @Override
    public int compareTo(Asteroid asteroid) {
       return (int) (this.distanceToTarget - asteroid.distanceToTarget);
    }
}
