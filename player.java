import java.awt.Graphics;
import java.awt.Color;

public class player {
    // instance varibales
    private int x;
    private int y;
    private int radius;
    private boolean onGround;
    private double dx;
    private double dy;
    private double acceleration;

    private final int gravity = 1;
    public static final int Right = 1;
    public static final int Left = -1;

    /**
     * constructor
     * 
     * @param x      positon of the player
     * @param y      positon of the player
     * @param radius of the circle
     */
    public player(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.onGround = false;
        this.dx = 0;
        this.dy = 0;
        this.radius = radius;
    }

    /**
     * @param g draws the player to the screen
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        drawCircle(g, this.x, this.y, this.radius);
    }

    /**
     * @param g       makes custom circle
     * @param xCenter x-coordinate of the player
     * @param yCenter y-coordinate of the player
     * @param r       radius of the circle
     */
    public void drawCircle(Graphics g, int xCenter, int yCenter, int r) {
        g.fillOval(xCenter - r, yCenter - r - 1, 2 * r, 2 * r);
    }

    /**
     * sets the horizontal movement
     * 
     * @param value which direction to move
     */
    public void setDX(int value, int[] points) {
        if (value == -1) {
            putBreaks(points);
        } else {
            this.dx = value;
        }
    }

    /**
     * updates the player
     * 
     * @param points data where the ground is located
     */
    public void update() {
        // move the x value
        this.x = (int) Math.round(this.x + this.dx);
        // if player is not on ground should have gravity
        if (!onGround) {
            dy = dy + this.gravity;
            this.y = (int) Math.round(this.y + this.dy);
        }
    }

    // moves the player right
    public void moveRight(int[] points) {
        // as long as the player is above the ground
        if (this.y + this.radius > points[this.x]) {
            // checks the difference between the current y positon and the next y posiotn
            // and +/- that from current to move
            this.y = this.y + (-(points[this.x] - points[x + 1]));
            // is it downhill
            if (points[this.x + 20] - points[this.x] > 5) {
                // makes the player move fast
                this.acceleration = 7;
                accelerate(points);
            } else if (points[this.x] - points[this.x - 50] > 10) {
                // smoothes in as the player is going away from the downhill roll
                this.acceleration = 4;
                accelerate(points);
            }
        }
    }

    /**
     * @param points data of the ground
     *               makes the player stop slowly
     */
    public void putBreaks(int[] points) {
        this.x = this.x - (int) Math.round(0.2 * this.acceleration);
        this.y = points[this.x] - 6;
    }

    /**
     * @param points data of the ground
     *               increases the speed of the player and decreases overtime
     * 
     */
    public void accelerate(int[] points) {
        this.x = this.x + (int) Math.round(0.2 * this.acceleration);
        this.y = points[this.x] - 6;
    }

    /**
     * @param value set on ground true or false
     */
    public void setOnGround(boolean value) {
        this.onGround = value;
    }

    /**
     * @param points data where the ground is located
     * @return true if player is above the ground
     */
    public boolean onTopOfGround(int points[]) {
        // falling and above the ground
        if (dy > 0 && this.y < points[this.x]) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks collison with the ground
     * 
     * @param points data where the ground is located
     * @return true if the player is hitting the ground
     */
    public boolean collides(int points[]) {
        if (this.y > points[this.x] - this.radius) {
            return true;
        }
        return false;
    }

    /**
     * @return x-coordinate of the player
     */
    public int getX() {
        return this.x;
    }

    /**
     * resets the player back to original position
     * 
     * @param x coordinate of the player
     * @param y coordinate of the player
     */
    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        this.onGround = false;
        this.dx = 0;
        this.dy = 0;
    }
}