import java.util.Scanner;

/**
 * RPGGame class is to initiate different kinds of sub-rpg game class.
 */
public class RPGGame {
    RPGGame() {
    }

    /**
     * Start the RPG game center and prompt user to choose game.
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print(
            "Welcome to RPG Game Center.\n" +
            "1. Legends: Monsters and Heroes\n" +
            "2. Legends of Valor\n" +
            "3. Cyberpunk 2077\n" +
            "Which game you want to play? (Pick 1/2 now that's all we got!) "
        );
        String gameChoice = sc.next();
        while (!gameChoice.matches("^[1-3]$")) {
            System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
            gameChoice = sc.next();
        }

        if (gameChoice.equals("1")) {
            LMH_Logic lmh_logic = new LMH_Logic();
            lmh_logic.prep();
        }
        else if (gameChoice.equals("2")) {
            LoV_Logic loV_logic = new LoV_Logic();
            loV_logic.prep();
        }

        sc.close();
    }
}
