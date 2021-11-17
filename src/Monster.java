/**
 * Monster class maintains information about the contents of a Monster.
 * Monster class has a more specified schema extended from Character object.
 */
public class Monster extends Character {
    private int damage;
    private int defense;
    private int dodge;

    /**
     * No-arg constructor
     */
    Monster() {
    }

    /**
     * Constructor of Monster object
     *
     * @param name    name of the monster
     * @param LV      level of the monster
     * @param HP      health of the monster
     * @param damage  damage from the monster
     * @param defense defense of the monster
     * @param dodge   dodge of the monster
     */
    Monster(String name, int LV, int HP, int damage, int defense, int dodge) {
        setName(name);
        setLevel(LV);
        setHP();
        setCurrentHP(HP);
        setDamage(damage);
        setDefense(defense);
        setDodge(dodge);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getDodge() {
        return dodge;
    }

    /**
     * Display all monsters' info when in fight
     */
    public void displayInfo() {
        System.out.println(Color.PURPLE + "Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("HP: " + currentHP + "/" + HP);
        System.out.println("Defense: " + defense);
        System.out.println("Damage: " + damage + Color.RESET);
    }

    @Override
    public int attackDamage() {
        return (int) (damage * 0.25);
    }

    @Override
    public int calDefense() {
        return (int) (defense * 0.01);
    }

    @Override
    public double probDodge() {
        return dodge * 0.01 * 0.01;
    }
}
