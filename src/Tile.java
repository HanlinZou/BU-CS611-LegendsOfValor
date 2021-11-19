/**
 * Cell class maintains information about the contents of a Cell.
 * Cell class has a specified schema specified by a Cell object.
 */
public abstract class Tile extends Cell implements Drawable{

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
     * Set the mark of a Cell
     *
     * @param mark the object that a cell needs to display
     */
    public void setMark(Object mark) {
        this.mark = mark;
    }

    /**
     * get the mark of a Cell
     *
     * @return the object that a cell needs to display
     */
    public Object getMark() {
        return mark;
    }

    /**
     * Set whether there's a hero on current the cell or not
     *
     * @param on On the Cell/ NOT on the Cell
     */
    public void setHeroOn(boolean on) {
        this.heroOn = on;
    }

    /**
     * Get whether there's a hero on current the cell or not
     *
     * @return On the Cell/ NOT on the Cell
     */
    public boolean getHeroOn() {
        return heroOn;
    }

    // get set monsterOn
    public boolean get_monster_on() {
        return this.monsterOn;
    }

    public void setMonsterOn(boolean on) {
        this.monsterOn = on;
    }

    // get set cell type
    public CellType getCellType() {
        return this.type;
    }

    public void setCellType(CellType type) {
        this.type = type;
    }

    // get set accessible
    public boolean getAccessible() {
        return this.accessible;
    }

    public void setAccessible(boolean access) {
        this.accessible = access;
    }

    public abstract void cellEffect();

    public String plainDraw() {
        return
            getRowBound() + "\n" +
            getColBound() + "       " + getColBound() + "\n" +
            getRowBound();
    }

    public String specialDraw() {
        if (!getHeroOn() && !get_monster_on()) return plainDraw();

        String body = "";
        String hero = Color.BLUE + "H" + Color.RESET;
        String monster = Color.WHITE + "M" + Color.RESET;

        if (getHeroOn()) {
            body = getColBound() + " " + hero + "     " + getColBound();
        } else if (get_monster_on()) {
            body = getColBound() + "     " + monster + " " + getColBound();
        } else if (getHeroOn() && get_monster_on()) {
            body = getColBound() + " " + hero + "   " + monster + " " + getColBound();
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
