import java.util.Scanner;

/**
 * Game class is to initiate different kinds of sub-game class.
 */
public class Game {
    Game() {
    }

    /**
     * Starts the game center and prompts the user to choose game.
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to Game Center.\n1. RPG\n2. FPS\n3. MOBA\n" +
                "What kind of game you want to play? (Pick 1 now that's all we got!) ");
        String gameChoice = sc.next();
        while (!gameChoice.matches("^[1-3]$")) {
            System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
            gameChoice = sc.next();
        }

        if (gameChoice.equals("1")) {
            RPGGame rpgGame = new RPGGame();
            rpgGame.start();
        }

        sc.close();
    }
}
