/**
 * Cell class maintains information about the contents of a Cell.
 * Cell class has a specified schema specified by a Cell object.
 */
public class Cell {

    protected Object mark;
    protected boolean heroOn;

    /**
     * No-arg constructor
     */
    Cell() {
        mark = "";
        heroOn = false;
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
     * Get the mark of a Cell
     *
     * @return the object that a cell needs to display
     */
    public Object getMark() {
        return mark;
    }

    /**
     * Set whether there's a hero on current the cell or not
     *
     * @param on On the Cell / NOT on the Cell
     */
    public void setHeroOn(boolean on) {
        this.heroOn = on;
    }

    /**
     * Get whether there's a hero on current the cell or not
     *
     * @return On the Cell / NOT on the Cell
     */
    public boolean getHeroOn() {
        return heroOn;
    }

    @Override
    public String toString() {
        if (getHeroOn())
            return "H";
        return this.mark + "";
    }
}
