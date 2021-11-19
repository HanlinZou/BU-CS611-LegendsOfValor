
public class Nexus extends Tile {
    Nexus() {
        super.setCellType(CellType.NEXUS);
        super.setAccessible(true);
    }

    public String getRowBound() {
        return (Color.GREEN + "N---N---N" + Color.RESET);
    }

    public String getColBound() {
        return (Color.GREEN + "|" + Color.RESET);
    }

    @Override
    public void cellEffect() {
        // hero spawn & market
        // to do
    }
}
