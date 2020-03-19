package cleancodeexam.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.Player;
import persistence.PlayerDAO;
import persistence.ResultDAO;

public class DaoTest {

    MockDao mockDao;
    List<Integer> results;
    List<Player> players;

    @Before
    public void setUp() {
        mockDao = new MockDao();
    }

    @Test
    public void getAllResults() {
        results = mockDao.getAllResults(1);
        assertEquals(results, new ArrayList<>());
    }

    @Test
    public void getPlayerIdByName() {
        Player player1 = new Player();
        player1.setId(1);
        player1.setName("george");
        Player player = mockDao.getPlayerIdByName("george");
        assertEquals(player.getId(), player1.getId());
    }

    @After
    public void tearDown() {
    }

}

class MockDao implements ResultDAO, PlayerDAO {

    Player player;
    List<Integer> results;
    List<Player> players;

    @Override
    public void setResult(int nGuesses, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> getAllResults(int id) {
        return results = new ArrayList<>();
    }

    @Override
    public List<Player> getAllPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player getPlayerIdByName(String name) {
        player = new Player();
        player.setId(1);
        player.setName("george");
        return player;
    }

}
