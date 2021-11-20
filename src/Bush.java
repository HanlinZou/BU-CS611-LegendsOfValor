/**
 * Bush class maintains information about the contents of a bush tile for LoV.
 * Inheirts from Tile class and implements necessary interfaces.
*/

public class Bush extends Tile {
    // increase dexterity
    private double buff;

    Bush() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.BUSH);
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
