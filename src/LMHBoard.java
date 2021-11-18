import java.util.ArrayList;
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
    /*********************************************************
    *Please note:
    *	size equals to num of players, the shape of gameboard 
    *	is determined by num of players
    *********************************************************/
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
        System.out.println(Color.GREEN  + " [N] " + Color.RESET + "Nexus");
        System.out.println(Color.ORANGE + " [I] " + Color.RESET + "Inaccessible");
        System.out.println(Color.BLUE    + " [P] " + Color.RESET + "Plain");
        System.out.println(Color.CYAN    + " [B] " + Color.RESET + "Bush");
        System.out.println(Color.GREY    + " [Cave] " + Color.RESET + "Cave");
        System.out.println(Color.PURPLE    + " [K] " + Color.RESET + "Koulou");
        System.out.println(Color.WHITE    + " [M] " + Color.RESET + "Monster");
        System.out.println(bannerBorder);
    }

    /**
     * Prints instructions on movement operations.
     */
    private void displayOpetations() {
        System.out.println(Color.PURPLE + "--------------------------------" + Color.RESET);
        System.out.println(               "           Opetations           ");
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
    public void displayBoard() 
    {
        displayBoardInfo();
        String result = "";
		for(int i = 0; i < super.board.length; i++)
		{
			String row = "";
			ArrayList<String[]> this_line = new ArrayList<String[]>();
			for(int j = 0; j < super.board[i].length; j++)
			{
				String[] this_draw  = null;		
				this_draw = super.board[i][j].specialDraw().split("\\r?\\n");
				this_line.add(this_draw);
			}
			result += this.line_assembler(this_line) + "\n";
		}
		System.out.println(result);
        displayOpetations();
    }
	public String line_assembler(ArrayList<String[]> target)
	{
		String result = "";
		int num_of_loop = target.get(0).length;
		for(int i = 0; i < num_of_loop; i++)
		{
			for(String[] s:target)
			{
				result += s[i] + " ";
			}
			result += "\n";
		}
		return result;
	}
    @Override
    public void setBoard() 
    {
    	//20 special cell 40 plain, nexus and inaccessible fixed num
        super.cell_factory = new CellFactory();
    	Random random = new Random();
        int row = super.board.length;
        int column = super.board[0].length;
        int number_of_nexus = (column - this.size + 1) * 2;
        int number_of_inaccessible = (this.size - 1) * row;
        int number_of_plain = 17 * this.size;
        int number_of_special = (row * column) - number_of_nexus - number_of_inaccessible - number_of_plain;
        int index_count_for_inaccessible = 0;
        int category;
        CellType type = null;
        for (int i = 0; i < this.board.length; i++) 
        {
            for (int j = 0; j < this.board[i].length; j++) 
            {
            	//cell for inaccessible
            	if(index_count_for_inaccessible == 2)
            	{
            		index_count_for_inaccessible = 0;
            		type = CellType.INACCESSIBLE;
            		index_count_for_inaccessible -= 1;
            	}
            	//cell for nexus
            	else if(i == 0 || i == super.board.length - 1)
                {
            		type = CellType.NEXUS;
                }
            	else//cell for special and plain
            	{
            		boolean match = false;
            		while(!match)
            		{
            			category = random.nextInt(5);
                		if(category == 0 && number_of_special != 0)
                		{
                			type = CellType.BUSH;
                			number_of_special -= 1;
                			match = true;
                		}
                		else if(category == 1&& number_of_special != 0)
                		{
                			type = CellType.CAVE;
                			number_of_special -= 1;
                			match = true;
                		}
                		else if(category == 2&& number_of_special != 0)
                		{
                			type = CellType.KOULOU;
                			number_of_special -= 1;
                			match = true;
                		}
                		else if(number_of_plain != 0)
                		{
                			type = CellType.PLAIN;
                			number_of_plain -= 1;
                			match = true;
                		}
            		}
            	} 
            	index_count_for_inaccessible += 1;
                super.board[i][j] = super.cell_factory.create_cell(type);
            }
            index_count_for_inaccessible = 0;
        }
       
    }
}
