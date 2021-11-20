/**
 * Koulou class maintains information about the contents of an koulou tile for LoV.
 * Inheirts from Tile class and implements necessary interfaces.
*/

public class Koulou extends Tile {
    // increase strength
    private double buff;

    Koulou() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.KOULOU);
        super.setAccessible(true);
        setBuff(0.1);
    }

    public double getBuff() {
        return this.buff;
    }

    public void setBuff(double v) {
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
