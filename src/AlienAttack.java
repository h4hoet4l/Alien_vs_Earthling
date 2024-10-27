import java.awt.Image;
import javax.swing.*;

/**
 * Represents an alien attack in the game, characterized by its position,
 * level, speed, and appearance.
 */
public class AlienAttack {
    int level;
    ImageIcon appearance;
    Image image; // Added image field for direct access to Image
    public int speed;
    int x;
    int y;
    public final int attack = 5;

    /**
     * Constructs an AlienAttack object with the specified position and level.
     *
     * @param x the x-coordinate of the alien attack's initial position
     * @param y the y-coordinate of the alien attack's initial position
     * @param level the level of the alien attack, affecting its attributes
     */
    public AlienAttack(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;
        setAlienAttackImage();
    }
     
    /**
     * Sets the appearance of the alien attack based on its level
     * and initializes its speed. The image field is also initialized
     * from the appearance.
     */
    public final void setAlienAttackImage() {
        if (level == 1) {
            this.appearance = new ImageIcon("src/images/alien_attack2.png", "alien beam");
            this.speed = 10;
        } else {
            this.appearance = new ImageIcon("src/images/alien_attack.png", "alien beam");
            this.speed = 12;
        }
    
        // Initialize image field from appearance
        this.image = appearance.getImage();
    }
    
    // Constructor with two parameters (default level set to 1)
    public AlienAttack(int x, int y) {
        this(x, y, 1); // Calls the main constructor with a default level of 1
    }
    
    public void shoot() {
        this.x -= 12;
    }
}
