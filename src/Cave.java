
public class Cave extends Tile {
    // increase agility
    private double buff;

    Cave() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.CAVE);
        super.setAccessible(true);
        set_buff(0.1);
    }

    public double get_buff() {
        return this.buff;
    }

    public void set_buff(double v) {
        this.buff = v;
    }

    public String getRowBound() {
        return (Color.ORANGE + "C---C---C" + Color.RESET);
    }

    public String getColBound() {
        return (Color.ORANGE + "|" + Color.RESET);
    }

    @Override
    public void cellEffect() {
        // buff agility
    }
}
