/**
 * Nexus class maintains information about the contents of a Nexus tile for LoV.
 * Inherits from Tile class and implements necessary interfaces.
*/

public class Nexus extends Tile {
    Nexus() {
        super.setCellType(CellType.NEXUS);
        super.setAccessible(true);
    }

    public String getRowBound() {
        return (Color.getColor().CYAN + "N---N---N" + Color.getColor().RESET);
    }

    public String getColBound() {
        return (Color.getColor().CYAN + "|" + Color.getColor().RESET);
    }
}
