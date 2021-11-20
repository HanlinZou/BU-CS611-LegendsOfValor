/**
 * Cell class maintains information about the contents of a Cell.
 * Cell class has a specified schema specified by a Cell object.
 */
public abstract class Tile extends Cell implements Drawable {

    private boolean monsterOn;
    private CellType type;
    private boolean accessible;

    /**
     * No-arg constructor
     */
    Tile() {
        mark = "";
        heroOn = false;
        type = null;
        monsterOn = false;
        accessible = true;
    }

    /**
     * Returns whether there's a monster on current the tile or not
     *
     * @return On the Tile / NOT on the Tile
     */
    public boolean get_monster_on() {
        return this.monsterOn;
    }

    /**
     * Set whether there's a monster on current the tile or not
     *
     * @param on On the Tile / NOT on the Tile
     */
    public void setMonsterOn(boolean on) {
        this.monsterOn = on;
    }

    /**
     * Returns the cell type
     *
     * @return Type of the cell
     */
    public CellType getCellType() {
        return this.type;
    }

    /**
     * Set the cell type
     *
     * @param type Type of the cell
     */
    public void setCellType(CellType type) {
        this.type = type;
    }

    /**
     * Returns if this tile is accessible
     *
     * @return Accessible or not
     */
    public boolean getAccessible() {
        return this.accessible;
    }

    /**
     * Set if this tile is accessible
     *
     * @param access Accessible or not
     */
    public void setAccessible(boolean access) {
        this.accessible = access;
    }

    public abstract void cellEffect();

    /**
     * Prints the tile when there is no character on it.
     */
    public String plainDraw() {
        return
            getRowBound() + "\n" +
            getColBound() + "       " + getColBound() + "\n" +
            getRowBound();
    }

    /**
     * Prints the tile when there is(are) character(s) on it.
     */
    public String specialDraw() {
        if (!getHeroOn() && !get_monster_on()) return plainDraw();

        String body = "";
        String hero = Color.BLUE + "H" + Color.RESET;
        String monster = Color.WHITE + "M" + Color.RESET;

        if (getHeroOn() && get_monster_on()) {
            body = getColBound() + " " + hero + "   " + monster + " " + getColBound();
        } else if (get_monster_on()) {
            body = getColBound() + "     " + monster + " " + getColBound();
        } else if (getHeroOn()) {
            body = getColBound() + " " + hero + "     " + getColBound();
        }

        return
            getRowBound() + "\n" +
            body + "\n" +
            getRowBound();
    }

    @Override
    public String toString() {
        return specialDraw();
    }
}
