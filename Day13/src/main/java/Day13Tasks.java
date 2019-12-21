import java.lang.management.GarbageCollectorMXBean;

public class Day13Tasks {

    public static void main(String[] args) {

        String fileName = "src\\main\\resources\\Day13.txt";

        ArcadeGame mainGame = new ArcadeGame(fileName);

        mainGame.moveJoyStick(-1);
        mainGame.updateGameStateFromList(mainGame.runArcadeComputer());
        System.out.println(mainGame.createGameBoardString());

        System.out.println("The total number of blocks at this point of the game is: " + mainGame.getScreenItemCount(2));

        while (mainGame.getScreenItemCount(2) > 0) {

            mainGame.moveJoyStick(mainGame.ballPosition.x - mainGame.paddlePosition.x);
            mainGame.updateGameStateFromList(mainGame.runArcadeComputer());
            System.out.println(mainGame.createGameBoardString());

        }


    }


}
