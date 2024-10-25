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
            this.appearance = new ImageIcon("src/images/alien2.png", "Advanced alien image");
            this.width = 400;
            this.height = 400;
            this.beam = new AlienAttack(); 
        } else {
            this.appearance = new ImageIcon("src/images/alien1.png", "Advanced alien image");
            this.width = 500;
            this.height = 500;
            this.beam = new AlienAttack();
        }
    }
<<<<<<< HEAD
    public void move() {
=======
  public void move() {
>>>>>>> a15ca8f28667706d622e3b6fe7aa46b3e9422050
        this.x -= 7;
    }
}
