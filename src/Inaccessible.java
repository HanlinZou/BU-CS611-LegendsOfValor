
public class Inaccessible extends Cell
{
	Inaccessible()
	{
		super.set_accessible(false);
		super.set_cell_type(CellType.INACCESSIBLE);
	}
	@Override
	public String plainDraw()
	{
		return "IxxxIxxxI"
				+ "\n" + "| X X X |"
				+ "\n" + "IxxxIxxxI";
	}

	@Override
	public String specialDraw() {
		// TODO Auto-generated method stub
		return this.plainDraw();
	}

	@Override
	public void cell_effect()
	{
		//nothing
	}
	
}
