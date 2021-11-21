import java.util.ArrayList;

/**
 * LMHPlayer class maintains information about the contents of a Player class in LMH games.
 * LMHPlayer class has a more specified schema extended from Player object.
 */
public class LMHPlayer extends Player {
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
        if (heroArrayList.isEmpty()) return;

        System.out.println(Color.getColor().ORANGE +"Heroes: " + Color.getColor().RESET);

        String border =
            Color.getColor().ORANGE +
            "-----------------------------------------------------------------------------------------------------" +
            Color.getColor().RESET;

        String title =
            "Name                       Type        Level    HP        MP        Equipped Weapon    Equipped Armor";

        System.out.println(border);
        System.out.println(title);
        System.out.println(border);

        for (int i = 0; i < heroArrayList.size(); i++) {
            Hero hero = heroArrayList.get(i);
            hero.displayInfoInFight(false, i + 1);
        }
        System.out.println(border);
    }

    /**
     * Display all heroes' info when in fight
     */
    public void displayInfoNotInFight() {
        if (heroArrayList.isEmpty()) return;

        System.out.println(Color.getColor().ORANGE +"Heroes: " + Color.getColor().RESET);

        String border =
            Color.getColor().ORANGE +
            "----------------------------------------------------------------------------------------------------------------" +
            Color.getColor().RESET;

        String title =
            "Name                       Type      Level        HP      MP    Strength    Dexterity    Agility   Money     Exp";

        System.out.println(border);
        System.out.println(title);
        System.out.println(border);

        for (int i = 0; i < heroArrayList.size(); i++) {
            Hero hero = heroArrayList.get(i);
            hero.displayInfoNotInFight(false, i + 1);
        }

        System.out.println(border);
    }

    /**
     * Display all heroes' info when in market
     */
    public void displayInfoInMarket() {
        if (heroArrayList.isEmpty()) return;

        String border =
            Color.getColor().ORANGE +
            "-----------------------------------------------------" +
            Color.getColor().RESET;

        String title =
            "Name                       Type        Level    Money";

        System.out.println(border);
        System.out.println(title);
        System.out.println(border);

        for (int i = 0; i < heroArrayList.size(); i++) {
            Hero hero = heroArrayList.get(i);
            hero.displayInfoInMarket(false, i + 1);
        }

        System.out.println(border);
    }
}
