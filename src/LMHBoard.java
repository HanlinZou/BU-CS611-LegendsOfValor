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
        StringBuilder bannerBorder = new StringBuilder(Color.getColor().PURPLE + "-");
        bannerBorder.append("---".repeat(Math.max(10, this.size)));
        bannerBorder.append(Color.getColor().RESET);

        System.out.println(bannerBorder);
        System.out.println(Color.getColor().BLUE   + " [H] " + Color.getColor().RESET + "You");
        System.out.println(Color.getColor().GREEN  + " [.] " + Color.getColor().RESET + "Accessible (might be dangerous)");
        System.out.println(Color.getColor().ORANGE + " [M] " + Color.getColor().RESET + "Market");
        System.out.println(Color.getColor().RED    + " [X] " + Color.getColor().RESET + "Inaccessible");
        System.out.println(bannerBorder);
    }

    /**
     * Prints instructions on movement operations.
     */
    private void displayOperations() {
        System.out.println(Color.getColor().PURPLE + "--------------------------------" + Color.getColor().RESET);
        System.out.println(               "           Operations           ");
        System.out.println(Color.getColor().PURPLE + "--------------------------------" + Color.getColor().RESET);

        System.out.println(Color.getColor().RED + "W/w" + Color.getColor().RESET + ": Up");
        System.out.println(Color.getColor().RED + "A/a" + Color.getColor().RESET + ": Left");

        System.out.println(Color.getColor().RED + "S/s" + Color.getColor().RESET + ": Down");
        System.out.println(Color.getColor().RED + "D/d" + Color.getColor().RESET + ": Right");

        System.out.println(Color.getColor().RED + "I/i" + Color.getColor().RESET + ": Show hero(es) info & Check inventory");
        System.out.println(Color.getColor().RED + "Q/q" + Color.getColor().RESET + ": Quit");

        System.out.println(Color.getColor().PURPLE + "--------------------------------" + Color.getColor().RESET);
    }

    /**
     * Displays the LMH board in terminal.
     */
    @Override
    public void displayBoard() {
        displayBoardInfo();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (board[i][j].toString()) {
                    case "H":
                        System.out.printf(Color.getColor().BLUE + "[%s]" + Color.getColor().RESET, board[i][j]);
                        break;
                    case "X":
                        System.out.printf(Color.getColor().RED + "[%s]" + Color.getColor().RESET, board[i][j]);
                        break;
                    case "M":
                        System.out.printf(Color.getColor().ORANGE + "[%s]" + Color.getColor().RESET, board[i][j]);
                        break;
                    case ".":
                        System.out.printf(Color.getColor().GREEN + "[%s]" + Color.getColor().RESET, board[i][j]);
                        break;
                }
            }
            System.out.print("\n");
        }

        displayOperations();
    }

    /**
     * Initializes the LMH board randomly.
     */
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
