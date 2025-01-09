import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class level extends JPanel implements ActionListener {
    // instance varibales
    private int height;
    private int width;
    private levelImage image;
    private player player;
    private JButton back;
    private boolean backclick;
    private BufferedImage flag;
    private int flagX;
    private int flagY;
    private boolean levelcompleted;
    boolean right = false;
    boolean left = false;

    /**
     * constructor
     * 
     * @param width  of the gam window
     * @param height of the game window
     * @param path   of the level to make from image
     */
    public level(int width, int height, String path) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.flag = null;
        // reads the image and catch any erros
        try {
            this.flag = ImageIO.read(new File("flag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(null);
        // makes the designated level from the image path given
        this.image = new levelImage(path);
        // makes a player
        this.player = new player(this.width - (this.width - 50), 100, 10);
        // add the level to the screen
        this.add(image);
        // seting up the back button
        this.back = new JButton("Back");
        this.back.addActionListener(this);
        this.back.setActionCommand("back");
        int length = 80;
        // adding back button to the screen
        this.back.setBounds(this.width - (this.width - 50), this.height - 70, length, length / 3);
        this.add(this.back);
        this.flagX = this.width - 70;
        this.flagY = this.image.getSpecificPoints(this.width - 50) - this.flag.getHeight();

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     * draws the player as well as the level
     */
    public void paintComponent(Graphics g) {
        image.paintComponent(g);
        this.player.draw(g);
        g.drawImage(this.flag, this.flagX, this.flagY, null);
    }

    /**
     * loop to run continous for my game logic
     */
    public void loop() {
        // checks if the player passed finsih line
        if (this.player.getX() - 20 < this.flagX) {
            // moving left or right
            if (right == true) {
                // sets the value to positve 1 to move right
                this.player.setDX(this.player.Right, this.image.getPoints());
                this.player.moveRight(this.image.getPoints());
            } else if (left == true) {
                // sets the value to negative 1 to move left
                this.player.setDX(this.player.Left, this.image.getPoints());
            } else {
                this.player.setDX(0, this.image.getPoints());
            }
            // checks the player for the collison with the ground
            if (this.player.collides(this.image.getPoints())) {
                if (this.player.onTopOfGround(this.image.getPoints())) {
                    this.player.setOnGround(true);
                }
            }
        } else {
            this.player.setDX(0, this.image.getPoints());
            this.levelcompleted = true;
        }
        // updates the player
        this.player.update();

    }

    /**
     * @return this.levlecompleted
     */
    public boolean levelCompleted() {
        return this.levelcompleted;
    }

    /**
     * @return the keyboard assigned with this level
     */
    public KeyAdapter getKeys() {
        return new Keyboard();
    }

    // Used to implemens any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_D) {
                right = true;
            } else if (key == KeyEvent.VK_A) {
                left = true;
            } else if (key == KeyEvent.VK_LEFT) {
                left = true;
            } else if (key == KeyEvent.VK_RIGHT) {
                right = true;
            }
        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_D) {
                right = false;
            } else if (key == KeyEvent.VK_A) {
                left = false;
            } else if (key == KeyEvent.VK_LEFT) {
                left = false;
            } else if (key == KeyEvent.VK_RIGHT) {
                right = false;
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * sets the boolean based on the action command
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command == "back") {
            this.backclick = true;
        }
        loop();
        repaint();
    }

    /**
     * @return this.backclick
     */
    public boolean getBackClick() {
        return this.backclick;
    }

    /**
     * resets the level by reseting the player to original position
     * 
     * @return this.backcick ad false
     */
    public boolean resetBackClick() {
        resetLevel();
        return this.backclick = false;
    }

    /**
     * reset the player
     */
    public void resetLevel() {
        this.player.reset(this.width - (this.width - 50), 100);
    }
}