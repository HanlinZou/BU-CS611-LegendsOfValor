/**
 * A static utility class providing ASCI color code.
 */
public final class Color {
    private Color() {
    }
    private static Color color;
    
    // Reset
    public final String RESET = "\u001B[0m";

    // Regular Colors
    public final String BLACK  = "\u001B[30m";
    public final String RED    = "\u001B[31m";
    public final String GREEN  = "\u001B[32m";
    public final String YELLOW = "\u001B[33m";
    public final String BLUE   = "\u001B[34m";
    public final String PURPLE = "\u001B[35m";
    public final String CYAN   = "\u001B[36m";
    public final String WHITE  = "\u001B[37m";
    public final String ORANGE = "\u001b[38;5;208m";
    public final String GREY   = "\u001b[38;5;246m";

    // Background
    public final String BG_BLACK  = "\u001B[40m";
    public final String BG_RED    = "\u001B[41m";
    public final String BG_GREEN  = "\u001B[42m";
    public final String BG_YELLOW = "\u001B[43m";
    public final String BG_BLUE   = "\u001B[44m";
    public final String BG_PURPLE = "\u001B[45m";
    public final String BG_CYAN   = "\u001B[46m";
    public final String BG_WHITE  = "\u001B[47m";
    
    public static Color getColor()
    {
    	if(color == null)
    	{
    		color = new Color();
    	}
    	return color;
    }
}
