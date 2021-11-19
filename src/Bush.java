
public class Bush extends Tile {
    // increase dexterity
    private double buff;

    Bush() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.BUSH);
        super.setAccessible(true);
        this.buff = .1;
    }

    public double get_buff() {
        return this.buff;
    }

    public void set_buff(int v) {
        this.buff = v;
    }

    public String getRowBound() {
        return (Color.GREEN + "B---B---B" + Color.RESET);
    }

    public String getColBound() {
        return (Color.GREEN + "|" + Color.RESET);
    }

    @Override
    public void cellEffect() {
        // buff dex
    }
}
