import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Alien {
    Image appearance;
    public int x;
    public int y;
    public final int width;
    public final int height;
    public int health = 10;
    public AlienAttack beam; 

    public Alien (int x, int y) {
        this.x = x;
        this.y = y;
        setAlienImage();
        width = appearance.getWidth(null);
        height = appearance.getHeight(null);
    }
    private void setAlienImage() {
        Random random = new Random();
        int randNum = random.nextInt(100);
        
        if ((randNum % 2 == 0) ) {
            this.appearance = new ImageIcon(
                "src/images/alien2.png", "Advanced alien image").getImage();
            this.beam = new AlienAttack(x, y, 2); 
        } else {
             this.appearance = new ImageIcon(
                "src/images/alien.png", "Advanced alien image").getImage();
            this.beam = new AlienAttack(x, y, 1);
        }
    }


    public void move() {
        this.x -= 10;
    }

    public int[] getCoordinates() {
        int[] coordinates = {x, y};
        return coordinates;
    }

    public Image getImage() {
        return this.appearance;
    }
}
