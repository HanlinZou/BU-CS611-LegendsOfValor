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
