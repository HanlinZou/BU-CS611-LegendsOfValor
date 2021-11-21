/**
 * Plain class maintains information about the contents of a Plain tile for LoV.
 * Inherits from Tile class and implements necessary interfaces.
*/

public class Plain extends Tile {
    Plain() {
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.PLAIN);
        super.setAccessible(true);
    }

    public String getRowBound() {
        return (Color.getColor().RESET + "P---P---P");
    }

    public String getColBound() {
        return (Color.getColor().RESET + "|");
    }
}
