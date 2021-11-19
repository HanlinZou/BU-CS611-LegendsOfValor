
public class Inaccessible extends Tile {
    Inaccessible() {
        super.setAccessible(false);
        super.setCellType(CellType.INACCESSIBLE);
    }

    public String getRowBound() {
        return (Color.ORANGE + "IxxxIxxxI" + Color.RESET);
    }

    public String getColBound() {
        return (Color.ORANGE + "|" + Color.RESET);
    }

    @Override
    public String plainDraw() {
        return
            getRowBound() + "\n" +
            Color.ORANGE + "| X X X |" + Color.RESET + "\n" +
            getRowBound();
    }

    @Override
    public String specialDraw() {
        // TODO Auto-generated method stub
        return this.plainDraw();
    }

    @Override
    public void cellEffect() {
        // nothing
    }
}
