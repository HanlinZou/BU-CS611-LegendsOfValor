/**
 * Koulou class maintains information about the contents of a koulou tile for LoV.
 * Inherits from Tile class and implements necessary interfaces.
*/

public class Koulou extends BuffTile {
    public Koulou() {
        super(0.1);
        super.setHeroOn(false);
        super.setMonsterOn(false);
        super.setCellType(CellType.KOULOU);
    }

    public String getRowBound() {
        return (Color.PURPLE + "K---K---K" + Color.RESET);
    }

    public String getColBound() {
        return (Color.PURPLE + "|" + Color.RESET);
    }

    /**
     * Increases the strength of the given hero.
     *
     * @param hero A hero object.
    */
    @Override
    public void cellEffect(Hero hero) {
        hero.setBuffed(true);
        hero.setBuffType("str");
        hero.setBuffAmt((int) (hero.getStrength() * getBuff()));
        hero.setStrength(hero.getStrength() + hero.getBuffAmt());
        System.out.println(
            Color.RED + "Koulou Buff: hero " + hero.getName() + "'s strength increase " + getBuff() + ", " +
            "now is " + hero.getStrength() + "." + Color.RESET
        );
    }

    /**
     * Restore the strength of the given hero.
     *
     * @param hero A hero object.
    */
    @Override
    public void removeEffect(Hero hero) {
        if (!(hero.getBuffed() && hero.getBuffType().equals("str"))) return;
        hero.setStrength(hero.getStrength() - hero.getBuffAmt());
        hero.setBuffed(false);
        hero.setBuffType("");
        hero.setBuffAmt(0);
        System.out.println(
            Color.RED + "Koulou Buff removed: hero " + hero.getName() + "'s strength return to " + hero.getStrength() + "." + Color.RESET
        );
    }
}
