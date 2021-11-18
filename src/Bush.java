
public class Bush extends Cell
{
	//increase dexterity
	private double buff;
	
	Bush()
	{
		super.setHeroOn(false);
		super.set_monster_on(false);
		super.set_cell_type(CellType.BUSH);
		super.set_accessible(true);
		this.buff = .1;
	}
	
	public double get_buff() {return this.buff;}
	public void set_buff(int v) {this.buff = v;}
	
	@Override
	public String plainDraw() 
	{
		return "B---B---B"
				+ "\n" +  "|       |"
				+ "\n" +  "B---B---B";
	}

	@Override
	public String specialDraw() 
	{
		if(super.getHeroOn())
		{
			return "B---B---B"
					+ "\n" +  "| H     |"
					+ "\n" +  "B---B---B";
		}
		else if(super.get_monster_on())
		{
			return "B---B---B"
					+ "\n" +  "|     M |"
					+ "\n" +  "B---B---B";
		}
		else if(super.getHeroOn() && super.get_monster_on())
		{
			return "B---B---B"
					+ "\n" +  "| H   M |"
					+ "\n" +  "B---B---B";
		}
		return this.plainDraw();
	}

	@Override
	public void cell_effect() 
	{
		//buff dex
	}
}
