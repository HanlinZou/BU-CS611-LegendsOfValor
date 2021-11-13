public class Exoskeleton extends Monster {

    /**
     * No-arg constructor
     */
    Exoskeleton() {
        super();
    }

    /**
     * Constructor of Exoskeleton object
     *
     * @param name    name of the monster
     * @param LV      level of the monster
     * @param HP      health of the monster
     * @param damage  damage from the monster
     * @param defense defense of the monster
     * @param dodge   dodge of the monster
     */
    Exoskeleton(String name, int LV, int HP, int damage, int defense, int dodge) {
        super(name, LV, HP, damage, defense, dodge);
    }
}
