import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Alienattack {
    int damage;
    int level;
    ImageIcon appearance;

    public Attack(int level) {
        this.damage = level * 2;
        this.level = level;
        if (level == 1) {
            this.appearance = new ImageIcon("images/earthling_attack.png", "earthling beam");
        } else if (level == 2) {
            this.appearance = new ImageIcon("images/earthling_attack_double.png", "earthling beam");
        } else {
            this.appearance = new ImageIcon("images/earthling_attack_triple.png", "earthling beam");
        }
    
    }
    void update() {
        if (level == 1) {
            this.appearance = new ImageIcon("images/earthling_attack.png", "earthling beam");
        } else if (level == 2) {
            this.appearance = new ImageIcon("images/earthling_attack_double.png", "earthling beam");
        } else {
            this.appearance = new ImageIcon("images/earthling_attack_triple.png", "earthling beam");
        }
        this.damage = level * 2;
    }
    
}