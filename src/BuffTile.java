/**
 * BuffTile class maintains information about the contents of a LoV game board cell
 * which can give hero who stand on it a buff.
 *
 * BuffTile class has a specified schema specified by a Tile object.
 */

public abstract class BuffTile extends Tile {
    private double buff;  // Increate attribute

    public BuffTile(double buff) {
        setBuff(buff);
        setAccessible(true);
    }

    /**
     * Add buff to the given hero.
     *
     * @param hero A hero object.
    */
    public abstract void cellEffect(Hero hero);

    /**
     * Remove buff to the given hero (after hero leaving this tile).
     *
     * @param hero A hero object.
    */
    public abstract void removeEffect(Hero hero);

    /**
     * Returns the buff value.
     *
     * @return Buff value.
    */
    public double getBuff() {
        return this.buff;
    }

    /**
     * Sets the buff value.
     *
     * @param buff Buff value.
    */
    public void setBuff(double buff) {
        this.buff = buff;
    }

    /**
     * A buff tile, return true.
     *
     * @return True
    */
    @Override
    public boolean isBuffTile() {
        return true;
    }
}
