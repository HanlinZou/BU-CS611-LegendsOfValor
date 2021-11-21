/**
 * Tile class maintains information about the contents of a LoV game board cell.
 * Tile class has a specified schema specified by a Cell object.
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
    public boolean getMonsterOn() {
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
        if (!getHeroOn() && !getMonsterOn()) return plainDraw();

        String body = "";
        String hero = Color.getColor().BLUE + "H" + Color.getColor().RESET;
        String monster = Color.getColor().WHITE + "M" + Color.getColor().RESET;

        if (getHeroOn() && getMonsterOn()) {
            body = getColBound() + " " + hero + "   " + monster + " " + getColBound();
        } else if (getMonsterOn()) {
            body = getColBound() + "     " + monster + " " + getColBound();
        } else if (getHeroOn()) {
            body = getColBound() + " " + hero + "     " + getColBound();
        }

        return
            getRowBound() + "\n" +
            body + "\n" +
            getRowBound();
    }

    /**
     * Returns if this tile can give hero standing on it buff.
     *
     * @return Buff tile or not?
     */
    public boolean isBuffTile() {
        return false;
    }

    @Override
    public String toString() {
        return specialDraw();
    }
}
