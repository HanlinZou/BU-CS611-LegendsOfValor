/**
 * Nexus class maintains information about the contents of a Nexus tile for LoV.
 * Inheirts from Tile class and implements necessary interfaces.
*/

public class Nexus extends Tile {
    Nexus() {
        super.setCellType(CellType.NEXUS);
        super.setAccessible(true);
    }

    public String getRowBound() {
        return (Color.CYAN + "N---N---N" + Color.RESET);
    }

    public String getColBound() {
        return (Color.CYAN + "|" + Color.RESET);
    }

    @Override
    public void cellEffect() {
        // hero spawn & market
        // to do
    }
}
