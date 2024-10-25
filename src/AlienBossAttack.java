import java.awt.*;
import javax.swing.*;

public class AlienBossAttack {
    Image image = new ImageIcon("src/images/alien_boss_attack.png").getImage();
    int x;
    int y;
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int attack = 10;

    public AlienBossAttack(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void shoot() {
        this.x -= 12;
    }
}