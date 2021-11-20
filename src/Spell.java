/**
 * Spell class maintains information about the contents of a Spell Item.
 * Spell class has a more specified schema extended from Item object.
 * Spell examples can be found in FireSpells.txt, IceSpells.txt and LightningSpells.txt
 */
public abstract class Spell extends Item implements Castable {
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

    public void setType(String type) {
        this.type = type;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int damageInFight() {
        return damage / 4;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Cost: " + cost + " Min LV: " + minLv + " Damage: " +
                getDamage() + " Mana cost: " + getManaCost() + " Spell cate: " + getType();
    }
    
    @Override
    public boolean castable(int heroMana)
    {
    	if(heroMana - this.manaCost >= 0)
    	{
    		return true;
    	}
		return false;
    }
}
