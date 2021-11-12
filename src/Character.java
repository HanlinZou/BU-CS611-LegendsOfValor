/**
 * Character class maintains information about the contents of a Character.
 * Character class has a specified schema specified by a Character object and
 * groups of variables of a character.
 */
public abstract class Character {
    public static String ANSI_BADNEWS = "\u001b[38;5;124m";
    public static String ANSI_HIT = "\u001b[38;5;87m";
    public static String ANSI_MISS = "\u001b[38;5;246m";

    protected String name;
    protected int level;
    protected int HP;
    protected int currentHP;
    public int[] location = new int[2];

    /**
     * No-arg constructor
     */
    Character(){
        name = "";
        level = 0;
        HP = 0;
        currentHP = 0;
    }

    /**
     * Set a character's name
     * @param name name of the character
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Set a character's level
     * @param level level of the character
     */
    public void setLevel(int level){
        this.level = level;
    }

    /**
     * Set a character's health based on its level
     */
    public void setHP(){
        HP = level * 100;
    }

    /**
     * Set a character's current health
     * @param HP character's current health
     */
    public void setCurrentHP(int HP){
        currentHP = HP;
    }

    /**
     * Get a character's name
     * @return name of the character
     */
    public String getName(){
        return name;
    }

    /**
     * Get a character's level
     * @return level of the character
     */
    public int getLevel(){
        return level;
    }

    /**
     * Get a character's health
     * @return character's health
     */
    public int getHP(){
        return HP;
    }

    /**
     * Get a character's current health
     * @return character's current health
     */
    public int getCurrentHP(){
        return currentHP;
    }

    /**
     * this method performs a basic attack process between two characters
     * @param defence Hero/Monster whoever is attacked
     */
    public void regularAttack(Character defence){
        //calculate the damage based on damage and defense
        int damageRes = attackDamage() - defence.calDefense();

        //check whether the attack is dodged
        boolean ifHit = Math.random() > defence.probDodge();
        if(ifHit){
            if(defence instanceof Hero)
                System.out.println(ANSI_BADNEWS + getName() + " attacked and dealt " + damageRes
                        + " points of damage to " + defence.getName());
            else
                System.out.println(ANSI_HIT + getName() + " attacked and dealt " + damageRes
                        + " points of damage to " + defence.getName());
            defence.setCurrentHP(defence.getCurrentHP() - damageRes);
        }
        else
            System.out.println(ANSI_MISS + "Unfortunately " + getName() + " missed.");
    }

    public abstract int attackDamage();
    public abstract int calDefense();
    public abstract double probDodge();
}
