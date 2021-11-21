/**
 * Inaccessible class maintains information about the contents of an inaccessible tile for LoV.
 * Inherits from Tile class and implements necessary interfaces.
*/

public class Inaccessible extends Tile {
    Inaccessible() {
        super.setAccessible(false);
        super.setCellType(CellType.INACCESSIBLE);
    }

    public String getRowBound() {
        return (Color.getColor().RED + "IxxxIxxxI" + Color.getColor().RESET);
    }

    public String getColBound() {
        return (Color.getColor().RED + "|" + Color.getColor().RESET);
    }

    @Override
    public String plainDraw() {
        return
            getRowBound() + "\n" +
            Color.getColor().RED + "| X X X |" + Color.getColor().RESET + "\n" +
            getRowBound();
    }

    @Override
    public String specialDraw() {
        return this.plainDraw();
    }
}
