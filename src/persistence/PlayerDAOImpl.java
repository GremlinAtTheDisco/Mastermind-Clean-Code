package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerDAOImpl implements PlayerDAO {

    private Statement stmt;
    private ResultSet rs;
    private Connection connection;

    public PlayerDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/moodb", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Database not available " + ex);
        }
    }

    @Override
    public Player getPlayerIdByName(String name) {
        Player player = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select id,name from players where name = '" + name + "'");

            while (rs.next()) {
                player = new Player();
                int playerId = rs.getInt("id");
                String playerName = rs.getString("name");
                player.setId(playerId);
                player.setName(playerName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return player;
    }

    @Override
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select * from players");

            while (rs.next()) {
                Player player = new Player();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                player.setId(id);
                player.setName(name);
                players.add(player);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return players;
    }

}
