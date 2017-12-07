package finalproject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class gameControl extends Panel implements Runnable, KeyListener {
  private LinkedList<Point> snake;
  private Point apple;

  private final int pointWidth = 15;
  private final int pointHeight = 15;
  private final int PLAY_AREA_WIDTH = 30;
  private final int PLAY_AREA_HEIGHT = 30;

  private Graphics globalGraphics;
  private Thread runThread;

  private int Score = 0;

  private int direction = Direction.noDirection;

  public void paint(Graphics g) {

    globalGraphics = g.create();
    if (runThread == null) {
      runThread = new Thread(this);
      runThread.start();
    }
    this.setPreferredSize(new Dimension(640, 480));
    snake = new LinkedList<>();
    apple = new Point();
    defaultSettings();
    placeApple();

    /*snake.add(new Point(15,15));
    snake.add(new Point(15,14));
    snake.add(new Point(15,13));
    placeApple(); */

    this.addKeyListener(this);
  }

  public void defaultSettings() {
    Score = 0;
    snake.clear();
    snake.add(new Point(15, 15));
    snake.add(new Point(15, 14));
    snake.add(new Point(15, 13));
    direction = Direction.noDirection;
  }

  public void Draw(Graphics g) {
    g.clearRect(0, 0, 640, 480);
    g.drawRect(0, 0, PLAY_AREA_WIDTH * pointWidth, PLAY_AREA_HEIGHT * pointHeight);
    DrawSnake(g);
    DrawApple(g);
    DrawScore(g);
  }

  public void DrawSnake(Graphics g) {

    for (Point p : snake) {
      g.setColor(Color.GREEN);
      g.fillRect(p.x * pointWidth, p.y * pointHeight, pointWidth, pointHeight);
      g.setColor(Color.black);
      g.drawRect(p.x * pointWidth, p.y * pointHeight, pointWidth, pointHeight);
    }
  }

  public void DrawScore(Graphics g) {
    g.setColor(Color.black);
    g.setFont(new Font("arial", Font.PLAIN, 14));
    // g.drawString("Score: " + Score, 0, 20);
    g.drawString("Score: " + (int) countScore.score(snake.size()), 0, 20);
  }

  public void DrawApple(Graphics g) {
    g.setColor(Color.yellow);
    g.fillOval(apple.x * pointWidth, apple.y * pointHeight, pointWidth, pointHeight);
  }

  public void DrawGameOver(Graphics g) {
    g.setColor(Color.black);
    g.setFont(new Font("arial", Font.BOLD, 50));
    g.drawString("GAME OVER", 300, 300);
  }

  public void placeApple() {
    Random ran = new Random();
    int randomX = ran.nextInt(PLAY_AREA_WIDTH);
    int randomY = ran.nextInt(PLAY_AREA_HEIGHT);
    Point randomPoint = new Point(randomX, randomY);
    while ((snake.peekFirst()).equals(randomPoint)) {
      randomX = ran.nextInt(PLAY_AREA_WIDTH);
      randomY = ran.nextInt(PLAY_AREA_HEIGHT);
      randomPoint = new Point(randomX, randomY);
    }
    apple = randomPoint;
  }

  @Override
  public void run() {
    while (true) {
      move();
      Draw(globalGraphics);

      try {
        Thread.currentThread();
        Thread.sleep(200);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void move() {
    Point head = snake.peekFirst();
    Point newPoint = head;
    switch (direction) {
      case Direction.up:
        newPoint = new Point(head.x, head.y - 1);
        break;
      case Direction.down:
        newPoint = new Point(head.x, head.y + 1);
        break;
      case Direction.left:
        newPoint = new Point(head.x - 1, head.y);
        break;
      case Direction.right:
        newPoint = new Point(head.x + 1, head.y);
        break;
    }

    snake.remove(snake.peekLast());

    if (newPoint.equals(apple)) {
      // when snake eats apple
      // Score += 50;
      Point addPoint = (Point) newPoint.clone();
      snake.push(addPoint);
      placeApple();
      Score ++;

    } else if (newPoint.x < 0 || newPoint.x > (PLAY_AREA_WIDTH - 1)) {
      // reset game
      defaultSettings();
      return;
    } else if (newPoint.y < 0 || newPoint.y > (PLAY_AREA_HEIGHT - 1)) {
      // reset game
      defaultSettings();
      return;
    } else if (snake.contains(newPoint)) {
      // reset game
      defaultSettings();
      return;
    }

    snake.push(newPoint);
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        if (direction != Direction.down) direction = Direction.up;
        break;
      case KeyEvent.VK_DOWN:
        if (direction != Direction.up) direction = Direction.down;
        break;
      case KeyEvent.VK_RIGHT:
        if (direction != Direction.left) direction = Direction.right;
        break;
      case KeyEvent.VK_LEFT:
        if (direction != Direction.right) direction = Direction.left;
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}
}
