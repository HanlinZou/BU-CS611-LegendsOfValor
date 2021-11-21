/**
 * Drawable defines interfaces that each board tile should implements to
 * print them in terminal easily.
 */

public interface Drawable {
    /**
     * Prints a naked block.
     */
    String plainDraw();

    /**
     * Prints a block with characters on it.
     */
    String specialDraw();

    /**
     * Prints block's upper and lower boundary.
     */
    String getRowBound();

    /**
     * Prints block's left and right boundary.
     */
    String getColBound();
}
