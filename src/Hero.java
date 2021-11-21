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
    //for LoV
    boolean buffed = false;
    int buffAmt = 0;
    String buffType = "";

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

    /**
     * Sets the current buff (increased) portion on hero.
     *
     * @param buffed Buff portion.
     */
    public void setBuffed(boolean buffed){
        this.buffed = buffed;
    }

    /**
     * Returns the current buff (increased) portion of this hero.
     *
     * @return Buff portion.
     */
    public boolean getBuffed() {
        return buffed;
    }

    /**
     * Sets the current buff type (which attribute to increase) on hero.
     *
     * @param type Buff type.
     */
    public void setBuffType(String type) {
        buffType = String.valueOf(type);
    }

    /**
     * Returns the current buff type (which attribute to increase) on hero.
     *
     * @return Buff type.
     */
    public String getBuffType() {
        return buffType;
    }

    /**
     * Sets the current buff (increased) value on hero.
     *
     * @param amt Buff value.
     */
    public void setBuffAmt(int amt) {
        buffAmt = amt;
    }

    /**
     * Returns the current buff (increased) value of this hero.
     *
     * @return Buff value.
     */
    public int getBuffAmt(){
        return buffAmt;
    }

    /**
     * Sets hero's mana.
     *
     * @param MP Mana value.
     */
    public void setMP(int MP) {
        this.MP = MP;
    }

    /**
     * Sets hero's current mana.
     *
     * @param MP Current Mana value.
     */
    public void setCurrentMP(int MP) {
        currentMP = MP;
    }

    /**
     * Sets hero's strength.
     *
     * @param strength Hero's strength.
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Sets hero's agility.
     *
     * @param agility Hero's agility.
     */
    public void setAgility(int agility) {
        this.agility = agility;
    }

    /**
     * Sets hero's dexterity.
     *
     * @param dexterity Hero's dexterity.
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * Sets hero's defense.
     *
     * @param defense Hero's defense.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Sets hero's money.
     *
     * @param money Hero's money.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Sets hero's experience points (EXP) to level * 10.
     */
    public void setXP() {
        XP = level * 10;
    }

    /**
     * Sets hero's current EXP.
     *
     * @param XP Current EXP.
     */
    public void setCurrentXP(int XP) {
        currentXP = XP;
    }

    /**
     * Equips a weapon.
     *
     * @param weapon A weapon object to be equipped.
     */
    public void setEquippedWeapon(Weapon weapon) {
        equippedWeapon = weapon;
    }

    /**
     * Equips an armor.
     *
     * @param armor An armor object to be equipped.
     */
    public void setEquippedArmor(Armor armor) {
        equippedArmor = armor;
    }

    /**
     * Returns hero's mana value.
     *
     * @return Mana value.
     */
    public int getMP() {
        return MP;
    }

    /**
     * Returns hero's current mana value.
     *
     * @return Current mana value.
     */
    public int getCurrentMP() {
        return currentMP;
    }

    /**
     * Returns hero's EXP.
     *
     * @return EXP.
     */
    public int getXP() {
        return XP;
    }

    /**
     * Returns hero's current EXP.
     *
     * @return Current EXP.
     */
    public int getCurrentXP() {
        return currentXP;
    }

    /**
     * Returns hero's strength.
     *
     * @return Hero's strength.
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Returns hero's dexterity.
     *
     * @return Hero's dexterity.
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * Returns hero's agility.
     *
     * @return Hero's agility.
     */
    public int getAgility() {
        return agility;
    }

    /**
     * Returns hero's defense.
     *
     * @return Hero's defense.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Returns hero's money.
     *
     * @return Hero's money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Returns hero's equipped weapon.
     *
     * @return Hero's equipped weapon.
     */
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Returns hero's equipped armor.
     *
     * @return Hero's equipped armor.
     */
    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    /**
     * Returns a list of items in this hero's storage.
     *
     * @return A list of items in storage.
     */
    public ArrayList<Item> getStorage() {
        return storage;
    }

    /**
     * Returns a list of spells that has been learned by this hero.
     *
     * @return A list of learned spells.
     */
    public ArrayList<Spell> getLearnedSpell() {
        return learnedSpell;
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
    @Override
    public double probDodge() {
        return agility * 0.002 * 0.01;
    }

    /**
     * Display all items a hero equips and inside its package
     */
    public void displayEquips() {
        System.out.println(Color.getColor().ORANGE + "Name: " + name);
        System.out.println("1. Equipped Weapon: " + equippedWeapon);
        System.out.println("2. Equipped Armor: " + equippedArmor);
        for (int i = 0; i < storage.size(); i++) {
            System.out.println(Color.getColor().ORANGE + (i + 3) + ". " + storage.get(i) + Color.getColor().RESET);
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
            System.out.println(Color.getColor().GREEN + (i + 1) + ". " + getLearnedSpell().get(i));
        }
        System.out.print(Color.getColor().YELLOW + "Which spell you want to cast: ");
        decision = sc.next();
        while (!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1
                || Integer.parseInt(decision) > getLearnedSpell().size()) {
            System.out.print(Color.getColor().RED + "Your selection is invalid, try again: " + Color.getColor().RESET);
            decision = sc.next();
        }

        // after validate the decision, we calculate its damage
        int spellNo = Integer.parseInt(decision) - 1;
        int spellDamage = getLearnedSpell().get(spellNo).damageInFight() + getDexterity() / 20 - monster.calDefense();

        // if player pick a spell that doesn't have enough mana to cast, automatically
        // switch a regular attack
        if (getCurrentMP() < getLearnedSpell().get(Integer.parseInt(decision) - 1).getCost()) {
            System.out.println(Color.getColor().RED + "You don't have enough mana, now regular attack...");
            regularAttack(monster);
        } else {
            // decrease hero's current mana
            setCurrentMP(getCurrentMP() - getLearnedSpell().get(spellNo).getCost());
            System.out.println(Color.getColor().BLUE + getName() + " casted " + getLearnedSpell().get(spellNo).getName()
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
            System.out.println(Color.getColor().GREEN + "Equipped Weapon is taken off now.");
            displayEquips();
        } else if (itemNo == 2 && getEquippedArmor() != null) {
            // move equipped armor to inventory and set armor on body to empty
            getStorage().add(getEquippedArmor());
            setEquippedArmor(null);
            System.out.println(Color.getColor().GREEN + "Equipped Armor is taken off now.");
            displayEquips();
        } else {
            if (itemNo == 1)
                System.out.println(Color.getColor().RED + "You failed to take off a weapon. Verify if you have one equipped");
            else
                System.out.println(Color.getColor().RED + "You failed to take off an armor. Verify if you have one equipped");
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
                System.out.println(Color.getColor().GREEN + "Switching Weapon...");
                takeOff(1);
            }
            setEquippedWeapon((Weapon) getStorage().remove(itemNo));
            System.out.println(Color.getColor().GREEN + "You successfully equipped a weapon.");
        } else if (getStorage().get(itemNo) instanceof Armor) {
            // if hero already has an armor on, switch them
            if (getEquippedArmor() != null) {
                System.out.println(Color.getColor().GREEN + "Switching Armor...");
                takeOff(2);
            }
            setEquippedArmor((Armor) getStorage().remove(itemNo));
            System.out.println("You successfully equipped an armor.");
        } else {
            System.out.println(Color.getColor().RED + "You failed to equip a weapon/armor.");
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
                System.out.println(Color.getColor().RED + "This hero already learned this spell.");
                learned = true;
                break;
            }
        }

        if (!learned) {
            getLearnedSpell().add((Spell) getStorage().remove(itemNo));
            System.out.println(Color.getColor().GREEN + "You successfully learned a new spell.");
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
        System.out.println(Color.getColor().GREEN + "Woo, you drank so you feeling good~");
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
            System.out.print(Color.getColor().YELLOW + getName() + ", which item you want to operate: ");
            decision = sc.next();
            // validate input
            while (!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1
                    || Integer.parseInt(decision) > getStorage().size() + 2) {
                System.out.print(Color.getColor().RED + "Your selection is invalid, try again: " + Color.getColor().RESET);
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
                        System.out.println(Color.getColor().RED + "You can't learn a spell during a fight!");
                        itemOp(true);
                    }
                }
            }

            // ask if player wants another operation if not during a fight
            if (!inFight) {
                System.out.print(Color.getColor().YELLOW + "Are you done? Input \"N\" to do another operation: ");
                isDone = sc.next();
                if (!isDone.equalsIgnoreCase("N"))
                    done = true;
            } else
                done = true;
        }
    }

    /**
     * Display hero's info when not in fight
     *
     * @param displayTitle Display table title or not?
     * @param index Index of the hero, -1 for no index.
     */
    @Override
    public void displayInfoInFight(boolean displayTitle, int index) {
        String border =
            Color.getColor().ORANGE +
            "-----------------------------------------------------------------------------------------------------" +
            Color.getColor().RESET;

        if (displayTitle) {
            System.out.println(Color.getColor().ORANGE +"Hero " + getName() + ":" + Color.getColor().RESET);

            String title =
                "Name                       Type        Level    HP        MP        Equipped Weapon    Equipped Armor";

            System.out.println(border);
            System.out.println(title);
            System.out.println(border);
        }

        String name = index > -1 ? index + ". " + getName() : getName();
        String type = getType();

        int level = getLevel();
        String hp = getCurrentHP() + "/" + getHP();
        String mp = getCurrentMP() + "/" + getMP();

        Weapon weapon = getEquippedWeapon();
        Armor armor = getEquippedArmor();
        String weaponName = (weapon != null) ? weapon.getName() + "(" + weapon.getDamage() + ")" : "null";
        String armorName = (armor != null) ? armor.getName() + "(" + armor.getDefense() + ")"  : "null";

        System.out.printf(
            "%-26s %-11s %-8d %-9s %-9s %-18s %-18s",
            name, type, level, hp, mp, weaponName, armorName
        );
        System.out.println();

        if (displayTitle) System.out.println(border);
    }

    /**
     * Display hero's info when in fight
     */
    public void displayInfoNotInFight(boolean displayTitle, int index) {
        String border =
            Color.getColor().ORANGE +
            "----------------------------------------------------------------------------------------------------------------" +
            Color.getColor().RESET;

        if (displayTitle) {
            System.out.println(Color.getColor().ORANGE +"Hero " + getName() + ":" + Color.getColor().RESET);

            String title =
                "Name                       Type      Level        HP      MP    Strength    Dexterity    Agility   Money     Exp";

            System.out.println(border);
            System.out.println(title);
            System.out.println(border);
        }

        String name = index + ". " + getName();
        String type = getType();

        int level = getLevel();
        String exp = getCurrentXP() + "/" + getXP();
        int money = getMoney();

        String hp = getCurrentHP() + "/" + getHP();
        String mp = getCurrentMP() + "/" + getMP();

        int strength = getStrength();
        int dexterity = getDexterity();
        int agility = getAgility();

        System.out.printf(
            "%-26s %-11s %-8d %-7s %-9s %-12d %-11d %-8d %-8d %-5s",
            name, type, level, hp, mp, strength, dexterity, agility , money, exp
        );
        System.out.println();

        if (displayTitle) System.out.println(border);
    }

    /**
     * Display hero's info when in market
     */
    public void displayInfoInMarket(boolean displayTitle, int index) {
        String border =
            Color.getColor().ORANGE +
            "-----------------------------------------------------" +
            Color.getColor().RESET;

        if (displayTitle) {
            System.out.println(Color.getColor().ORANGE +"Hero " + getName() + ":" + Color.getColor().RESET);

            String title =
                "Name                       Type        Level    Money";

            System.out.println(border);
            System.out.println(title);
            System.out.println(border);
        }

        String name = index + ". " + getName();
        String type = getType();

        int level = getLevel();
        int money = getMoney();

        System.out.printf("%-26s %-11s %-8d %-8d", name, type, level, money);
        System.out.println();

        if (displayTitle) System.out.println(border);
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
