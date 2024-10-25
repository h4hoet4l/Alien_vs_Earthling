import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;



public class AlienVSEarthling extends JFrame{

    ImageIcon homePage = new ImageIcon(new ImageIcon("src/images/home_page.png", 
                    "Game main screen").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
    ImageIcon gameBackground = new ImageIcon(new ImageIcon("src/images/game_page.png", 
            "Spacey background").getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH));
    ImageIcon loadingScreen = new ImageIcon(new ImageIcon("src/images/loading_page.png",
             "Game loading").getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH));
    ImageIcon charSelection = new ImageIcon(new ImageIcon("images/character_page.png", 
            "selection screen for earthling with 3 options").getImage().getScaledInstance(600, 
                            600, Image.SCALE_SMOOTH));
    ImageIcon currentScreen;
    JLabel screenImage = new JLabel();
    BGM music = new BGM("src/audio/MainPageBGM.wav", true);
    public AlienVSEarthling() {
        
        setSize(Main.ScreenWidth, Main.ScreenHeight);
        changeCurrentScreen(homePage);
        setVisible(true);
        setTitle("Aliens vs Earthlings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        music.start();
        
        


    }
    public void drawCurrentScreen(Graphics g) {
        

    }

    public void changeCurrentScreen(ImageIcon screen) {
        this.currentScreen = screen;
        drawCurrentScreen(getGraphics());
        this.paint(getGraphics());
        screenImage.setIcon(currentScreen);
        this.add(screenImage);
        this.pack();
    }

    public void startGame(){
        changeCurrentScreen(homePage);

    }
}