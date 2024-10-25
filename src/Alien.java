import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Alien {
    ImageIcon appearance;
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
            this.appearance = new ImageIcon("images/alien2.png", "Advanced alien image");
            this.width = 400;
            this.height = 400;
            this.beam = new AlienAttack(); 
        } else {
            this.appearance = new ImageIcon("images/alien1.png", "Advanced alien image");
            this.width = 500;
            this.height = 500;
            this.beam = new AlienAttack();
        }
    }
  public void move() {
        this.x -= 7;
    }
}
