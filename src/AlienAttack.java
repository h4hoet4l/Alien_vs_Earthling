import java.awt.Image;
import javax.swing.*;

public class AlienAttack {
    int damage;
    int level;
    ImageIcon appearance;
    Image image; // Added image field for direct access to Image
    int speed;
    int x;
    int y;
    int shoot;

    public AlienAttack(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.damage = level * 2;
        this.level = level;
        
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
    
    
    public void shoot() {
        this.x -= speed;
    }
}
