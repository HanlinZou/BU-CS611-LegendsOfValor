/**
 * Cave class maintains information about the contents of a cave tile.
 * Inheirts from Tile class and implements necessary interfaces.
*/

public class Cave extends BuffTile {
    public Cave() {
        super(0.1);
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.CAVE);
    }

    public String getRowBound() {
        return (Color.ORANGE + "C---C---C" + Color.RESET);
    }

    public String getColBound() {
        return (Color.ORANGE + "|" + Color.RESET);
    }

    /**
     * Increases the agility of the given hero.
     *
     * @param hero A hero object.
    */
    @Override
    public void cellEffect(Hero hero) {
        hero.setBuffed(true);
        hero.setBuffType("agi");
        hero.setBuffAmt((int) (hero.getAgility() * getBuff()));
        hero.setAgility(hero.getAgility() + hero.getBuffAmt());
        System.out.println(
            Color.RED + "Cave Buff: hero " + hero.getName() + "'s agility increase " + getBuff() + ", " +
            "now is " + hero.getAgility() + "." + Color.RESET
        );
    }

    /**
     * Restore the agility of the given hero.
     *
     * @param hero A hero object.
    */
    @Override
    public void removeEffect(Hero hero) {
        if (!(hero.getBuffed() && hero.getBuffType().equals("agi"))) return;
        hero.setAgility(hero.getAgility() - hero.getBuffAmt());
        hero.setBuffed(false);
        hero.setBuffType("");
        hero.setBuffAmt(0);
        System.out.println(
            Color.RED + "Cave Buff removed: hero " + hero.getName() + "'s agility return to " + hero.getAgility() + "." + Color.RESET
        );
    }
}
