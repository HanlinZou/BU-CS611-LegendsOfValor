/**
 * Defines interfaces for all hittable characters.
 * Heroes and Monsters classes should inherit implement this.
 */

public interface Fight {
    /**
     * Calculate this character's regular attack damage.
     *
     * @return Regular damage
     */
	int attackDamage();

    /**
     * Calculate how much damage this character can block when get attacked.
     *
     * @return Defense value
     */
    int calDefense();

    /**
     * Calculate the probability for this character to dodge a regular attack
     * successfully.
     *
     * @return Probability to dodge a regular attack
     */
    double probDodge();

    /**
     * Displays the character's information during a fight.
     */
    void displayInfoInFight(boolean displayTitle, int index);
}
