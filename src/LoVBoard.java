import java.util.ArrayList;
import java.util.Random;

/**
 * LMHBoard class maintains information about the contents of a Board class in LMH games.
 * LMHBoard class has a more specified schema extended from Board object.
 */

public class LoVBoard extends Board {
    private Tile[][] board;

    /**
     * No-arg constructor
     */
    LoVBoard() {
        this(0);
    }

    /**
     * Constructor of a Board with specified size
     *
     * @param size size of the board
     */
    /*********************************************************
     *Please note:
     *	size equals to num of players, the shape of gameboard
     *	is determined by num of players
     *********************************************************/
    LoVBoard(int size) {
        this.size = size;
        int column = (size * 2) + (size - 1);
        int row = column;
        board = new Tile[row][column];
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
        System.out.println(Color.CYAN   + " [N] " + Color.RESET + "Nexus");
        System.out.println(Color.RED    + " [I] " + Color.RESET + "Inaccessible");
        System.out.println(               " [P] " + "Plain");
        System.out.println(Color.GREEN  + " [B] " + Color.RESET + "Bush");
        System.out.println(Color.ORANGE + " [C] " + Color.RESET + "Cave");
        System.out.println(Color.PURPLE + " [K] " + Color.RESET + "Koulou");
        System.out.println(Color.WHITE  + " [M] " + Color.RESET + "Monster");
        System.out.println(bannerBorder);
    }

    /**
     * Prints instructions on movement operations.
     */
    private void displayOpetations() {
        System.out.println(Color.PURPLE + "--------------------------------" + Color.RESET);
        System.out.println(               "           Operations           ");
        System.out.println(Color.PURPLE + "--------------------------------" + Color.RESET);

        System.out.println(Color.RED + "W/w" + Color.RESET + ": Up");
        System.out.println(Color.RED + "A/a" + Color.RESET + ": Left");

        System.out.println(Color.RED + "S/s" + Color.RESET + ": Down");
        System.out.println(Color.RED + "D/d" + Color.RESET + ": Right");

        System.out.println(Color.RED + "I/i" + Color.RESET + ": Show hero(es) info & Check inventory");
        System.out.println(Color.RED + "Q/q" + Color.RESET + ": Quit");

        System.out.println(Color.RED + "T/t" + Color.RESET + ": Teleport to one of your teammate");
        System.out.println(Color.RED + "B/b" + Color.RESET + ": Back to Nexus");

        System.out.println(Color.PURPLE + "--------------------------------" + Color.RESET);
    }

    @Override
    public void displayBoard() {
        displayBoardInfo();
        String result = "";
        for(int i = 0; i < this.board.length; i++) {
            ArrayList<String[]> this_line = new ArrayList<>();
            for(int j = 0; j < this.board[i].length; j++) {
                String[] this_draw = this.board[i][j].specialDraw().split("\\r?\\n");
                this_line.add(this_draw);
            }
            result += this.line_assembler(this_line) + "\n";
        }
        System.out.println(result);
        displayOpetations();
    }

    public String line_assembler(ArrayList<String[]> target) {
        String result = "";
        int num_of_loop = target.get(0).length;
        for(int i = 0; i < num_of_loop; i++) {
            for(String[] s:target) {
                result += s[i] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public int getNumRow() {
        return this.board.length;
    }

    public int getNumColumn() {
        return this.board[0].length;
    }

    @Override
    public void setBoard() {
        //20 special cell 40 plain, nexus and inaccessible fixed num
        this.cell_factory = new CellFactory();
        Random random = new Random();
        int row = getNumRow();
        int column = getNumColumn();
        int number_of_nexus = (column - this.size + 1) * 2;
        int number_of_inaccessible = (this.size - 1) * row;
        int number_of_plain = 17 * this.size;
        int number_of_special = (row * column) - number_of_nexus - number_of_inaccessible - number_of_plain;
        int index_count_for_inaccessible = 0;
        int category;
        CellType type = null;

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if(index_count_for_inaccessible == 2) {
                    // cell for inaccessible
                    index_count_for_inaccessible = 0;
                    type = CellType.INACCESSIBLE;
                    index_count_for_inaccessible -= 1;
                } else if(i == 0 || i == this.board.length - 1) {
                    // cell for nexus
                    type = CellType.NEXUS;
                }
                else {
                    // cell for special and plain
                    boolean match = false;
                    while(!match) {
                        category = random.nextInt(5);
                        if(category == 0 && number_of_special != 0) {
                            type = CellType.BUSH;
                            number_of_special -= 1;
                            match = true;
                        }
                        else if(category == 1&& number_of_special != 0) {
                            type = CellType.CAVE;
                            number_of_special -= 1;
                            match = true;
                        }
                        else if(category == 2&& number_of_special != 0) {
                            type = CellType.KOULOU;
                            number_of_special -= 1;
                            match = true;
                        }
                        else if(number_of_plain != 0) {
                            type = CellType.PLAIN;
                            number_of_plain -= 1;
                            match = true;
                        }
                    }
                }
                index_count_for_inaccessible += 1;
                this.board[i][j] = this.cell_factory.create_cell(type);
            }
            index_count_for_inaccessible = 0;
        }

    }

    public Tile getTile(int x, int y){
        return this.board[x][y];
    }
}
