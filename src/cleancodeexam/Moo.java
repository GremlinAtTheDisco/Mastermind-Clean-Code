package cleancodeexam;

import controller.MooController;
import cleancodeexam.model.MooGame;
import presentation.SimpleWindow;

public class Moo {

    static SimpleWindow gui;

    public static void main(String[] args)  {
        gui = new SimpleWindow("Moo");
        MooGame mooGame = new MooGame();
        MooController controller = new MooController(gui, mooGame);
        controller.run();

    }

}
