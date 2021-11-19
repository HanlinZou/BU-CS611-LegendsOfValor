import java.util.Random;

/**
 * LMHBoard class maintains information about the contents of a Board class in LMH games.
 * LMHBoard class has a more specified schema extended from Board object.
 */
public class LMHBoard extends Board {
    /**
     * No-arg constructor
     */
    LMHBoard() {
        super();
    }

    /**
     * Constructor of a Board with specified size
     *
     * @param size size of the board
     */
    LMHBoard(int size) {
        super(size);
    }

    /**
     * Prints the meaning of icons on the board.
     */
    private void displayBoardInfo() {
        String bannerBorder = Color.PURPLE + "-";
        for (int i = 0; i < Math.max(10, this.size); i++) bannerBorder += "---";
        bannerBorder += Color.RESET;

        System.out.println(bannerBorder);
        System.out.println(Color.BLUE   + " [H] " + Color.RESET + "You");
        System.out.println(Color.GREEN  + " [.] " + Color.RESET + "Accessible (might be dangerous)");
        System.out.println(Color.ORANGE + " [M] " + Color.RESET + "Market");
        System.out.println(Color.RED    + " [X] " + Color.RESET + "Inaccessible");
        System.out.println(bannerBorder);
    }

    /**
     * Prints instructions on movement operations.
     */
    private void displayOperations() {
        System.out.println(Color.PURPLE + "--------------------------------" + Color.RESET);
        System.out.println(               "           Operations           ");
        System.out.println(Color.PURPLE + "--------------------------------" + Color.RESET);

        System.out.println(Color.RED + "W/w" + Color.RESET + ": Up");
        System.out.println(Color.RED + "A/a" + Color.RESET + ": Left");

        System.out.println(Color.RED + "S/s" + Color.RESET + ": Down");
        System.out.println(Color.RED + "D/d" + Color.RESET + ": Right");

        System.out.println(Color.RED + "I/i" + Color.RESET + ": Show hero(es) info & Check inventory");
        System.out.println(Color.RED + "Q/q" + Color.RESET + ": Quit");

        System.out.println(Color.PURPLE + "--------------------------------" + Color.RESET);
    }

    @Override
    public void displayBoard() {
        displayBoardInfo();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (board[i][j].toString()) {
                    case "H":
                        System.out.printf(Color.BLUE + "[%s]" + Color.RESET, board[i][j]);
                        break;
                    case "X":
                        System.out.printf(Color.RED + "[%s]" + Color.RESET, board[i][j]);
                        break;
                    case "M":
                        System.out.printf(Color.ORANGE + "[%s]" + Color.RESET, board[i][j]);
                        break;
                    case ".":
                        System.out.printf(Color.GREEN + "[%s]" + Color.RESET, board[i][j]);
                        break;
                }
            }
            System.out.print("\n");
        }

        displayOperations();
    }

    @Override
    public void setBoard() {
        Random random = new Random();
        int category;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new Cell();
                category = random.nextInt(10);
                if (category < 2)
                    this.board[i][j].setMark("X");
                else if (category < 5)
                    this.board[i][j].setMark("M");
                else
                    this.board[i][j].setMark(".");
            }
        }
        // To avoid the case that player get stuck by two "X" at the beginning
        // We set first two cells be bushes and third one to be market
        this.board[0][0].setMark(".");
        this.board[0][0].setHeroOn(true);
        this.board[0][1].setMark(".");
        this.board[0][2].setMark("M");
    }
}
