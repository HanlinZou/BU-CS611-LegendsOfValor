/**
 * LightningSpell class maintains information and actions for a lightning spell.
 */

public class LightningSpell extends Spell {
    /**
     * No-arg constructor
     */
    LightningSpell() {
        super();
    }

    /**
     * Constructor of lightning Spell object
     *
     * @param name     name of the spell
     * @param cost     money to purchase the spell
     * @param minLV    minimum level to purchase and learn the spell
     * @param damage   damage the spell can make
     * @param manaCost amount of mana the spell costs
     */
    LightningSpell(String name, int cost, int minLV, int damage, int manaCost) {
        super("Lightning", name, cost, minLV, damage, manaCost);
    }
}
