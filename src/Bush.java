/**
 * Bush class maintains information about the contents of a bush tile for LoV.
 * Inherits from Tile class and implements necessary interfaces.
*/

public class Bush extends BuffTile {
    public Bush() {
        super(0.1);
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.BUSH);
    }

    public String getRowBound() {
        return (Color.GREEN + "B---B---B" + Color.RESET);
    }

    public String getColBound() {
        return (Color.GREEN + "|" + Color.RESET);
    }

    /**
     * Increases the dexterity of the given hero.
     *
     * @param hero A hero object.
    */
    @Override
    public void cellEffect(Hero hero) {
        hero.setBuffed(true);
        hero.setBuffType("dex");
        hero.setBuffAmt((int) (hero.getDexterity() * getBuff()));
        hero.setDexterity(hero.getDexterity() + hero.getBuffAmt());
        System.out.println(
            Color.RED + "Bush Buff: hero " + hero.getName() + "'s dexterity increase " + getBuff() + ", " +
            "now is " + hero.getDexterity() + "." + Color.RESET
        );
    }

    /**
     * Restore the dexterity of the given hero.
     *
     * @param hero A hero object.
    */
    @Override
    public void removeEffect(Hero hero) {
        if (!(hero.getBuffed() && hero.getBuffType().equals("dex"))) return;
        hero.setDexterity(hero.getDexterity() - hero.getBuffAmt());
        hero.setBuffed(false);
        hero.setBuffType("");
        hero.setBuffAmt(0);
        System.out.println(
            Color.RED + "Bush Buff removed: hero " + hero.getName() + "'s dexterity return to " + hero.getDexterity() + "." + Color.RESET
        );
    }
}
