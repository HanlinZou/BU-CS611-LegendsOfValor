/**
 * Item class maintains information about the contents of an Item.
 * Item class has a specified schema specified by an Item object and
 * groups of variables of an item.
 */
public class Item implements Equipable, ItemTrade {
    protected String name;
    protected int cost;
    protected int minLv;

    /**
     * No-arg Constructor
     */
    Item() {
        name = "";
        cost = 0;
        minLv = 0;
    }

    /**
     * Set the name for this item.
     *
     * @param name Item name.
     */
    public void setName(String name) {
        this.name = name.replace("_", " ");
    }

    /**
     * Set price for this item.
     *
     * @param cost Item price.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Set the required level for this item.
     *
     * @param minLv Required level.
     */
    public void setMinLv(int minLv) {
        this.minLv = minLv;
    }

    /**
     * Returns the name of this item.
     *
     * @return Item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price if this item.
     *
     * @return Item price.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the required level for this item.
     *
     * @return Required level.
     */
    public int getMinLv() {
        return minLv;
    }

	@Override
	public boolean equipable(int hero_level) {
        return this.minLv == hero_level;
    }

	@Override
	public int buy() {
		return this.cost;
	}

	@Override
	public int sell() {
		return this.cost / 2;
	}
}
