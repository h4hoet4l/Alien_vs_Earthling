import java.awt.*;
import javax.swing.*;

public class EarthlingAttack {
    public Image image;
    public int x;
    public int y;
    public int width;
    public int height;
    public final int attack = 5;
    static int earthlingColor;

    public EarthlingAttack(int x, int y) {
        this.x = x;
        this.y = y;
        setEarthlingAttackImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    private void setEarthlingAttackImage() {
        image = switch (earthlingColor) {
            case 2 -> new ImageIcon("src/images/earthling_attack2.png").getImage();
            case 3 -> new ImageIcon("src/images/earthling_attack3.png").getImage();
            default -> new ImageIcon("src/images/earthling_attack.png").getImage();
        };
    }        
    public void shoot() {
        this.x += 15;
    }
}
