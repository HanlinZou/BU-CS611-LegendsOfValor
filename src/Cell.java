/**
 * Cell class maintains information about the contents of a Cell.
 * Cell class has a specified schema specified by a Cell object.
 */
public abstract class Cell implements Drawable {

    private Object mark;
    private boolean heroOn;
    private boolean monsterOn;
    private CellType type;
    private boolean accessible;
    /**
     * No-arg constructor
     */
    Cell() {
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
    
    //get set monsterOn
    public boolean get_monster_on() {return this.monsterOn;}
    public void set_monster_on(boolean on) {this.monsterOn = on;}
    //get set cell type
    public CellType get_cell_type() {return this.type;}
    public void set_cell_type(CellType type) {this.type = type;}
    //get set accessible
    public boolean get_accessible() {return this.accessible;}
    public void set_accessible(boolean access) {this.accessible = access;}
    
    public abstract void cell_effect();
    
    
    @Override
    public String toString() {
        return specialDraw();
    }
}
