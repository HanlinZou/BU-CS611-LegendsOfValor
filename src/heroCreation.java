import java.util.Scanner;

public class heroCreation {
    public static String ANSI_RESET = "\u001b[0m";
    public static String ANSI_PROMPT = "\u001b[38;5;230m";
    public static String ANSI_SELECT = "\u001b[38;5;106m";
    public static String ANSI_ERROR = "\u001b[38;5;196m";

    CharacterLibrary chl = new CharacterLibrary();

    heroCreation(){}

    public void prep(LMHPlayer player){
        Scanner sc = new Scanner(System.in);
        // prompt user the number of heroes
        System.out.print(ANSI_PROMPT + "How many heroes would you like?(1-3) ");
        String heroNum = sc.next();
        while(!heroNum.matches("^[1-3]$")){
            System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
            heroNum = sc.next();
        }

        for(int i = 0; i < Integer.parseInt(heroNum); i++){
            // prompt user the position and specific hero to generate
            System.out.println(ANSI_SELECT + "1. Warriors  2. Sorcerers  3. Paladins");
            System.out.print(ANSI_PROMPT + "What position will your No." + (i+1) + " hero to be: ");
            String posChoice = sc.next();

            while(!posChoice.matches("^[1-3]$")){
                System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
                posChoice = sc.next();
            }

            int heroChoice = heroSelection(posChoice);
            heroCreation(player, Integer.parseInt(posChoice), heroChoice - 1);
        }
    }

    /**
     * This function interact with player and let player
     * pick the hero that he/she wants
     * @param posChoice position of heroes(war/sor/pal)
     * @return index of hero in the hero list
     */
    public int heroSelection(String posChoice){
        Scanner sc = new Scanner(System.in);

        // display all heroes based on the position that player required
        if(posChoice.equals("1"))
            chl.displayWarrior();
        else if(posChoice.equals("2"))
            chl.displaySorcerer();
        else
            chl.displayPaladin();

        System.out.print(ANSI_PROMPT + "Pick your favorite one: ");
        String heroChoice = sc.next();

        // after get the result, we verify if it's valid
        while(posChoice.equals("1") &&
                (!heroChoice.matches("[0-9]*")
                        || Integer.parseInt(heroChoice) > chl.warriorArrayList.size()
                        || Integer.parseInt(heroChoice) < 1)){
            System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
            heroChoice = sc.next();
        }

        while(posChoice.equals("2") &&
                (!heroChoice.matches("[0-9]*") ||
                        Integer.parseInt(heroChoice) > chl.sorcererArrayList.size() ||
                        Integer.parseInt(heroChoice) < 1)){
            System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
            heroChoice = sc.next();
        }

        while(posChoice.equals("3") &&
                (!heroChoice.matches("[0-9]*") ||
                        Integer.parseInt(heroChoice) > chl.paladinArrayList.size() ||
                        Integer.parseInt(heroChoice) < 1)){
            System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
            heroChoice = sc.next();
        }

        return Integer.parseInt(heroChoice);
    }

    /**
     * In this function, we generate the required hero and add it
     * to player's hero list after we acquired player's requirements
     * @param player player
     * @param pos position of heroes
     * @param rank the index in that specified hero list
     */
    public void heroCreation(LMHPlayer player, int pos, int rank){
        switch(pos){
            case(1):
                player.heroArrayList.add(chl.warriorArrayList.get(rank));
                break;
            case(2):
                player.heroArrayList.add(chl.sorcererArrayList.get(rank));
                break;
            case(3):
                player.heroArrayList.add(chl.paladinArrayList.get(rank));
                break;
        }
    }
}
