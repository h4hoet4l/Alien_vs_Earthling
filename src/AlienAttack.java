import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class AlienAttack {
    int damage;
    int level;
    ImageIcon appearance;
    int speed;
    int x;
    int y;

    public AlienAttack(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.damage = level * 2;
        this.level = level;
        if (level == 1) {
            this.appearance = new ImageIcon("images/alien_attack.png", "alien beam");
            this.speed = 10;
        } else if (level == 2) {
            this.appearance = new ImageIcon("images/alien_attack2.png", "alien beam");
            this.speed = 12;
        } else {
            this.appearance = new ImageIcon("images/alien_attack3.png", "alien beam"); //create 1 more alien_attack image
            this.speed = 14;
        }
    
    }
    void shoot() {
        this.x -= speed;
    }
}
