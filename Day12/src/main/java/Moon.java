import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Moon {

    private List<Moon> otherMoons = new ArrayList<>();

    private Point3D position;

    private Point3D velocity = new Point3D(0,0,0);

    private double totalLunarEnergy;

    public Moon(Point3D position) {

        this.position = position;

    }

    public Moon(double x, double y, double z) {

        this.position = new Point3D(x,y,z);

        this.totalLunarEnergy = computeTotalLunarEnergy();

    }

    public double computeTotalLunarEnergy() {
        return computeManhattanDistanceToZero(position) * computeManhattanDistanceToZero(velocity);
    }

    public void addOtherMoon(Moon otherMoon) {
        otherMoons.add(otherMoon);
    }

    public void moveMoon() {

        position = position.add(velocity);

    }

    public void updateVelocity(Moon otherMoon) {

        Point3D otherMoonPosition = otherMoon.getPosition();

        if (otherMoonPosition.getX() > this.position.getX()) {
           this.velocity = this.velocity.add(1,0,0);
        }
        else if(otherMoonPosition.getX() < this.position.getX()) {
            this.velocity = this.velocity.add(-1,0,0);
        }

        if (otherMoonPosition.getY() > this.position.getY()) {
            this.velocity = this.velocity.add(0,1,0);
        }
        else if(otherMoonPosition.getY() < this.position.getY()) {
            this.velocity = this.velocity.add(0,-1,0);
        }

        if (otherMoonPosition.getZ() > this.position.getZ()) {
            this.velocity = this.velocity.add(0,0,1);
        }
        else if(otherMoonPosition.getZ() < this.position.getZ()) {
            this.velocity = this.velocity.add(0,0,-1);
        }




    }

    public void computeNextVelocity() {
        for (Moon otherMoon : otherMoons) {
            updateVelocity(otherMoon);
        }
    }

    public void setVelocity(Point3D velocity) {
        this.velocity = velocity;
    }
    public void setVelocity(double x, double y, double z) {
        this.velocity = new Point3D(x,y,z);
    }

    public Point3D getPosition() {
        return position;
    }

    public Point3D getVelocity() {
        return velocity;
    }

    public List<Moon> getOtherMoons() {
        return otherMoons;
    }

    private double computeManhattanDistanceToZero(Point3D point) {

        return Math.abs(point.getX()) + Math.abs(point.getY()) + Math.abs(point.getZ());

    }



}
