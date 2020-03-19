package cleancodeexam.model;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MooGameTest {

    GameHandler game;

    @Before
    public void setUp() {
        game = new MooGameMock();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMakeGoal() {
        String goal = game.makeGoal();
        assertEquals("1234", goal);
    }

    @Test
    public void testCheckBC() {
        String goal = game.makeGoal();
        System.out.println(goal);
        String guess = "1024";
        String result = game.checkBC(goal, guess);
        assertEquals("BB,C", result);
    }

}

class MooGameMock implements GameHandler {

    MooGame mooGame = new MooGame();

    @Override
    public String checkBC(String goal, String guess) {
        String result = mooGame.checkBC(goal, guess);
        return result;
    }

    @Override
    public String makeGoal() {
        String goal = "1234";
        return goal;

    }

}
