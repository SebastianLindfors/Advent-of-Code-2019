import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Mapper {

    Map<Point, Integer> floorLayout = new HashMap<>();

    List<Point> unexploredPoints = new ArrayList<>();
    List<Point> visitedTiles = new ArrayList<>();



    public Mapper() {

        Point startPoint = new Point(0,0);

        floorLayout.put(startPoint, 1);
        visitedTiles.add(startPoint);


    }

    public void updateFloorLayout(Point targetCoOrdinates, int mapData) {

        floorLayout.put(targetCoOrdinates, mapData);

    }

    



    public int getFloorCoOrdinate(Point targetCoOrdinate) {

        if (!(floorLayout.containsKey(targetCoOrdinate))) {
            return -1;
        }

        return floorLayout.get(targetCoOrdinate);

    }




}
