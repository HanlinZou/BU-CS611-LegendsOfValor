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
        return (Color.getColor().GREEN + "B---B---B" + Color.getColor().RESET);
    }

    public String getColBound() {
        return (Color.getColor().GREEN + "|" + Color.getColor().RESET);
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
            Color.getColor().RED + "Bush Buff: hero " + hero.getName() + "'s dexterity increase " + getBuff() + ", " +
            "now is " + hero.getDexterity() + "." + Color.getColor().RESET
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
            Color.getColor().RED + "Bush Buff removed: hero " + hero.getName() + "'s dexterity return to " + hero.getDexterity() + "." + Color.getColor().RESET
        );
    }
}
