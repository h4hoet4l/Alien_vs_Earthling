import java.awt.*;
import javax.swing.*;

/**
 * Represents an Earthling character in the game with specific attributes 
 * such as position, appearance, speed, health, and attack capability.
 */
public class Earthling {
    Image appearance;
    public int x;
    public int y;
    public final int width;
    public final int height;
    public int health = 20;
    public EarthlingAttack beam;
    public int speed = 12;

    /**
     * Constructs an Earthling with specified initial coordinates and a type 
     * that determines its appearance.
     * 
     * @param x the initial x-coordinate of the Earthling
     * @param y the initial y-coordinate of the Earthling
     * @param earthling the type of Earthling (0, 1, or other) to set specific appearance
     */
    public Earthling(int x, int y, int earthling) {
        this.x = x;
        this.y = y;
        switch (earthling) {
            case 0 -> {
                this.appearance = new ImageIcon("src/images/earthling.png").getImage();
                this.width = 10;
                this.height = 10;
            }
            case 1 -> {
                this.appearance = new ImageIcon("src/images/earthling2.png").getImage();
                this.width = 10;
                this.height = 10;
            }
            default -> {
                this.appearance = new ImageIcon("src/images/earthling3.png").getImage();
                this.width = 10;
                this.height = 10;
            }
        }
        this.beam = new EarthlingAttack(x, y);
    }

    /**
     * Returns the current coordinates of the Earthling as an array.
     * 
     * @return an array containing the x and y coordinates of the Earthling
     */
    public int[] getCoordinates() {
        int[] coordinates = {x, y};
        return coordinates;
    }

    /**
     * Returns the image representation of the Earthling.
     * 
     * @return the Image object representing the Earthling's appearance
     */
    public Image getImage() {
        return this.appearance;
    }

    /**
     * Moves the Earthling in a specified direction based on input.
     * 
     * @param direction the direction character ('w', 's', 'a', or 'd') representing 
     *                  up, down, left, and right, respectively
     */
    public void move(char direction) {
        switch (direction) {
            case 'w' -> this.y -= speed;
            case 's' -> this.y += speed;
            case 'a' -> this.x -= speed;
            case 'd' -> this.x += speed;
            default -> {
            }
        }
    }
}
