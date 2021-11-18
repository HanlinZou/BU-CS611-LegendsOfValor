
public class Koulou extends Cell
{
	//increase strength
	private double buff;
	
	Koulou()
	{
		super.setHeroOn(false);
		super.set_monster_on(false);
		super.set_cell_type(CellType.KOULOU);
		super.set_accessible(true);
		this.buff = .1;
	}
	
	public double get_buff() {return this.buff;}
	public void set_buff(int v) {this.buff = v;}
	
	@Override
	public String plainDraw() 
	{
		return "K---K---K"
				+ "\n" +  "|       |"
				+ "\n" +  "K---K---K";
	}

	@Override
	public String specialDraw() 
	{
		if(super.getHeroOn())
		{
			return "K---K---K"
					+ "\n" +  "| H     |"
					+ "\n" +  "K---K---K";
		}
		else if(super.get_monster_on())
		{
			return "K---K---K"
					+ "\n" +  "|     M |"
					+ "\n" +  "K---K---K";
		}
		else if(super.getHeroOn() && super.get_monster_on())
		{
			return "K---K---K"
					+ "\n" +  "| H   M |"
					+ "\n" +  "K---K---K";
		}
		return this.plainDraw();
	}

	@Override
	public void cell_effect() 
	{
		//buff strength
	}
}
