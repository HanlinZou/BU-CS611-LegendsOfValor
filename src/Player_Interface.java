/**
 * Player Interface is designed to satisfy potential scalability and extendability
 * purpose. Convenient to build features such as playerId, teams, etc.
 */

public interface Player_Interface {

    void setPlayerName(String name);

    void setTeam(int id, String s);

    String getPlayerName();

    String getTeamName();

    int getTeamId();

    String toString();
}
