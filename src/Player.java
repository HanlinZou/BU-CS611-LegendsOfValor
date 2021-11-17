/**
 * A class with implement on player interface.
 * Can be further designed.
 * Provided with some supplement method to make player
 * 		functional.
 */

public class Player implements PlayerInterface {
    protected String playerName;
    protected int teamId;
    protected String teamName;

    Player(String pName, String tName, int tId) {
        setPlayerName(pName);
        setTeam(tId, tName);
    }

    Player(String pName) {
        this(pName, "No team", 0);
    }

    Player() {
        this("No name");
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setTeam(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamId() {
        return teamId;
    }

    public String toString() {
        String res = "\nPlayer Name: " + playerName;
        if (teamId != 0) {
            res += "\nPlayer's Team: " + teamName + "\nTeam ID: " + teamId;
        }
        return res;
    }
}
