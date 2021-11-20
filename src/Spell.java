/**
 * Spell class maintains information about the contents of a Spell Item.
 * Spell class has a more specified schema extended from Item object.
 * Spell examples can be found in FireSpells.txt, IceSpells.txt and LightningSpells.txt
 */
public abstract class Spell extends Item {
    private int damage;
    private int manaCost;
    private String type;

    /**
     * No-arg constructor
     */
    Spell() {
        damage = 0;
        manaCost = 0;
    }

    /**
     * Constructor of Spell object
     *
     * @param type     type of the spell
     * @param name     name of the spell
     * @param cost     money to purchase the spell
     * @param minLV    minimum level to purchase and learn the spell
     * @param damage   damage the spell can make
     * @param manaCost amount of mana the spell costs
     */
    Spell(String type, String name, int cost, int minLV, int damage, int manaCost) {
        setType(type);
        setName(name);
        setCost(cost);
        setMinLv(minLV);
        setDamage(damage);
        setManaCost(manaCost);
    }

    /**
     * Sets type for this spell (fire / ice / lightning)
     *
     * @param type Spell type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets base demage of this spell.
     *
     * @param damage Spell base damage.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Sets the mana value this spell would cost.
     *
     * @param manaCost Mana value to be cost.
     */
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    /**
     * Returns the type for this spell (fire / ice / lightning)
     *
     * @return Spell type.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the base demage of this spell.
     *
     * @return Spell base damage.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the mana value this spell would cost.
     *
     * @return Mana value to be cost.
     */
    public int getManaCost() {
        return manaCost;
    }

    /**
     * Computes the damage this spell can cause in fights.
     *
     * @return Spell damage.
     */
    public int damageInFight() {
        return damage / 4;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Cost: " + cost + " Min LV: " + minLv + " Damage: " +
                getDamage() + " Mana cost: " + getManaCost() + " Spell cate: " + getType();
    }
}
