package finalproject;

import java.awt.event.*;
import javax.swing.*;

public class Main implements ActionListener {
  public static void main(String[] args) {
    Main p1 = new Main();
  }

  JFrame frame = new JFrame("Hungry Snake");
  JButton button;

  Main() {
    button = new JButton("Start Game");
    frame.setSize(640, 480);
    frame.setVisible(true);
    frame.setLayout(null);
    frame.setLocation(400, 100);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    frame.getContentPane().add(button);
    button.setBounds(270, 150, 100, 30);

    button.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {

    frame.dispose();
    gamePlay p2 = new gamePlay();
  }
}
