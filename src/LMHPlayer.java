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

        System.out.println(Color.ORANGE +"Heroes: " + Color.RESET);

        String border =
            Color.ORANGE +
            "-----------------------------------------------------------------------------------------------------" +
            Color.RESET + "\n";

        String title =
            "Name                       Type        Level    HP        MP        Equipped Weapon    Equipped Armor";

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        for (int i = 0; i < heroArrayList.size(); i++) {
            Hero hero = heroArrayList.get(i);

            String name = (i + 1) + ". " + hero.getName();
            String type = hero.getType();

            int level = hero.getLevel();
            String hp = hero.getCurrentHP() + "/" + hero.getHP();
            String mp = hero.getCurrentMP() + "/" + hero.getMP();

            Weapon weapon = hero.getEquippedWeapon();
            Armor armor = hero.getEquippedArmor();
            String weaponName = (weapon != null) ? weapon.getName() + "(" + weapon.getDamage() + ")" : "null";
            String armorName = (armor != null) ? armor.getName() + "(" + armor.getDefense() + ")"  : "null";

            System.out.printf(
                "%-26s %-11s %-8d %-9s %-9s %-18s %-18s",
                name, type, level, hp, mp, weaponName, armorName
            );
            System.out.println();
        }

        System.out.println(border);
    }

    /**
     * Display all heroes' info when in fight
     */
    public void displayInfoNotInFight() {
        if (heroArrayList.isEmpty()) return;

        System.out.println(Color.ORANGE +"Heroes: " + Color.RESET);

        String border =
            Color.ORANGE +
            "----------------------------------------------------------------------------------------------------------------" +
            Color.RESET + "\n";

        String title =
            "Name                       Type      Level        HP      MP    Strength    Dexterity    Agility   Money     Exp";

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        for (int i = 0; i < heroArrayList.size(); i++) {
            Hero hero = heroArrayList.get(i);

            String name = (i + 1) + ". " + hero.getName();
            String type = hero.getType();

            int level = hero.getLevel();
            String exp = hero.getCurrentXP() + "/" + hero.getXP();
            int money = hero.getMoney();

            String hp = hero.getCurrentHP() + "/" + hero.getHP();
            String mp = hero.getCurrentMP() + "/" + hero.getMP();

            int strength = hero.getStrength();
            int dexterity = hero.getDexterity();
            int agility = hero.getAgility();

            System.out.printf(
                "%-26s %-11s %-8d %-7s %-9s %-12d %-11d %-8d %-8d %-5s",
                name, type, level, hp, mp, strength, dexterity, agility , money, exp
            );
            System.out.println();
        }

        System.out.println(border);
    }

    /**
     * Display all heroes' info when in market
     */
    public void displayInfoInMarket() {
        String border =
            Color.ORANGE +
            "-----------------------------------------------------" +
            Color.RESET + "\n";

        String title =
            "Name                       Type        Level    Money";

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        for (int i = 0; i < heroArrayList.size(); i++) {
            Hero hero = heroArrayList.get(i);

            String name = (i + 1) + ". " + hero.getName();
            String type = hero.getType();

            int level = hero.getLevel();
            int money = hero.getMoney();

            System.out.printf("%-26s %-11s %-8d %-8d", name, type, level, money);
            System.out.println();
        }

        System.out.println(border);
    }
}
