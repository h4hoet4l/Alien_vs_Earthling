import java.awt.*;
import javax.swing.*;

public class AlienBoss {
    public Image image = new ImageIcon("src/images/alien_boss.png").getImage();
    
    int x;
    int y;

    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int hp = 30;

    public AlienBoss(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Image getBoss() {
    	return this.image;
    }
    
}