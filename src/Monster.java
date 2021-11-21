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
     * Display monster's info when in fight
     */
    public void displayInfoInFight(boolean displayTitle, int index) {
        String border = Color.getColor().PURPLE +
            "-------------------------------------------------------------------" +
            Color.getColor().RESET;


        if (displayTitle) {
            System.out.println(Color.getColor().PURPLE +"Monster " + getName() + ":" + Color.getColor().RESET);

            System.out.println(border);
            System.out.println("Name                       Level    HP        Damage    Defense");
            System.out.println(border);
        }

        String name = index > -1 ? index + ". " + getName() : getName();
        String hp = getCurrentHP() + "/" + getHP();
        int level = getLevel();
        int damage = getDamage();
        int defense = getDefense();

        System.out.printf("%-26s %-8d %-9s %-9d %-11d", name, level, hp, damage, defense);
        System.out.println();

        if (displayTitle) System.out.println(border);
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
