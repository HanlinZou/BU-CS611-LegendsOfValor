/**
 * Potion class maintains information about the contents of a Potion Item.
 * Potion class has a more specified schema extended from Item object.
 * Potion examples can be found in Potions.txt
 */
public class Potion extends Item {
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
     * @param statCategory category of hero's stats to be inproved
     */
    Potion(String name, int cost, int minLV, int statInc, String statCategory) {
        setName(name);
        setCost(cost);
        setMinLv(minLV);
        setStatInc(statInc);
        setStatCategory(statCategory);
    }

    public void setStatInc(int statInc) {
        this.statInc = statInc;
    }

    public void setStatCategory(String statCategory) {
        this.statCategory = statCategory;
    }

    public int getStatInc() {
        return statInc;
    }

    public String getStatCategory() {
        return statCategory;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Cost: " + cost + " Min LV: " +
                minLv + " Attr Inc: " + statInc + " Attr aff: " + statCategory;
    }
}
