import java.awt.Image;
import javax.swing.*;

public class AlienAttack {
    int level;
    ImageIcon appearance;
    Image image;
    public int speed;
    int x;
    int y;
    public final int attack = 5;

    public AlienAttack(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;
        setAlienAttackImage();
    }
        
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
