/**
 * Board class maintains information about the contents of a Board.
 * Board class has a specified schema specified by a Board object and
 * groups of Cell objects.
 */
/*********************************************************
*Please note:
*	size equals to num of players, the shape of gameboard 
*	is determined by num of players
*********************************************************/
public class Board {
    protected Cell[][] board;
    protected int size;
    protected CellFactory cell_factory;
    /**
     * No-arg constructor
     */
    Board() {
        board = null;
        size = 0;
        cell_factory = new CellFactory();
    }

    /**
     * Constructor of Board
     *
     * @param size size of the board
     */
    Board(int size) {
        this.size = size;
        int column = (size * 2) + (size - 1);
        int row = column;
        board = new Cell[row][column];
    }

    /**
     * Get the Cell object at specified location
     *
     * @param x one of the coordinates
     * @param y one of the coordinates
     * @return The Cell object at specified location
     */
    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    /**
     * Display the board
     */
    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("[%s]", board[i][j]);
            }
            System.out.print("\n");
        }
    }

    /**
     * Set an empty board
     */
    public void setBoard() 
    {
        /*for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) 
            {
                this.board[i][j] = new Cell();
                this.board[i][j].setMark(" ");
            }
        }*/
    }
}
