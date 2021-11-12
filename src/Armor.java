/**
 * Armor class maintains information about the contents of an Armor Item.
 * Armor class has a more specified schema extended from Item object.
 * Armor could be a shield or a cloth. Examples can be found in Armory.txt
 */
public class Armor extends Item{
    // Decides how much damage you can block
    private int defense;

    /**
     * No-arg constructor
     */
    Armor(){
        super();
        this.defense = 0;
    }

    /**
     * Constructor of Armor
     * @param name Name of the armor
     * @param cost how much money to buy the armor
     * @param minLV minimum level to equip the armor
     * @param defense defense value of the armor
     */
    Armor(String name, int cost, int minLV, int defense){
        setName(name);
        setCost(cost);
        setMinLv(minLV);
        setDefense(defense);
    }

    /**
     * set defense value for an armor
     * @param defense value of defense
     */
    public void setDefense(int defense){
        this.defense =  defense;
    }

    /**
     * return defense value of an armor
     * @return value of defense
     */
    public int getDefense(){
        return defense;
    }

    @Override
    public String toString(){
        return "Name: " + name + " Cost: " + cost + " Min LV: " + minLv + " Defense: " + defense;
    }
}
