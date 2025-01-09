import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class about extends JPanel implements ActionListener {
    // instance variables
    private int width;
    private int height;
    private JButton back = new JButton();
    private boolean clickedBack = false;

    public about(int width, int height) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        // title for the screen
        JLabel heading = new JLabel("About", JLabel.CENTER);
        // Inforamtion on the screen
        JLabel info = new JLabel(
                "<html><p>Climber is a climbing game where you move ball and complete the levels. You use the forward arrow key (also D key) to go forward and back arrow key (also A key) to stop.<br> Made by Sukhampreet Sandhu in VS Code <br> Copyright 2023 @Climber All rights are Protected </p></html>",
                JLabel.CENTER);

        // setting the font of title and info
        heading.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        info.setFont(new Font("Arial", Font.BOLD, 23));

        // setting up the location of the heading and info on the screen
        heading.setBounds(this.width / 2 - (this.width / 6), this.height / 40, this.width / 3, 50);
        info.setBounds(this.width / 2 - (this.width / 4), this.height / 8, this.width / 2, 500);

        // setting up the back button
        this.back.setText("Back");
        int length = 80;
        this.back.setBounds(this.width - (this.width - 50), this.height - 70, length, length / 3);

        // adds the button to the screen
        this.add(this.back);
        this.add(heading);
        this.add(info);

        // adds action listener to the back button
        this.back.addActionListener(this);
        this.back.setActionCommand("back");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * sets the boolean based on the command
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String command = e.getActionCommand();
        // checks is the command true
        if (command.equals("back")) {
            this.clickedBack = true;
        }
    }

    /**
     * @return this.clickedBack
     */
    public boolean getClickedBack() {
        return this.clickedBack;
    }

    /**
     * @return this.clickedBack as fasle
     */
    public boolean resetBackClick() {
        return this.clickedBack = false;
    }
}