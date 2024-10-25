import java.awt.*;
import javax.swing.*;

public class EarnHealth {
    Image image = new ImageIcon("src/images/HPItem.png").getImage();
    
    int x;

    int y;

    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int hp = 10;
    

    public EarnHealth(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void move() {
         this.x -= 7;
    }
    
}