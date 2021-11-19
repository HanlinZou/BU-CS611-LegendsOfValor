
public class Cave extends Tile {
    // increase agility
    private double buff;

    Cave() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.CAVE);
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
        return (Color.GREY + "C---C---C" + Color.RESET);
    }

    public String getColBound() {
        return (Color.GREY + "|" + Color.RESET);
    }

    @Override
    public void cellEffect() {
        // buff agility
    }
}
