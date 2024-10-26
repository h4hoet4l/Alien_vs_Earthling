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

    public Earthling(int x, int y, Image appearance) {
        this.x = x;
        this.y = y;
        this.appearance = appearance;
        this.width = 10;
        this.height = 10;
        this.beam = new EarthlingAttack(x, y); 
        
    }
}