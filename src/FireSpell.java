/**
 * FireSpell class maintains information and actions for a fire spell.
 */

public class FireSpell extends Spell {
    /**
     * No-arg constructor
     */
    FireSpell() {
        super();
    }

    /**
     * Constructor of fire Spell object
     *
     * @param name     name of the spell
     * @param cost     money to purchase the spell
     * @param minLV    minimum level to purchase and learn the spell
     * @param damage   damage the spell can make
     * @param manaCost amount of mana the spell costs
     */
    FireSpell(String name, int cost, int minLV, int damage, int manaCost) {
        super("Fire", name, cost, minLV, damage, manaCost);
    }
}
