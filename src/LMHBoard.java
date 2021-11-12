import java.util.Random;
/**
 * LMHBoard class maintains information about the contents of a Board class in LMH games.
 * LMHBoard class has a more specified schema extended from Board object.
 */
public class LMHBoard extends Board{
    public static String ANSI_RESET = "\u001b[0m";
    public static String ANSI_BUSH = "\u001b[38;5;77m";
    public static String ANSI_HERO = "\u001b[38;5;87m";
    public static String ANSI_MARKET = "\u001b[38;5;143m";
    public static String ANSI_BLOCK = "\u001b[38;5;196m";
    public static String ANSI_PROMPT = "\u001b[38;5;230m";

    /**
     * No-arg constructor
     */
    LMHBoard(){
        super();
    }

    /**
     * Constructor of a Board with specified size
     * @param size size of the board
     */
    LMHBoard(int size){
        super(size);
    }

    @Override
    public void displayBoard(){
        System.out.println(ANSI_PROMPT + "H: You\n.: Bush(might be dangerous)\nM: Market\nX: Inaccessible");
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                switch (board[i][j].toString()){
                    case "H":
                        System.out.printf(ANSI_HERO + "[%s]" + ANSI_RESET, board[i][j]);
                        break;
                    case "X":
                        System.out.printf(ANSI_BLOCK + "[%s]" + ANSI_RESET, board[i][j]);
                        break;
                    case "M":
                        System.out.printf(ANSI_MARKET + "[%s]" + ANSI_RESET, board[i][j]);
                        break;
                    case ".":
                        System.out.printf(ANSI_BUSH + "[%s]" + ANSI_RESET, board[i][j]);
                        break;
                }
            }
            System.out.print("\n");
        }
        System.out.println(ANSI_PROMPT + "W: Up\nA: Left\nS: Down\nD: Right\n" +
                "I: Show hero(es) info & Check inventory\nQ: Quit");
    }

    @Override
    public void setBoard(){
        Random random = new Random();
        int category;
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                this.board[i][j] = new Cell();
                category = random.nextInt(10);
                if(category < 2)
                    this.board[i][j].setMark("X");
                else if(category < 5)
                    this.board[i][j].setMark("M");
                else
                    this.board[i][j].setMark(".");
            }
        }
        //To avoid the case that player get stuck by two "X" at the beginning
        //We set first two cells be bushes and third one to be market
        this.board[0][0].setMark(".");
        this.board[0][0].setHeroOn(true);
        this.board[0][1].setMark(".");
        this.board[0][2].setMark("M");
    }
}
