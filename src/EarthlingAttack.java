import java.awt.*;
import javax.swing.*;

/**
 * Represents an Earthling's attack in the game, including attributes such as
 * position, appearance, attack strength, and functionality for moving the attack.
 */
public class EarthlingAttack {
    public Image image;
    public int x;
    public int y;
    public int width;
    public int height;
    public final int attack = 3;
    static int earthlingColor;

    /**
     * Constructs an EarthlingAttack at specified coordinates with an image 
     * based on the Earthling's color.
     * 
     * @param x the initial x-coordinate of the attack
     * @param y the initial y-coordinate of the attack
     */
    public EarthlingAttack(int x, int y) {
        this.x = x;
        this.y = y;
        setEarthlingAttackImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    /**
     * Sets the image for the Earthling's attack based on the color specified by earthlingColor.
     * Images vary according to different earthling types.
     */
    private void setEarthlingAttackImage() {
        image = switch (earthlingColor) {
            case 2 -> new ImageIcon("src/images/earthling_attack2.png").getImage();
            case 3 -> new ImageIcon("src/images/earthling_attack3.png").getImage();
            default -> new ImageIcon("src/images/earthling_attack.png").getImage();
        };
    }
        
    /**
     * Moves the Earthling's attack in the rightward direction, simulating the attack action.
     */
    public void shoot() {
        this.x += 15;
    }
}
