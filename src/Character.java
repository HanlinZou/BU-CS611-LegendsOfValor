/**
 * Character class maintains information about the contents of a Character.
 * Character class has a specified schema specified by a Character object and
 * groups of variables of a character.
 */
public abstract class Character implements Fight {
    protected String type;
    protected String name;
    protected int level;
    protected int HP;
    protected int currentHP;
    protected int x, y;

    /**
     * No-arg constructor
     */
    Character() {
        name = "";
        level = 0;
        HP = 0;
        currentHP = 0;
    }

    Character(String name, int level) {
        setName(name);
        setLevel(level);
        setHP();
    }

    /**
     * Set a character's type
     *
     * @param type type of the character
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set a character's name
     *
     * @param name name of the character
     */
    public void setName(String name) {
        this.name = name.replace("_", " ");
    }

    /**
     * Set a character's level
     *
     * @param level level of the character
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Set a character's health based on its level
     */
    public void setHP() {
        HP = level * 100;
    }

    /**
     * Set a character's current health
     *
     * @param HP character's current health
     */
    public void setCurrentHP(int HP) {
        currentHP = HP;
    }

    /**
     * Set a character's position
     *
     * @param x X position
     * @param y Y position
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get a character's type
     *
     * @return type of the character
     */
    public String getType() {
        return type;
    }

    /**
     * Get a character's name
     *
     * @return name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Get a character's level
     *
     * @return level of the character
     */
    public int getLevel() {
        return level;
    }

    /**
     * Get a character's health
     *
     * @return character's health
     */
    public int getHP() {
        return HP;
    }

    /**
     * Get a character's current health
     *
     * @return character's current health
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Get a character's X position
     *
     * @return X position
     */
    public int getX() {
        return x;
    }

    /**
     * Get a character's Y position
     *
     * @return Y position
     */
    public int getY() {
        return y;
    }

    /**
     * this method performs a basic attack process between two characters
     *
     * @param opponent Hero/Monster whoever is attacked
     */
    public void regularAttack(Character opponent) {
        // calculate the damage based on damage and defense
        int damageRes = attackDamage() - opponent.calDefense();

        // check whether the attack is dodged
        boolean isHit = Math.random() > opponent.probDodge();
        if (isHit) {
            String color = (opponent instanceof Hero) ? Color.RED : Color.BLUE;
            System.out.println(color + getName() + " attacked and dealt " + damageRes
                + " points of damage to " + opponent.getName() + Color.RESET);
            opponent.setCurrentHP(opponent.getCurrentHP() - damageRes);
        } else {
            System.out.println(Color.GREY + "Unfortunately " + getName() + " missed." + Color.RESET);
        }
    }


}
