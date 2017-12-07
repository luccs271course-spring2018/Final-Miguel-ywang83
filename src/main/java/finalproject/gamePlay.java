package finalproject;

import java.awt.*;
import javax.swing.*;

public class gamePlay {
  JFrame frame = new JFrame("Level 1");
  gameControl game = new gameControl();

  gamePlay() {
    game.setPreferredSize(new Dimension(640, 480));
    game.setVisible(true);
    game.setFocusable(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setLocation(400, 100);
    frame.setSize(640, 480);
    frame.add(game);
  }
}
