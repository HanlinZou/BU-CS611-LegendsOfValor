public class IceSpell extends Spell {

    /**
     * No-arg constructor
     */
    IceSpell() {
        super();
    }

    /**
     * Constructor of ice Spell object
     *
     * @param name     name of the spell
     * @param cost     money to purchase the spell
     * @param minLV    minimum level to purchase and learn the spell
     * @param damage   damage the spell can make
     * @param manaCost amount of mana the spell costs
     */
    IceSpell(String name, int cost, int minLV, int damage, int manaCost) {
        super("Ice", name, cost, minLV, damage, manaCost);
    }
}
