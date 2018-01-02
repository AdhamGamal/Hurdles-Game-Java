package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import models.Background;
import models.Block;
import models.Player;

public final class Hurdles_Game extends JFrame {

    private Player player;
    private final int numOfBlocks = 3;
    private final Block[] blocks = new Block[numOfBlocks];
    private Background background;
    private Background ground;
    private final int windowWidth = 500;
    private final int windowHeight = 500;

    private final int groundX = 300;
    private final int backgroundDx = 1;
    private final int groundDx = 2;
    private final int playerDy = 3;
    private final int blocksDx = groundDx;

    public Hurdles_Game() {
        setSize(windowWidth, windowHeight);
        setTitle("Hurdles Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
        //add key listener
        addKeyListener(new KeyInput());
        //add game panel
        add(new GamePanal());
    }

    public class GamePanal extends JPanel implements ActionListener {

        //Timer
        Timer timer;

        public GamePanal() {
            //make timer do the action every 10 m.s
            timer = new Timer(10, this);
            //start timer 
            timer.start();
        }

        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);
            if (blocks[0].getX() + blocks[0].getWidth() <= 0) {
                blocks[0].setX(blocks[2].getX() + getRandomPosition());
            }
            if (blocks[1].getX() + blocks[1].getWidth() <= 0) {
                blocks[1].setX(blocks[0].getX() + getRandomPosition());
            }
            if (blocks[2].getX() + blocks[2].getWidth() <= 0) {
                blocks[2].setX(blocks[1].getX() + getRandomPosition());
            }
            //draw background 
            graphics.drawImage(background.getImg(), background.getX(), background.getY(), this);
            //repeat the background and border
            if (ground.getX() <= -(ground.getImg().getWidth(null) / 2)) {
                ground.setX(0);
            }
            //draw ground 
            graphics.drawImage(ground.getImg(), ground.getX(), ground.getY(), this);
            //draw character
            graphics.drawImage(player.getImg(), player.getX(), player.getY(), this);
            //draw blocks
            for (int i = 0; i < numOfBlocks; i++) {
                graphics.drawImage(blocks[i].getImg(), blocks[i].getX(), blocks[i].getY(), this);
            }

            //drae score and level
            graphics.setColor(Color.black);
            graphics.drawString("Level : " + 1, 150, 460);
            graphics.drawString("Score : " + (count / 10), 300, 460);

        }
        int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            //update every thing every 10 m.s
            if (background.getDx() > 0) {
                count += 1;
                if (count % 20 == 0) {
                    player.setImg(new ImageIcon(getClass().getResource("/images/up.png")).getImage());
                } else if (count % 15 == 0) {
                    player.setImg(new ImageIcon(getClass().getResource("/images/stand.png")).getImage());
                } else if (count % 10 == 0) {
                    player.setImg(new ImageIcon(getClass().getResource("/images/down.png")).getImage());
                } else if (count % 5 == 0) {
                    player.setImg(new ImageIcon(getClass().getResource("/images/stand.png")).getImage());
                }
            }
            background.updateObjectCoordinates();
            ground.updateObjectCoordinates();
            player.updateObjectCoordinates();
            for (int i = 0; i < numOfBlocks; i++) {
                blocks[i].updateObjectCoordinates();
            }
            if (player.isDead()) {
                if (JOptionPane.showConfirmDialog(null, "Your Legs is broken", "Play Again", JOptionPane.YES_NO_OPTION) == 0) {
                    removeAll();
                    init();
                } else {
                    timer.stop();
                    System.exit(0);
                }
            }
            //repaint before any check
            repaint();

        }
    }

    public void init() {
        background = new Background(new ImageIcon(getClass().getResource("/images/background.png")).getImage(), 0, backgroundDx);
        ground = new Background(new ImageIcon(getClass().getResource("/images/ground.png")).getImage(), groundX, groundDx);
        player = new Player(new ImageIcon(getClass().getResource("/images/stand.png")).getImage(), groundX, playerDy);
        int blockPosition = 0;
        for (int i = 0; i < numOfBlocks; i++) {
            blockPosition += getRandomPosition();
            blocks[i] = new Block(new ImageIcon(getClass().getResource("/images/block.png")).getImage(), blockPosition, groundX, blocksDx, player);
        }
    }

    public static int getRandomPosition() {
        int high = 400, low = 250;
        return new Random().nextInt(high - low) + low;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        new Hurdles_Game().setVisible(true);
    }
}
