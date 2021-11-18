import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hero class maintains information about the contents of a Hero.
 * Hero class has a more specified schema extended from Character object.
 */
public abstract class Hero extends Character {
    private int MP;
    private int currentMP;
    private int XP;
    private int currentXP;
    private int strength;
    private int dexterity;
    private int agility;
    private int defense;
    private int money;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    // all items that a hero has in his package/bag
    private ArrayList<Item> storage;
    private ArrayList<Spell> learnedSpell;

    /**
     * No-arg constructor
     */
    Hero() {
    }

    /**
     * Constructor of Hero object
     *
     * @param type      type of the hero
     * @param name      name of the hero
     * @param mana      mana of the hero
     * @param strength  strength of the hero
     * @param agility   agility of the hero
     * @param dexterity dexterity of the hero
     * @param initMoney initial money of the hero
     * @param initXP    initial experience of the hero
     */
    Hero(String type, String name, int mana, int strength, int agility, int dexterity, int initMoney, int initXP) {
        super(name, 1);

        setType(type);
        setCurrentHP(getHP());
        setMP(mana);
        setCurrentMP(mana);
        setStrength(strength);
        setAgility(agility);
        setDexterity(dexterity);
        setDefense(0);
        setMoney(initMoney);
        setXP();
        setCurrentXP(initXP);

        equippedWeapon = null;
        equippedArmor = null;
        storage = new ArrayList<>();
        learnedSpell = new ArrayList<>();
    }

    /**
     * A hero levels up.
     * 1. increase its level
     * 2. increase its maximum health & mana
     * 3. refill its health & mana
     * 4. reset its experience
     * 5. increase its experience needed to next level
     * 6. increase its skills (strength/agility/dexterity)
     */
    public void LVUP() {
        System.out.println("Congratulations, " + name + ", you leveled up!");
        setLevel(level + 1);
        setHP();
        setCurrentHP(HP);
        setMP((int) (MP * 1.1));
        setCurrentMP(MP);
        setCurrentXP(currentXP - XP);
        setXP();
        boostSkill();
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setCurrentMP(int MP) {
        currentMP = MP;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setXP() {
        XP = level * 10;
    }

    public void setCurrentXP(int XP) {
        currentXP = XP;
    }

    public void setEquippedWeapon(Weapon weapon) {
        equippedWeapon = weapon;
    }

    public void setEquippedArmor(Armor armor) {
        equippedArmor = armor;
    }

    public int getMP() {
        return MP;
    }

    public int getCurrentMP() {
        return currentMP;
    }

    public int getXP() {
        return XP;
    }

    public int getCurrentXP() {
        return currentXP;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public int getDefense() {
        return defense;
    }

    public int getMoney() {
        return money;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public ArrayList<Item> getStorage() {
        return storage;
    }

    public ArrayList<Spell> getLearnedSpell() {
        return learnedSpell;
    }

    /**
     * Get the input action for this hero.
     *
     * @param board A game map, for checking if the move is valid.
     */
    private String enterAction(LMHBoard board) {
        Scanner sc = new Scanner(System.in);
        String action;

        board.displayBoard();
        System.out.print(Color.YELLOW + "Enter " + "No." + ID + " Hero's move: " + Color.RESET);

        while (true) {
            action = sc.next();

            if (
                !action.equalsIgnoreCase("W") && !action.equalsIgnoreCase("A") &&
                !action.equalsIgnoreCase("S") && !action.equalsIgnoreCase("D") &&
                !action.equalsIgnoreCase("Q") && !action.equalsIgnoreCase("I")
            ) {
                System.out.print(Color.RED + "Invalid move, please re-inter it: " + Color.RESET);
            }

            else if (
                (action.equalsIgnoreCase("A") && (y - 1 < 0)) ||
                (action.equalsIgnoreCase("D") && (y + 1 >= board.size)) ||
                (action.equalsIgnoreCase("W") && (x - 1 < 0)) ||
                (action.equalsIgnoreCase("S") && (x + 1 >= board.size))
            ) {
                // Go outside the map
                System.out.print(Color.RED + "You can't go outside the map, please re-inter your move: " + Color.RESET);
            }

            else if (
                (action.equalsIgnoreCase("A") && board.getCell(x, y - 1).getMark().equals("X")) ||
                (action.equalsIgnoreCase("D") && board.getCell(x, y + 1).getMark().equals("X")) ||
                (action.equalsIgnoreCase("W") && board.getCell(x - 1, y).getMark().equals("X")) ||
                (action.equalsIgnoreCase("S") && board.getCell(x + 1, y).getMark().equals("X"))
            ) {
                // Move to Inaccessible Cell
                System.out.print(Color.RED + "The cell is inaccessible, please re-inter your move: " + Color.RESET);
            }

            else return action;
        }
    }

    /**
     * The hero takes an action.
     *
     * @param board A game map, for checking if the move is valid.
     */
    public String takeAction(LMHBoard board) {
        Scanner sc = new Scanner(System.in);
        String action = enterAction(board);

        if (
            action.equalsIgnoreCase("W") || action.equalsIgnoreCase("A") ||
            action.equalsIgnoreCase("S") || action.equalsIgnoreCase("D")
        ) {
            return move(action, board);
        } else {
            displayInfoNotInFight();
            System.out.print(Color.YELLOW + "Do you want to switch your weapon/armor, " +
                        "use potions, or learn a spell? Input \"Y\" to operate: ");
            String ans = sc.next();
            if (ans.equalsIgnoreCase("Y")) itemOp(false);

            return action;
        }
    }

    /**
     * The hero moves on the map.
     *
     * @param direction Move direction
     */
    public String move(String direction, LMHBoard board) {
        board.getCell(x, y).setMark(".");

        if (direction.equalsIgnoreCase("A")) y--;
        else if (direction.equalsIgnoreCase("D")) y++;
        else if (direction.equalsIgnoreCase("W")) x--;
        else if (direction.equalsIgnoreCase("S")) x++;

        String fromMark = board.getCell(x, y).getMark().toString();
        board.getCell(x, y).setHeroOn(true);

        // TO DO: maybe we'll handle events here?
        return fromMark;
    }

    /**
     * Calculate a hero's regular attack damage
     *
     * @return damage a hero's regular attack makes
     */
    public int attackDamage() {
        if (equippedWeapon != null)
            return (int) ((strength + equippedWeapon.getDamage()) * 0.05);
        else
            return (int) ((strength * 0.05));
    }

    /**
     * Calculate how much damage a hero can block when get attacked
     *
     * @return defense value
     */
    public int calDefense() {
        // if hero doesn't equip any armor, 0 defense
        if (equippedArmor != null)
            return (int) (equippedArmor.getDefense() * 0.01);
        else
            return 0;
    }

    /**
     * Calculate probability that a hero dodge a regular attack
     *
     * @return Probability that a hero dodge a regular attack
     */
    public double probDodge() {
        return agility * 0.002 * 0.01;
    }

    /**
     * Display all items a hero equips and inside its package
     */
    public void displayEquips() {
        System.out.println(Color.ORANGE + "Name: " + name);
        System.out.println("1. Equipped Weapon: " + equippedWeapon);
        System.out.println("2. Equipped Armor: " + equippedArmor);
        for (int i = 0; i < storage.size(); i++) {
            System.out.println(Color.ORANGE + (i + 3) + ". " + storage.get(i) + Color.RESET);
        }
    }

    /**
     * Following functions increase a hero's stats
     * based on kind of potion it took
     *
     * @param incAmt amount of points to increase
     */
    public void incHealth(int incAmt) {
        currentHP += incAmt;
    }

    public void incMana(int incAmt) {
        currentMP += incAmt;
    }

    public void incStrength(int incAmt) {
        strength += incAmt;
    }

    public void incDefense(int incAmt) {
        defense += incAmt;
    }

    public void incAgility(int incAmt) {
        agility += incAmt;
    }

    public void incDexterity(int incAmt) {
        dexterity += incAmt;
    }

    public void incAll(int incAmt) {
        incHealth(incAmt);
        incMana(incAmt);
        incStrength(incAmt);
        incDefense(incAmt);
        incAgility(incAmt);
        incDexterity(incAmt);
    }

    /**
     * Regain health & mana after each round in fight
     */
    public void heal() {
        currentHP += 0.1 * HP;
        currentMP += 0.1 * MP;
    }

    /**
     * reward winning & survived hero
     */
    public void win() {
        setMoney(money + 100 * level);
        setCurrentXP(currentXP + 2);
    }

    /**
     * punish lost heroes
     */
    public void lose() {
        revive();
        money /= 2;
    }

    /**
     * reward winning but died heroes OR
     * punish lost & died heroes
     */
    public void revive() {
        currentHP = HP / 2;
        currentMP = 0;
    }

    /**
     * This method performs a spell process from a hero to a monster
     *
     * @param monster monster who takes the spell
     */
    public void castSpell(Monster monster) {
        Scanner sc = new Scanner(System.in);
        String decision;

        // prompt for player's decision
        for (int i = 0; i < getLearnedSpell().size(); i++) {
            System.out.println(Color.GREEN + (i + 1) + ". " + getLearnedSpell().get(i));
        }
        System.out.print(Color.YELLOW + "Which spell you want to cast: ");
        decision = sc.next();
        while (!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1
                || Integer.parseInt(decision) > getLearnedSpell().size()) {
            System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
            decision = sc.next();
        }

        // after validate the decision, we calculate its damage
        int spellNo = Integer.parseInt(decision) - 1;
        int spellDamage = getLearnedSpell().get(spellNo).damageInFight() + getDexterity() / 20 - monster.calDefense();

        // if player pick a spell that doesn't have enough mana to cast, automatically
        // switch a regular attack
        if (getCurrentMP() < getLearnedSpell().get(Integer.parseInt(decision) - 1).getCost()) {
            System.out.println(Color.RED + "You don't have enough mana, now regular attack...");
            regularAttack(monster);
        } else {
            // decrease hero's current mana
            setCurrentMP(getCurrentMP() - getLearnedSpell().get(spellNo).getCost());
            System.out.println(Color.BLUE + getName() + " casted " + getLearnedSpell().get(spellNo).getName()
                    + " and dealt " + spellDamage + " points of damage to " + monster.getName());

            // spells can't be dodged, so we decrease monster's health and related skills data
            monster.setCurrentHP(monster.getCurrentHP() - spellDamage);
            if (getLearnedSpell().get(spellNo) instanceof FireSpell)
                monster.setDefense((int) (monster.getDefense() * 0.9));
            else if (getLearnedSpell().get(spellNo) instanceof IceSpell)
                monster.setDamage((int) (monster.getDamage() * 0.9));
            else
                monster.setDodge((int) (monster.getDodge() * 0.9));
        }
    }

    /**
     * The method performs a hero take off a weapon/armor
     *
     * @param itemNo indicates if it's weapon or armor
     */
    public void takeOff(int itemNo) {
        if (itemNo == 1 && getEquippedWeapon() != null) {
            // move equipped weapon to inventory and set weapon in hand to empty
            getStorage().add(getEquippedWeapon());
            setEquippedWeapon(null);
            System.out.println(Color.GREEN + "Equipped Weapon is taken off now.");
            displayEquips();
        } else if (itemNo == 2 && getEquippedArmor() != null) {
            // move equipped armor to inventory and set armor on body to empty
            getStorage().add(getEquippedArmor());
            setEquippedArmor(null);
            System.out.println(Color.GREEN + "Equipped Armor is taken off now.");
            displayEquips();
        } else {
            if (itemNo == 1)
                System.out.println(Color.RED + "You failed to take off a weapon. Verify if you have one equipped");
            else
                System.out.println(Color.RED + "You failed to take off an armor. Verify if you have one equipped");
        }
    }

    /**
     * The method performs a hero equip a weapon/armor
     *
     * @param itemNo index in inventory
     */
    public void takeOn(int itemNo) {
        if (getStorage().get(itemNo) instanceof Weapon) {
            // if hero already has a weapon on, switch them
            if (getEquippedWeapon() != null) {
                System.out.println(Color.GREEN + "Switching Weapon...");
                takeOff(1);
            }
            setEquippedWeapon((Weapon) getStorage().remove(itemNo));
            System.out.println(Color.GREEN + "You successfully equipped a weapon.");
        } else if (getStorage().get(itemNo) instanceof Armor) {
            // if hero already has an armor on, switch them
            if (getEquippedArmor() != null) {
                System.out.println(Color.GREEN + "Switching Armor...");
                takeOff(2);
            }
            setEquippedArmor((Armor) getStorage().remove(itemNo));
            System.out.println("You successfully equipped an armor.");
        } else {
            System.out.println(Color.RED + "You failed to equip a weapon/armor.");
        }
        displayEquips();
    }

    /**
     * The method performs learning spells process
     *
     * @param itemNo index in inventory
     */
    public void learnSpell(int itemNo) {
        // get the name of the spell
        String spellName = getStorage().get(itemNo).getName();
        boolean learned = false;

        // and check whether the hero has learned it already
        for (int i = 0; i < getLearnedSpell().size(); i++) {
            if (getLearnedSpell().get(i).getName().equals(spellName)) {
                System.out.println(Color.RED + "This hero already learned this spell.");
                learned = true;
                break;
            }
        }

        if (!learned) {
            getLearnedSpell().add((Spell) getStorage().remove(itemNo));
            System.out.println(Color.GREEN + "You successfully learned a new spell.");
        }
    }

    /**
     * The method performs drinking potions process
     *
     * @param potion potion to be drunk
     */
    public void drinkPotion(Potion potion) {
        // get the potion's name
        String attrEff = potion.getStatCategory();
        int incAmt = potion.getStatInc();
        String[] attr = attrEff.split("/");
        System.out.println(Color.GREEN + "Woo, you drank so you feeling good~");
        // after acquiring all data, improve hero's skills or stats
        for (String s : attr) {
            switch (s) {
                case "Health":
                    incHealth(incAmt);
                    break;
                case "Mana":
                    incMana(incAmt);
                    break;
                case "Strength":
                    incStrength(incAmt);
                    break;
                case "Defense":
                    incDefense(incAmt);
                    break;
                case "Agility":
                    incAgility(incAmt);
                    break;
                case "Dexterity":
                    incDexterity(incAmt);
                    break;
                default:
                    incAll(incAmt);
                    break;
            }
        }
    }

    /**
     * Used to switch equipments, drink potion, and learn spells
     *
     * @param inFight whether during a fight
     */
    public void itemOp(boolean inFight) {
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        String isDone;
        String decision;

        while (!done) {
            // display equipped items and inventory
            displayEquips();
            System.out.print(Color.YELLOW + getName() + ", which item you want to operate: ");
            decision = sc.next();
            // validate input
            while (!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1
                    || Integer.parseInt(decision) > getStorage().size() + 2) {
                System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                decision = sc.next();
            }
            int itemNo = Integer.parseInt(decision);

            // take off equipped weapon
            if (itemNo < 3)
                takeOff(itemNo);
            else {
                // inventory operation so -3
                itemNo -= 3;
                // equip weapon/armor
                if (getStorage().get(itemNo) instanceof Weapon || getStorage().get(itemNo) instanceof Armor)
                    takeOn(itemNo);
                // drink potion
                else if (getStorage().get(itemNo) instanceof Potion) {
                    drinkPotion((Potion) getStorage().get(itemNo));
                    getStorage().remove(itemNo);
                }
                // learn a spell
                else {
                    if (!inFight)
                        learnSpell(itemNo);
                    else {
                        System.out.println(Color.RED + "You can't learn a spell during a fight!");
                        itemOp(true);
                    }
                }
            }

            // ask if player wants another operation if not during a fight
            if (!inFight) {
                System.out.print(Color.YELLOW + "Are you done? Input \"N\" to do another operation: ");
                isDone = sc.next();
                if (!isDone.equalsIgnoreCase("N"))
                    done = true;
            } else
                done = true;
        }
    }

    /**
     * Display the hero's info when in fight
     */
    public void displayInfoNotInFight() {
        String border =
            Color.ORANGE +
            "----------------------------------------------------------------------------------------------------------------" +
            Color.RESET + "\n";

        String title =
            "Name                       Type        Level    HP      MP      Strength    Dexterity    Agility    Money    Exp";

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        System.out.printf(
            "%-26s %-11s %-8d %-7d %-9d %-12d %-11d %-8d %-8d %-5d",
            name, type, level, HP, MP, strength, dexterity, agility , money, XP
        );
        System.out.println();

        System.out.println(border);
    }

    /**
     * Display the hero's info when in market
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

        System.out.printf("%-26s %-11s %-8d %-8d", name, type, level, money);
        System.out.println();

        System.out.println(border);
    }

    @Override
    public String toString() {
        return getName() + " Mana: " + MP + " Strength: " + strength + " Agility: " +
                agility + " Dexterity: " + dexterity + " Initial Money: " + money +
                " Initial XP: " + currentXP;
    }

    /**
     * Increase hero's skills based on its favorites
     */
    public abstract void boostSkill();
}
