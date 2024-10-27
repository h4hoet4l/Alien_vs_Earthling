import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 * Represents an alien in a game. The alien can move and has an appearance,
 * health, and an associated attack beam.
 *
 * @Author: Aldair Pedro
 * @ID: 1589296
 *
 * @Author: Gihak Kim
 * @ID: 2083132
 */
public class Alien {
    Image appearance;
    public int x;
    public int y;
    public final int width;
    public final int height;
    public int health = 10;
    public AlienAttack beam; 

    /**
     * Constructs an Alien object with the specified position.
     *
     * @param x the x-coordinate of the alien's initial position
     * @param y the y-coordinate of the alien's initial position
     */
    public Alien(int x, int y) {
        this.x = x;
        this.y = y;
        setAlienImage();
        width = appearance.getWidth(null);
        height = appearance.getHeight(null);
    }

    /**
     * Sets the appearance of the alien randomly, choosing between two images,
     * and initializes the alien's attack beam based on the chosen image.
     */
    private void setAlienImage() {
        Random random = new Random();
        int randNum = random.nextInt(100);
        
        if ((randNum % 2 == 0)) {
            this.appearance = new ImageIcon(
                "src/images/alien2.png", "Advanced alien image").getImage();
            this.beam = new AlienAttack(x, y, 2); 
        } else {
            this.appearance = new ImageIcon(
                "src/images/alien.png", "Advanced alien image").getImage();
            this.beam = new AlienAttack(x, y, 1);
        }
    }

    /**
     * Moves the alien left on the screen by a fixed amount.
     * This method updates the alien's x-coordinate.
     */
    public void move() {
        this.x -= 10;
    }

    /**
     * Returns the current coordinates of the alien as an array.
     *
     * @return an array containing the x and y coordinates of the alien
     */
    public int[] getCoordinates() {
        int[] coordinates = {x, y};
        return coordinates;
    }

    public Image getImage() {
        return this.appearance;
    }
}
