import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ArcadeGame {

    long score = 0;

    Point ballPosition = new Point(-1,-1);
    Point paddlePosition = new Point(-1, -1);

    Map<Integer, Integer> screenItemCounts = new HashMap<>();
    Map<Point, Integer> gameState = new HashMap<>();

    ElfComputer gameComputer;



    public ArcadeGame (String fileName) {

        gameComputer = new ElfComputer(fileName);
        gameComputer.directMemoryEdit(0, 2);

        screenItemCounts.put(0,0);
        screenItemCounts.put(1,0);
        screenItemCounts.put(2,0);
        screenItemCounts.put(3,0);
        screenItemCounts.put(4,0);


    }

    public List<Long> runArcadeComputer() {
        return gameComputer.executeProgram();
    }

    public void updateGameStateFromList(List<Long> gameStateList) {

        if (!(gameStateList.size() % 3 == 0)) {
            throw new IllegalArgumentException("Update Lists has illegal length: " + gameStateList.size());
        }

        Iterator<Long> intItr = gameStateList.iterator();
        while(intItr.hasNext()) {

            int x = Math.toIntExact(intItr.next());
            int y = Math.toIntExact(intItr.next());
            int id = Math.toIntExact(intItr.next());

            updateGameState(x,y,id);


        }


    }

    public void updateGameState(int x, int y, int id) {

        if (x == -1 && y == 0) {
            score = id;
            //System.out.println("Player score is now: " + score);
            return;
        }

        if (id < 0 || id > 4) {
            throw new IllegalArgumentException("Unknown Gameboard ID: " + id);
        }
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Illegal Game Board Coordinates: (" + x + ", " + y + ")" );
        }

        Point accessPoint = new Point(x,y);

        if (gameState.containsKey(accessPoint)) {
            screenItemCounts.put(gameState.get(accessPoint), screenItemCounts.get(gameState.get(accessPoint)) - 1);
        }

        gameState.put(new Point(accessPoint),id);
        screenItemCounts.put(id, screenItemCounts.get(id) + 1);

        if (id == 4) {
            ballPosition = accessPoint;
        }
        else if (id == 3) {
            paddlePosition = accessPoint;
        }

    }

    public void moveJoyStick(int direction)  {

        if (direction > 0) {
            gameComputer.setUpInput("1");
        }
        else if (direction < 0) {
            gameComputer.setUpInput("-1");
        }
        else {
            gameComputer.setUpInput("0");
        }

    }

    public String createGameBoardString() {

        int maxX = -1;
        int maxY = -1;
        for (Point p : gameState.keySet()) {
            if (p.x > maxX) {
                maxX = p.x;
            }
            if (p.y > maxY) {
                maxY = p.y;
            }
        }

        char[][] charArray = new char[maxY + 1][maxX + 1];
        for (Point p : gameState.keySet()) {
            charArray[p.y][p.x] = translateToOutputString(gameState.get(new Point(p.x,p.y)));
        }

        String outputString = "";
        for (int y = 0; y < maxY + 1; y++) {
            for (int x = 0; x < maxX + 1; x++) {
                outputString += charArray[y][x];
            }
            outputString += "\n";
        }
        return outputString;
    }

    public int getGameStatePointData(int x, int y) {

        if (!(gameState.containsKey(new Point(1,1)))) {
            throw new IllegalArgumentException("This board coordinate is not set: (" +  + x + ", " + y + ")");
        }

        return gameState.get(new Point(x,y));
    }

    public int getScreenItemCount(int id) {

        if (id < 0 || id > 4) {
            throw new IllegalArgumentException("Unknown Gameboard ID: " + id);
        }

        return screenItemCounts.get(id);

    }

    private char translateToOutputString(int i) {

        switch(i) {
            case 0:
                return ' ';
            case 1:
                return '|';
            case 2:
                return '#';
            case 3:
                return  '_';
            case 4:
                return 'o';
            default:
                throw new IllegalArgumentException("Unknown Tile ID: " + i);
        }
    }

    private long getScore() {
        return score;
    }




}
