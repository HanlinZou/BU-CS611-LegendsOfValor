
public class CellFactory 
{
	public <T extends Cell> T create_cell(CellType type)
	{
		switch(type)
		{
		case NEXUS:
			return (T) new Nexus();
		case INACCESSIBLE:
			return (T) new Inaccessible();
		case PLAIN:
			return (T) new Plain();
		case BUSH:
			return (T) new Bush();
		case CAVE:
			return (T) new Cave();
		case KOULOU:
			return (T) new Koulou();
		default:
			return null;
		}
	}
}
