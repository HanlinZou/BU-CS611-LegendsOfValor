
public class Koulou extends Cell {
    // increase strength
    private double buff;

    Koulou() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.KOULOU);
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
        return (Color.PURPLE + "K---K---K" + Color.RESET);
    }

    public String getColBound() {
        return (Color.PURPLE + "|" + Color.RESET);
    }

    @Override
    public void cellEffect() {
        // buff strength
    }
}
