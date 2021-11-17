/**
 * A static utility class providing ASCI color code.
 */
public final class Color {
    private Color() {
    }

    // Reset
    public static final String RESET = "\u001B[0m";

    // Regular Colors
    public static final String BLACK  = "\u001B[30m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN   = "\u001B[36m";
    public static final String WHITE  = "\u001B[37m";
    public static final String ORANGE = "\u001b[38;5;208m";
    public static final String GREY   = "\u001b[38;5;246m";

    // Background
    public static final String BGBLACK  = "\u001B[40m";
    public static final String BGRED    = "\u001B[41m";
    public static final String BGGREEN  = "\u001B[42m";
    public static final String BGYELLOW = "\u001B[43m";
    public static final String BGBLUE   = "\u001B[44m";
    public static final String BGPURPLE = "\u001B[45m";
    public static final String BGCYAN   = "\u001B[46m";
    public static final String BGWHITE  = "\u001B[47m";
}
