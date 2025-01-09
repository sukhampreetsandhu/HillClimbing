import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Sukhampreet Sandhu
 */
public class keepclimbing extends JComponent implements ActionListener {

    // Height and Width of our game
    static final int WIDTH = 1200;
    static final int HEIGHT = 600;

    // Title of the window
    String title = "Climber";

    // sets the framerate and delay for our game
    // this calculates the number of milliseconds per frame
    // you just need to select an appropriate framerate
    int desiredFPS = 60;
    int desiredTime = Math.round((1000 / desiredFPS));

    // timer used to run the game loop
    // this is what keeps our time running smoothly :)
    Timer gameTimer;

    // YOUR GAME VARIABLES WOULD GO HERE
    private ArrayList<String> levelsDone = new ArrayList<String>();
    CardLayout screen = new CardLayout();
    JPanel mainPanel = new JPanel();
    // creating the different screens
    MainMenu menuScreen;
    about aboutScreen;
    LevelPage levelScreen;
    // creating all the 10 levels
    level level1;
    level level2;
    level level3;
    level level4;
    level level5;
    level level6;
    level level7;
    level level8;
    level level9;
    level level10;
    // GAME VARIABLES END HERE

    // create a window for my game
    JFrame frame = new JFrame(title);

    // Constructor to create the Frame and placing the panels in
    public keepclimbing() {
        // reading the user data file
        readFile("userData.csv");

        // makes new instace of the defined varibales above
        menuScreen = new MainMenu(title, WIDTH, HEIGHT);
        aboutScreen = new about(WIDTH, HEIGHT);
        levelScreen = new LevelPage(WIDTH, HEIGHT, this.levelsDone);
        // makes all the levels
        level1 = new level(WIDTH, HEIGHT, "level1.png");
        level2 = new level(WIDTH, HEIGHT, "level2.png");
        level3 = new level(WIDTH, HEIGHT, "level3.png");
        level4 = new level(WIDTH, HEIGHT, "level4.png");
        level5 = new level(WIDTH, HEIGHT, "level5.png");
        level6 = new level(WIDTH, HEIGHT, "level6.png");
        level7 = new level(WIDTH, HEIGHT, "level7.png");
        level8 = new level(WIDTH, HEIGHT, "level8.png");
        level9 = new level(WIDTH, HEIGHT, "level9.png");
        level10 = new level(WIDTH, HEIGHT, "level10.png");

        // adds every thing to the main Panel followed by a String name to call it later
        mainPanel.setLayout(screen);
        mainPanel.add(menuScreen, "menu");
        mainPanel.add(aboutScreen, "About");
        mainPanel.add(levelScreen, "LvPage");
        mainPanel.add(level1, "level1");
        mainPanel.add(level2, "level2");
        mainPanel.add(level3, "level3");
        mainPanel.add(level4, "level4");
        mainPanel.add(level5, "level5");
        mainPanel.add(level6, "level6");
        mainPanel.add(level7, "level7");
        mainPanel.add(level8, "level8");
        mainPanel.add(level9, "level9");
        mainPanel.add(level10, "level10");

        // sets the size of my game
        this.setSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(mainPanel);
        // shows the main menu on startup
        screen.show(mainPanel, "menu");

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        // shows the window to the user
        frame.setVisible(true);

        // add keyboard listeners for keyboard for each level
        frame.addKeyListener(level1.getKeys());
        frame.addKeyListener(level2.getKeys());
        frame.addKeyListener(level3.getKeys());
        frame.addKeyListener(level4.getKeys());
        frame.addKeyListener(level5.getKeys());
        frame.addKeyListener(level6.getKeys());
        frame.addKeyListener(level7.getKeys());
        frame.addKeyListener(level8.getKeys());
        frame.addKeyListener(level9.getKeys());
        frame.addKeyListener(level10.getKeys());

        // Start the game loop
        gameTimer = new Timer(desiredTime, this);
        gameTimer.setRepeats(true);
        gameTimer.start();
    }

    // reads the file
    public void readFile(String filename) {
        Scanner input = null;
        try {
            input = new Scanner(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }

        input.nextLine();

        // continues to read as long as there is stuff to read
        while (input.hasNext()) {
            String line = input.nextLine();
            // reads each word after commas as an independed word
            String[] data = line.split(",");
            // gets the word after comma
            String name = data[1];
            // adds it to the arraylist
            this.levelsDone.add(name);
        }
    }

    // writes to the csv file
    private void writeFile(String filename) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // prints the very first line
        output.println("level,completed or not");
        // prints from the arraylist
        for (int i = 0; i < 10; i++) {
            output.println(i + "," + this.levelsDone.get(i));
        }
        output.close();
    }

    // The main game loop
    // In here is where all the logic for user interface will go
    public void loop() {
        // makes the frame in focus for the keyboard to work
        frame.setFocusable(true);
        frame.requestFocus();
        // is the start button clicked
        if (menuScreen.getStartClick() == true) {
            // changes the screen to level page
            screen.show(mainPanel, "LvPage");
            // is back clicked
            if (levelScreen.getBackClick() == true) {
                // reverts back to the main menu
                screen.show(mainPanel, "menu");
                // resets the back and start click
                levelScreen.resetBackClick();
                menuScreen.resetStartClick();
            }
        }
        // is the about button clicked
        if (menuScreen.getAboutclick() == true) {
            // chnages the screen to about page
            screen.show(mainPanel, "About");
            if (aboutScreen.getClickedBack() == true) {
                // reverts back to the main menu
                screen.show(mainPanel, "menu");
                // resets the back and about click
                aboutScreen.resetBackClick();
                menuScreen.resetAboutClick();
            }
        }

        // checks which level is clicked and changes the screen to the desired level
        // starts that level loop
        // if back is clicked on that level, it resets the level along with back button
        // upon level completion it changes it in the levelsDone arraylist

        if (levelScreen.clickedLevel1() == true) {
            screen.show(mainPanel, "level1");
            level1.loop();
            if (level1.getBackClick() == true) {
                levelScreen.resetClickLevel1();
                level1.resetBackClick();
                screen.show(mainPanel, "LvPage");
            }
            if (level1.levelCompleted() == true) {
                this.levelsDone.set(0, "true");
            }
        }
        if (levelScreen.clickedLevel2() == true) {
            screen.show(mainPanel, "level2");
            level2.loop();
            if (level2.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level2.resetBackClick();
                levelScreen.resetClickLevel2();
            }
            if (level2.levelCompleted() == true) {
                this.levelsDone.set(1, "true");
            }
        }
        if (levelScreen.clickedLevel3() == true) {
            screen.show(mainPanel, "level3");
            level3.loop();
            if (level3.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level3.resetBackClick();
                levelScreen.resetClickLevel3();
            }
            if (level3.levelCompleted() == true) {
                this.levelsDone.set(2, "true");
            }
        }
        if (levelScreen.clickedLevel4() == true) {
            screen.show(mainPanel, "level4");
            level4.loop();
            if (level4.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level4.resetBackClick();
                levelScreen.resetClickLevel4();
            }
            if (level4.levelCompleted() == true) {
                this.levelsDone.set(3, "true");
            }
        }
        if (levelScreen.clickedLevel5() == true) {
            screen.show(mainPanel, "level5");
            level5.loop();
            if (level5.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level5.resetBackClick();
                levelScreen.resetClickLevel5();
            }
            if (level5.levelCompleted() == true) {
                this.levelsDone.set(4, "true");
            }
        }
        if (levelScreen.clickedLevel6() == true) {
            screen.show(mainPanel, "level6");
            level6.loop();
            if (level6.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level6.resetBackClick();
                levelScreen.resetClickLevel6();
            }
            if (level6.levelCompleted() == true) {
                this.levelsDone.set(5, "true");
            }
        }
        if (levelScreen.clickedLevel7() == true) {
            screen.show(mainPanel, "level7");
            level7.loop();
            if (level7.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level7.resetBackClick();
                levelScreen.resetClickLevel7();
            }
            if (level7.levelCompleted() == true) {
                this.levelsDone.set(6, "true");
            }
        }
        if (levelScreen.clickedLevel8() == true) {
            screen.show(mainPanel, "level8");
            level8.loop();
            if (level8.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level8.resetBackClick();
                levelScreen.resetClickLevel8();
            }
            if (level8.levelCompleted() == true) {
                this.levelsDone.set(7, "true");
            }
        }
        if (levelScreen.clickedLevel9() == true) {
            screen.show(mainPanel, "level9");
            level9.loop();
            if (level9.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level9.resetBackClick();
                levelScreen.resetClickLevel9();
            }
            if (level9.levelCompleted() == true) {
                this.levelsDone.set(8, "true");
            }
        }
        if (levelScreen.clickedLevel10() == true) {
            screen.show(mainPanel, "level10");
            level10.loop();
            if (level10.getBackClick() == true) {
                screen.show(mainPanel, "LvPage");
                level10.resetBackClick();
                levelScreen.resetClickLevel10();
            }
            if (level10.levelCompleted() == true) {
                this.levelsDone.set(9, "true");
            }
        }
        // is quit button clicked
        if (menuScreen.getQuitClick() == true) {
            // updates the user data on close
            writeFile("userData.csv");
            frame.dispose();
        }

    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {

        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        loop();
        repaint();
        // updates the user data if the user does not quit properly
        /// i.e just closes the frame
        writeFile("userData.csv");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        keepclimbing game = new keepclimbing();
    }

}