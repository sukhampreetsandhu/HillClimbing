import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class MainMenu extends JPanel implements ActionListener {
    // instance varibales
    private JButton start;
    private JButton about;
    private JButton quit;
    private String title;
    private JLabel heading = new JLabel();
    private int width;
    private int height;
    private int buttonWidth = 220;
    private int buttonHeight = 60;
    private int spacer = 100;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private boolean clickStart;
    private boolean clickAbout;
    private boolean clickQuit;

    /**
     * Constructor
     * 
     * @param title  tittle of the game
     * @param width  width of the game window
     * @param height width of the game window
     */
    public MainMenu(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setVisible(true);

        // setting the text of the buttons
        this.heading.setText(this.title);
        this.start = new JButton("Start");
        this.about = new JButton("About");
        this.quit = new JButton("Quit");

        // adding buttons to the arrylist
        buttons.add(this.start);
        buttons.add(this.about);
        buttons.add(this.quit);

        // location of the content
        this.start.setBounds(width / 2 - buttonWidth / 2, 220, this.buttonWidth, this.buttonHeight);
        this.about.setBounds(start.getX(), start.getY() + spacer, this.buttonWidth, this.buttonHeight);
        this.quit.setBounds(start.getX(), about.getY() + spacer, this.buttonWidth, this.buttonHeight);
        this.heading.setBounds(start.getX() + spacer / 4, start.getY() - spacer - spacer / 4, this.buttonWidth,
                this.buttonHeight);
        this.heading.setFont(new Font("Comic Sans MS", Font.BOLD, 50));

        // sets action commands for buttons
        this.start.setActionCommand("start");
        this.about.setActionCommand("about");
        this.quit.setActionCommand("quit");

        /**
         * adds actionlister to every button
         * add it to the panel
         * sets the font of the button
         */
        for (JButton b : this.buttons) {
            b.addActionListener(this);
            this.add(b);
            b.setFont(new Font("Arial", Font.BOLD, 35));
        }
        this.add(this.heading);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * sets the boolean values as per the command
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("start")) {
            this.clickStart = true;
        } else if (command.equals("about")) {
            this.clickAbout = true;
        } else if (command.equals("quit")) {
            this.clickQuit = true;
        }
    }

    /**
     * @return clickStart
     */
    public boolean getStartClick() {
        return this.clickStart;
    }

    /**
     * @return clickAbout
     */
    public boolean getAboutclick() {
        return this.clickAbout;
    }

    /**
     * @return clickQuit
     */
    public boolean getQuitClick() {
        return this.clickQuit;
    }

    /**
     * @return clickStart
     */
    public boolean resetStartClick() {
        return this.clickStart = false;
    }

    /**
     * @return clickAbout
     */
    public boolean resetAboutClick() {
        return this.clickAbout = false;
    }
}
