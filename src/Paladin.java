public class Paladin extends Hero {

    /**
     * Constructor of Paladin object
     *
     * @param name      name of the hero
     * @param mana      mana of the hero
     * @param strength  strength of the hero
     * @param agility   agility of the hero
     * @param dexterity dexterity of the hero
     * @param initMoney initial money of the hero
     * @param initXP    initial experience of the hero
     */
    Paladin(String name, int mana, int strength, int agility, int dexterity, int initMoney, int initXP) {
        super(name, mana, strength, agility, dexterity, initMoney, initXP);
    }

    @Override
    public void boostSkill() {
        setStrength((int) (getStrength() * 1.1));
        setAgility((int) (getAgility() * 1.05));
        setDexterity((int) (getDexterity() * 1.1));
    }
}
