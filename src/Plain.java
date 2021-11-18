
public class Plain extends Cell
{
	Plain()
	{
		super.setHeroOn(false);
		super.set_monster_on(false);
		super.set_cell_type(CellType.PLAIN);
		super.set_accessible(true);
	}
	@Override
	public String plainDraw() 
	{
		return "P---P---P"
				+ "\n" +  "|       |"
				+ "\n" +  "P---P---P";
	}

	@Override
	public String specialDraw() 
	{
		if(super.getHeroOn())
		{
			return "P---P---P"
					+ "\n" +  "| H     |"
					+ "\n" +  "P---P---P";
		}
		else if(super.get_monster_on())
		{
			return "P---P---P"
					+ "\n" +  "|     M |"
					+ "\n" +  "P---P---P";
		}
		else if(super.getHeroOn() && super.get_monster_on())
		{
			return "P---P---P"
					+ "\n" +  "| H   M |"
					+ "\n" +  "P---P---P";
		}
		return this.plainDraw();
	}

	@Override
	public void cell_effect() 
	{
		//nothing
	}

}
