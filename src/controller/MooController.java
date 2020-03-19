package controller;

import cleancodeexam.model.MooGame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import persistence.Player;
import persistence.PlayerDAOImpl;
import persistence.ResultDAOImpl;
import presentation.SimpleWindow;

public class MooController {

    private PlayerDAOImpl playerDao = new PlayerDAOImpl();
    private ResultDAOImpl resultDao = new ResultDAOImpl();
    private SimpleWindow gui;
    private MooGame game;
    private Player player;

    public MooController() {
    }

    public MooController(SimpleWindow gui, MooGame game) {
        this.gui = gui;
        this.game = game;
    }

    public void run() {
        int answer = JOptionPane.YES_OPTION;
        while (answer == JOptionPane.YES_OPTION) {
            int id = login();
            gui.clear();
            gui.addString("New game:\n");
            //comment out or remove next line to play real games!
            String goal = game.makeGoal();
            gui.addString("For practice, number is: " + goal + "\n");
            int nGuess = runMooGame(answer, id, goal);
            resultDao.setResult(nGuess, id);
            showTopTen();
            answer = JOptionPane.showConfirmDialog(null, "Correct, it took " + nGuess
                    + " guesses\nContinue?");
        }
        gui.exit();
    }

    public int login() {
        int id = 0;
        gui.addString("Enter your user name:\n");
        String name = gui.getString();
        try {
            player = playerDao.getPlayerIdByName(name);

            if (player != null) {
                id = player.getId();
            } else {
                gui.addString("User not in database, please register with admin");
                Thread.sleep(5000);
                gui.exit();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public int runMooGame(int answer, int id, String goal) {
        String guess = gui.getString();
        gui.addString(guess + "\n");
        int nGuess = 1;
        String bbcc = game.checkBC(goal, guess);
        gui.addString(bbcc + "\n");
        while (!bbcc.equals("BBBB,")) {
            nGuess++;
            guess = gui.getString();
            gui.addString(guess + "\n");
            bbcc = game.checkBC(goal, guess);
            gui.addString(bbcc + "\n");
        }
        return nGuess;

    }

    private class PlayerAverage {

        String name;
        double average;

        public PlayerAverage(String name, double average) {
            this.name = name;
            this.average = average;
        }
    }

    public void showTopTen() {
        List<PlayerAverage> topList = new ArrayList<>();
        List<Integer> playersResult = new ArrayList<>();
        List<Player> allPlayers = playerDao.getAllPlayers();

        for (Player allPlayer : allPlayers) {
            int totalGuesses = 0;
            playersResult = resultDao.getAllResults(allPlayer.getId());
            int nGames = playersResult.size();

            for (Integer score : playersResult) {
                totalGuesses = totalGuesses + score;
            }

            if (nGames > 0) {
                topList.add(new PlayerAverage(allPlayer.getName(), (double) totalGuesses / nGames));
            }
        }

        gui.addString("Top Ten List\n    Player     Average\n");
        int pos = 1;
        topList.sort((p1, p2) -> (Double.compare(p1.average, p2.average)));

        for (PlayerAverage p : topList) {
            gui.addString(String.format("%3d %-10s%5.2f%n", pos, p.name, p.average));
            if (pos++ == 10) {
                break;
            }
        }

    }

}
