import java.util.ArrayList;

/**
 * LMHPlayer class maintains information about the contents of a Player class in LMH games.
 * LMHPlayer class has a more specified schema extended from Player object.
 */
public class LMHPlayer extends Player {
    public static String ANSI_RESET = "\u001b[0m";
    public static String ANSI_INFO = "\u001b[38;5;106m";

    // one player has at most three heroes
    public ArrayList<Hero> heroArrayList = new ArrayList<>(3);

    /**
     * Multiple players constructor
     *
     * @param pName player's name
     * @param tName team name
     * @param tId   team id
     */
    LMHPlayer(String pName, String tName, int tId) {
        super(pName, tName, tId);
    }

    /**
     * single player constructor
     *
     * @param pName single player's name
     */
    LMHPlayer(String pName) {
        super(pName, "No team", 0);
    }

    /**
     * No-arg constructor
     */
    LMHPlayer() {
        super("No name");
    }

    /**
     * Display all heroes' info when not in fight
     */
    public void displayInfoInFight() {
        for (int i = 0; i < heroArrayList.size(); i++) {
            System.out.println(ANSI_INFO + "Hero " + (i + 1));
            System.out.println("Name: " + heroArrayList.get(i).getName());
            System.out.println("Level: " + heroArrayList.get(i).getLevel());
            System.out.println("HP: " + heroArrayList.get(i).getCurrentHP() + "/" + heroArrayList.get(i).getHP());
            System.out.println("MP: " + heroArrayList.get(i).getCurrentMP() + "/" + heroArrayList.get(i).getMP());
            System.out.println("Equipped Weapon: " + heroArrayList.get(i).getEquippedWeapon());
            System.out.println("Equipped Armor: " + heroArrayList.get(i).getEquippedArmor() + ANSI_RESET);
        }
    }

    /**
     * Display all heroes' info when in fight
     */
    public void displayInfoNotInFight() {
        for (int i = 0; i < heroArrayList.size(); i++) {
            System.out.println(ANSI_INFO + "Hero " + (i + 1));
            System.out.println("Name: " + heroArrayList.get(i).getName());
            System.out.println("Level: " + heroArrayList.get(i).getLevel());
            System.out.println("HP: " + heroArrayList.get(i).getCurrentHP() + "/" + heroArrayList.get(i).getHP());
            System.out.println("MP: " + heroArrayList.get(i).getCurrentMP() + "/" + heroArrayList.get(i).getMP());
            System.out.println("XP: " + heroArrayList.get(i).getCurrentXP() + "/" + heroArrayList.get(i).getXP());
            System.out.println("Strength: " + heroArrayList.get(i).getStrength());
            System.out.println("Agility: " + heroArrayList.get(i).getAgility());
            System.out.println("Dexterity: " + heroArrayList.get(i).getDexterity());
            System.out.println("Money: " + heroArrayList.get(i).getMoney() + ANSI_RESET);
        }
    }
}
