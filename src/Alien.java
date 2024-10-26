import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Alien {
    Image appearance; // changed from ImageIcon to Image
    public int x;
    public int y;
    public final int width;
    public final int height;
    public int health = 10;
    public AlienAttack beam; 

    public Alien(int x, int y, boolean level_2) {
        this.x = x;
        this.y = y;
        if (level_2) {
            this.appearance = new ImageIcon(
                "src/images/alien2.png", "Advanced alien image").getImage();
            this.width = 400;
            this.height = 400;
            this.beam = new AlienAttack(x, y, 2); 
        } else {
             this.appearance = new ImageIcon(
                "src/images/alien1.png", "Advanced alien image").getImage();
            this.width = 500;
            this.height = 500;
            this.beam = new AlienAttack(x, y, 1);
        }
    }
    public void move() {

        this.x -= 7;
    }
}
