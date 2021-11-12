/**
 * Weapon class maintains information about the contents of a Weapon Item.
 * Weapon class has a more specified schema extended from Item object.
 * Weapon examples can be found in Weaponry.txt
 */
public class Weapon extends Item{
    private int damage;
    private int hands;

    /**
     * No-arg constructor
     */
    Weapon(){
        damage = 0;
        hands = 0;
    }

    /**
     * Constructor of Weapon object
     * @param name name of the weapon
     * @param cost money to purchase the weapon
     * @param minLV minimum level to purchase and equip the weapon
     * @param damage damage the weapon can make
     * @param hands number of hands to hold the weapon
     */
    Weapon(String name, int cost, int minLV, int damage, int hands){
        setName(name);
        setCost(cost);
        setMinLv(minLV);
        setDamage(damage);
        setHands(hands);
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setHands(int hands){
        this.hands = hands;
    }

    public int getDamage(){
        return damage;
    }

    public int getHands(){
        return hands;
    }

    @Override
    public String toString(){
        return "Name: " + name + " Cost: " + cost + " Min LV: " +
                minLv + " Damage: " + damage + " Min Hands: " + hands;
    }
}
