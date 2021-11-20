/**
 * Potion class maintains information about the contents of a Potion Item.
 * Potion class has a more specified schema extended from Item object.
 * Potion examples can be found in Potions.txt
 */
public class Potion extends Item implements Consumable {
    private int statInc;
    private String statCategory;

    /**
     * No-arg constructor
     */
    Potion() {
        statInc = 0;
        statCategory = "";
    }

    /**
     * Constructor of Potion object
     *
     * @param name         name of potion
     * @param cost         how much money to purchase the potion
     * @param minLV        minimum level to purchase the potion
     * @param statInc      amount to be added to hero's stats
     * @param statCategory category of hero's stats to be improved
     */
    Potion(String name, int cost, int minLV, int statInc, String statCategory) {
        setName(name);
        setCost(cost);
        setMinLv(minLV);
        setStatInc(statInc);
        setStatCategory(statCategory);
    }

    /**
     * Sets the value this potion can improve.
     *
     * @param statInc Improved value.
     */
    public void setStatInc(int statInc) {
        this.statInc = statInc;
    }

    /**
     * Sets the attribute(s) this potion can improve.
     *
     * @param statCategory Improved attribute(s).
     */
    public void setStatCategory(String statCategory) {
        this.statCategory = statCategory;
    }

    /**
     * Returns the value this potion can improve.
     *
     * @return Improved value.
     */
    public int getStatInc() {
        return statInc;
    }

    /**
     * Returns the attribute(s) this potion can improve.
     *
     * @return Improved attribute(s).
     */
    public String getStatCategory() {
        return statCategory;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Cost: " + cost + " Min LV: " +
                minLv + " Attr Inc: " + statInc + " Attr aff: " + statCategory;
    }

	@Override
	public boolean isConsumable(int hero_level) {
        return super.minLv <= hero_level;
    }
}
