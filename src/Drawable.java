/**
 * Drawable defines interfaces that each board tile should implements to print
 * them in terminal easily.
 */

public interface Drawable {
    // a naked block
    String plainDraw();

    // block occupied
    String specialDraw();

    String getRowBound();

    String getColBound();
}
