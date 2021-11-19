
public class Plain extends Tile {
    Plain() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.PLAIN);
        super.setAccessible(true);
    }

    public String getRowBound() {
        return (Color.RESET + "P---P---P");
    }

    public String getColBound() {
        return (Color.RESET + "|");
    }

    @Override
    public void cellEffect() {
        // nothing
    }
}
