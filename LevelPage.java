import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelPage extends JPanel implements ActionListener {

    // instance varibales
    private int width;
    private int height;
    private JButton back = new JButton();
    private JPanel levels = new JPanel();
    private String[] levelsName = { "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7",
            "Level 8", "Level 9", "Level 10" };
    private boolean clickedLevel1;
    private boolean clickedLevel2;
    private boolean clickedLevel3;
    private boolean clickedLevel4;
    private boolean clickedLevel5;
    private boolean clickedLevel6;
    private boolean clickedLevel7;
    private boolean clickedLevel8;
    private boolean clickedLevel9;
    private boolean clickedLevel10;
    private boolean clickedBack;

    /**
     * constructor
     * 
     * @param width  the width of the game window
     * @param height the height of the game window
     */
    public LevelPage(int width, int height, ArrayList<String> levelsDone) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        // setting up the levels button panel on the screen
        this.levels.setBounds(this.width - (this.width - 20), 150, this.width - 50, 350);
        this.levels.setBackground(Color.lightGray);
        this.levels.setLayout(new GridLayout(2, 5, 25, 50));

        /**
         * name the button from the array of names
         * adds actionlister to every button
         * sest the action commad on every button
         * sets the font of the button
         * adds it to the panel
         */
        for (int i = 0; i < 10; i++) {
            String text = this.levelsName[i];
            JButton buttons = new JButton(text);
            // chnages the color of the button whter it is completed or not
            if (levelsDone.get(i).equals("true")) {
                buttons.setBackground(Color.YELLOW);
            }
            buttons.addActionListener(this);
            buttons.setActionCommand(text);
            buttons.setFont(new Font("Arial", Font.BOLD, 20));
            this.levels.add(buttons);
        }

        // setting up the title on the screen
        JLabel heading = new JLabel("Select Level", JLabel.CENTER);
        heading.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        heading.setBounds(this.width / 2 - (this.width / 6), this.height / 40, this.width / 3, 50);
        int length = 80;
        // adding the back button on the screen
        this.back.setBounds(this.width - (this.width - 50), this.height - 70, length, length / 3);
        this.add(heading);

        // setting up the back button
        this.back.setText("Back");
        this.back.addActionListener(this);
        this.back.setActionCommand("back");
        this.add(this.back);

        // adds the level button panel to the screen
        this.add(this.levels);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * sets the boolean based on the action command
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String command = e.getActionCommand();
        // checks the different commands and sets the boolean
        if (command.equals("back")) {
            this.clickedBack = true;
        } else if (command.equals("Level 1")) {
            this.clickedLevel1 = true;
        } else if (command.equals("Level 2")) {
            this.clickedLevel2 = true;
        } else if (command.equals("Level 3")) {
            this.clickedLevel3 = true;
        } else if (command.equals("Level 4")) {
            this.clickedLevel4 = true;
        } else if (command.equals("Level 5")) {
            this.clickedLevel5 = true;
        } else if (command.equals("Level 6")) {
            this.clickedLevel6 = true;
        } else if (command.equals("Level 7")) {
            this.clickedLevel7 = true;
        } else if (command.equals("Level 8")) {
            this.clickedLevel8 = true;
        } else if (command.equals("Level 9")) {
            this.clickedLevel9 = true;
        } else if (command.equals("Level 10")) {
            this.clickedLevel10 = true;
        }
    }

    /**
     * @return this.clickedBack
     */
    public boolean getBackClick() {
        return this.clickedBack;
    }

    /**
     * @return this.clikedBack
     */
    public boolean resetBackClick() {
        return this.clickedBack = false;
    }

    /**
     * @return this.clickedLevel1
     */
    public boolean clickedLevel1() {
        return this.clickedLevel1;
    }

    /**
     * @return this.clickedLevel1
     */
    public boolean resetClickLevel1() {
        return this.clickedLevel1 = false;
    }

    /**
     * @return this.clickedLevel2
     */
    public boolean clickedLevel2() {
        return this.clickedLevel2;
    }

    /**
     * @return this.clickedLevel2
     */
    public boolean resetClickLevel2() {
        return this.clickedLevel2 = false;
    }

    /**
     * @return this.clickedLevel3
     */
    public boolean clickedLevel3() {
        return this.clickedLevel3;
    }

    /**
     * @return this.clickedLevel3
     */
    public boolean resetClickLevel3() {
        return this.clickedLevel3 = false;
    }

    /**
     * @return this.clickedLevel4
     */
    public boolean clickedLevel4() {
        return this.clickedLevel4;
    }

    /**
     * @return this.clickedLevel4
     */
    public boolean resetClickLevel4() {
        return this.clickedLevel4 = false;
    }

    /**
     * @return this.clickedLevel5
     */
    public boolean clickedLevel5() {
        return this.clickedLevel5;
    }

    /**
     * @return this.clickedLevel5
     */
    public boolean resetClickLevel5() {
        return this.clickedLevel5 = false;
    }

    /**
     * @return this.clickedLevel6
     */
    public boolean clickedLevel6() {
        return this.clickedLevel6;
    }

    /**
     * @return this.clickedLevel6
     */
    public boolean resetClickLevel6() {
        return this.clickedLevel6 = false;
    }

    /**
     * @return this.clickedLevel7
     */
    public boolean clickedLevel7() {
        return this.clickedLevel7;
    }

    /**
     * @return this.clickedLevel7
     */
    public boolean resetClickLevel7() {
        return this.clickedLevel7 = false;
    }

    /**
     * @return this.clickedLevel8
     */
    public boolean clickedLevel8() {
        return this.clickedLevel8;
    }

    /**
     * @return this.clickedLevel8
     */
    public boolean resetClickLevel8() {
        return this.clickedLevel8 = false;
    }

    /**
     * @return this.clickedLevel9
     */
    public boolean clickedLevel9() {
        return this.clickedLevel9;
    }

    /**
     * @return this.clickedLevel9
     */
    public boolean resetClickLevel9() {
        return this.clickedLevel9 = false;
    }

    /**
     * @return this.clickedLevel10
     */
    public boolean clickedLevel10() {
        return this.clickedLevel10;
    }

    /**
     * @return this.clickedLevel10
     */
    public boolean resetClickLevel10() {
        return this.clickedLevel10 = false;
    }
}