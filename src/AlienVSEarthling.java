import javax.swing.*;
import java.awt.*;


public class AlienVSEarthling extends JFrame{

    ImageIcon homePage = new ImageIcon("images/home_page.png", "Game main screen");
    ImageIcon gameBackground = new ImageIcon("images/game_page.png", "Spacey background");
    ImageIcon loadingScreen = new ImageIcon("images/loading_page.png", "Game loading");
    ImageIcon charSelection = new ImageIcon("images/character_page.png", 
                                                "selection screen for earthling with 3 options");
    ImageIcon currentScreen;
    public AlienVSEarthling() {
        
        setSize(400, 400);
        setVisible(true);
        setTitle("Aliens vs Earthlings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void drawCurrentScreen(Graphics g) {
        g.drawImage(currentScreen, 0, 0);

    }

    public void changeCurrentScreen(ImageIcon screen) {
        this.currentScreen = screen;
    }
}