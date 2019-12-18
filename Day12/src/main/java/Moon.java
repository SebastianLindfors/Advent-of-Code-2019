import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Moon {

    private String name;

    private List<Moon> otherMoons = new ArrayList<>();

    private Point3D position;
    private Point3D startingPosition;

    private Point3D velocity = new Point3D(0,0,0);

    private double totalLunarEnergy;

    public Moon(Point3D position) {

        this.position = position;

    }

    public Moon(String name, double x, double y, double z) {

        this.name = name;

        this.position = new Point3D(x,y,z);
        this.startingPosition = new Point3D(x,y,z);

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

    public Map<String, Integer> compareToStartPosition() {

        int xComp = greaterEqualOrLesser(position.getX(), startingPosition.getX());
        int yComp = greaterEqualOrLesser(position.getY(), startingPosition.getY());
        int zComp = greaterEqualOrLesser(position.getZ(), startingPosition.getZ());

        Map<String, Integer> output = new HashMap<>();

        output.put("x", xComp);
        output.put("y", yComp);
        output.put("z", zComp);

        return output;

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

    public Point3D getStartingPosition() {
        return startingPosition;
    }

    public Point3D getVelocity() {
        return velocity;
    }

    public List<Moon> getOtherMoons() {
        return otherMoons;
    }

    public String getName() {
        return name;
    }


    private double computeManhattanDistanceToZero(Point3D point) {

        return Math.abs(point.getX()) + Math.abs(point.getY()) + Math.abs(point.getZ());

    }

    private int greaterEqualOrLesser(double x, double y) {
        if (Math.abs(x - y) < 0.000001) {
            return 0;
        }
        else if (x > y) {
            return 1;
        }
        else {
            return -1;
        }
    }





}
