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
    public int speed = 20; 

    public Earthling(int x, int y, int earthling) {
        this.x = x;
        this.y = y;
        switch (earthling) {
            case 0 -> {
                this.appearance = new ImageIcon("src/images/earthling.png").getImage();
                this.width = 10;
                this.height = 10;
            }
            case 1 -> {
                this.appearance = new ImageIcon("src/images/earthling2.png").getImage();
                this.width = 10;
                this.height = 10;
            }
            default -> {
                this.appearance = new ImageIcon("src/images/earthling3.png").getImage();
                this.width = 10;
                this.height = 10;
            }
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
        switch (direction) {
            case 'w' -> this.y -= speed;
            case 's' -> this.y += speed;
            case 'a' -> this.x -= speed;
            case 'd' -> this.x += speed;
            default -> {
            }
        }
    }
}
