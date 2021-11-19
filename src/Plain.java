
public class Plain extends Tile {
    Plain() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.PLAIN);
        super.setAccessible(true);
    }

    public String getRowBound() {
        return (Color.BLUE + "P---P---P" + Color.RESET);
    }

    public String getColBound() {
        return (Color.BLUE + "|" + Color.RESET);
    }

    @Override
    public void cellEffect() {
        // nothing
    }
}
