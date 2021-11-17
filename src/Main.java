/**
 *	The main is used only as an endpoint that initiates
 *		the execution of the program and does not contain
 *   	any actual program specific logic.
 */

public class Main {
    public static void main(String[] args) {
        System.out.print("Welcome to Legends: Monsters and Heroes\n" +
                "I need your name first: ");
        Game game = new Game();
        game.start();
    }
}
