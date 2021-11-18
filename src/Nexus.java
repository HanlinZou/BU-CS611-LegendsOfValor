
public class Nexus extends Cell
{
	Nexus()
	{
		super.set_cell_type(CellType.NEXUS);
		super.set_accessible(true);
	}
	@Override
	public String plainDraw() 
	{
		return "N---N---N"
				+ "\n" +  "|       |"
				+ "\n" +  "N---N---N";
	}

	@Override
	public String specialDraw() 
	{
		if(super.getHeroOn())
		{
			return "N---N---N"
					+ "\n" +  "| H     |"
					+ "\n" +  "N---N---N";
		}
		else if(super.get_monster_on())
		{
			return "N---N---N"
					+ "\n" +  "|     M |"
					+ "\n" +  "N---N---N";
		}
		else if(super.getHeroOn() && super.get_monster_on())
		{
			return   "N---N---N"
					+ "\n" +  "| H   M |"
					+ "\n" +  "N---N---N";
		}
		return this.plainDraw();
	}

	@Override
	public void cell_effect() 
	{
		//hero spawn & market	
		//to do
	}
	
}
