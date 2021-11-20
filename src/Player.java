/**
 * A class with implement on player interface.
 * Can be further designed.
 * Provided with some supplement method to make player
 * 		functional.
 */

public class Player implements Player_Interface {
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

    /**
     * Sets the name of the player.
     *
     * @param playerName Player name.
    */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Sets the name and id of a team.
     *
     * @param teamId Team ID.
     * @param teamName Team name.
    */
    public void setTeam(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    /**
     * Returns the name of the player.
     *
     * @return Player name.
    */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Returns the name of the team.
     *
     * @return Team name.
    */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Returns the ID of the team.
     *
     * @return Team ID.
    */
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
