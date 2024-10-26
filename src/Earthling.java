import javax.swing.*;
import java.awt.*;

public class Earthling {
    Image appearance;
    public int x;
    public int y;
    public final int width;
    public final int height;
    public int health = 20;
    public EarthlingAttack beam;
    public int speed; 

    public Earthling(int x, int y, int earthling) {
        this.x = x;
        this.y = y;
        if (earthling == 0) {
            this.appearance = new ImageIcon("src/images/earthling.png").getImage();
            this.width = 10;
            this.height = 10;
        } else if (earthling == 1) {
            this.appearance = new ImageIcon("src/images/earthling1.png").getImage();
            this.width = 10;
            this.height = 10;
        } else {
            this.appearance = new ImageIcon("src/images/earthling2.png").getImage();
            this.width = 10;
            this.height = 10;
        }
        this.beam = new EarthlingAttack(x, y); 

        
    }

    public int[] getCoordinates() {
        int[] coordinates = {x, y};
        return coordinates;
    }

    public Image getImage() {
        return this.appearance;
    }

    public void move(char direction) {
        if (direction == 'w') {
            this.y -= speed;
        } else if (direction == 's') {
            this.y += speed;
        } else if (direction == 'a') {
            this.x -= speed;
        } else if (direction == 'd') {
            this.x += speed;
        }
    }

}