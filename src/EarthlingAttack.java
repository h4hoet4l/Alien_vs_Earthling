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
        switch (earthlingColor) {
            case 2:
                image = new ImageIcon("src/images/earthling_attack2.png").getImage();
                break;
            case 3:
                image = new ImageIcon("src/images/earthling_attack3.png").getImage();
                break;
            default:
                image = new ImageIcon("src/images/earthling_attack.png").getImage();
                break;
        }
    }
        
    public void shoot() {
        this.x += 15;
    }
}
