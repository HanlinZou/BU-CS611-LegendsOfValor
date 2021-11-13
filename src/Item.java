/**
 * Item class maintains information about the contents of an Item.
 * Item class has a specified schema specified by an Item object and
 * groups of variables of an item.
 */
public class Item {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setMinLv(int minLv) {
        this.minLv = minLv;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getMinLv() {
        return minLv;
    }
}
