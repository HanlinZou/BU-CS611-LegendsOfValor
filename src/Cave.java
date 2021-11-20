/**
 * Cave class maintains information about the contents of a cave tile.
 * Inheirts from Tile class and implements necessary interfaces.
*/

public class Cave extends Tile {
    // increase agility
    private double buff;

    Cave() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.CAVE);
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
