import java.util.Scanner;

/**
 * RPGGame class is to initiate different kinds of sub-rpg game class.
 */
public class RPGGame {
    RPGGame() {
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print(
            "Welcome to RPG Game Center.\n" +
            "1. Legends: Monsters and Heroes\n" +
            "2. Cyberpunk 2077\n" +
            "3. Grand Theft Auto V\n" +
            "Which game you want to play? (Pick 1 now that's all we got!) "
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

        sc.close();
    }
}
