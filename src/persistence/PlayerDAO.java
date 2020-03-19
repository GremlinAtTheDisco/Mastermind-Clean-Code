package persistence;

import java.util.List;

public interface PlayerDAO {

    public Player getPlayerIdByName(String name);

    public List<Player> getAllPlayers();


}
