import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class levelImage extends JComponent {
    // instance varibales
    private int[] points;
    private BufferedImage image;

    /**
     * @param path of the image file to draw the level from
     */
    public levelImage(String path) {
        this.image = null;
        // read the image and catch any errors
        try {
            this.image = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // store the width of the image
        int size = this.image.getWidth();
        // array to store the data points
        this.points = new int[size];
        // moves horizontally, in coloums
        for (int i = 0; i < size; i++) {
            // moves vertically, in rows
            for (int j = 0; j < this.image.getHeight(); j++) {
                // grabs the color of the current pixel
                int pixel = this.image.getRGB(i, j);
                // if it is a black colored pixel, stores the j- value
                if (pixel == Color.BLACK.getRGB()) {
                    this.points[i] = j;
                    // breaks after the birst black pixel
                    break;
                }
            }
        }

        // sets the size of the panel same as the size of the image
        this.setPreferredSize(new Dimension(size, image.getHeight()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     * draws the levels from the points array
     */
    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.image.getWidth(), this.image.getHeight());
        for (int i = 0; i < this.points.length - 1; i++) {
            g.setColor(Color.BLACK);
            // draws continous lines from the current point to the next
            g.drawLine(i, this.points[i], i + 1, this.points[i + 1]);
        }
    }

    /**
     * @return this.points
     */
    public int[] getPoints() {
        return this.points;
    }

    /**
     * @param spot where a certain y-value for the ground is stored
     * @return the value at that spot
     */
    public int getSpecificPoints(int spot) {
        return this.points[spot];
    }
}
