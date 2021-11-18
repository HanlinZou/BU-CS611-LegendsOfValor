
public class Cave extends Cell
{
	//increase agility
	private double buff;
	
	Cave()
	{
		super.setHeroOn(false);
		super.set_monster_on(false);
		super.set_cell_type(CellType.CAVE);
		super.set_accessible(true);
		this.buff = .1;
	}
	
	public double get_buff() {return this.buff;}
	public void set_buff(int v) {this.buff = v;}
	
	@Override
	public String plainDraw() 
	{
		return "C---C---C"
				+ "\n" +  "|       |"
				+ "\n" +  "C---C---C";
	}

	@Override
	public String specialDraw() 
	{
		if(super.getHeroOn())
		{
			return "C---C---C"
					+ "\n" +  "| H     |"
					+ "\n" +  "C---C---C";
		}
		else if(super.get_monster_on())
		{
			return "C---C---C"
					+ "\n" +  "|     M |"
					+ "\n" +  "C---C---C";
		}
		else if(super.getHeroOn() && super.get_monster_on())
		{
			return "C---C---C"
					+ "\n" +  "| H   M |"
					+ "\n" +  "C---C---C";
		}
		return this.plainDraw();
	}

	@Override
	public void cell_effect() 
	{
		//buff agility
	}
}