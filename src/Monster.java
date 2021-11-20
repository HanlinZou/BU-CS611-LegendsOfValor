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
     * @param level   level of the monster
     * @param HP      health of the monster
     * @param damage  damage from the monster
     * @param defense defense of the monster
     * @param dodge   dodge of the monster
     */
    Monster(String name, int level, int HP, int damage, int defense, int dodge) {
        super(name, level);

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
        System.out.print(Color.PURPLE + "Name: " + name);
        System.out.print(" Level: " + level);
        System.out.print(" HP: " + currentHP + "/" + HP);
        System.out.print(" Defense: " + defense);
        System.out.println(" Damage: " + damage + Color.RESET);
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
