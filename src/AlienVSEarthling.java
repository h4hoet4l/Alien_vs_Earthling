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
    ImageIcon charSelection = new ImageIcon(new ImageIcon("src/images/character_page.png", 
            "selection screen for earthling with 3 options").getImage().getScaledInstance(300, 
                            300, Image.SCALE_SMOOTH));
    ImageIcon currentScreen;
    JLabel screenImage = new JLabel();
    BGM music = new BGM("src/audio/MainPageBGM.wav", true);
    Image earthling;
    
    public AlienVSEarthling() {
        
        setSize(Main.ScreenWidth, Main.ScreenHeight);
        changeCurrentScreen(homePage, true);
        setVisible(true);
        setTitle("Aliens vs Earthlings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        music.start();
        addKeyListener(new KeyListener());
        


    }
    public void drawCurrentScreen(Graphics g) {
        

    }

    public void changeCurrentScreen(ImageIcon screen, boolean gameStart) {
        this.currentScreen = screen;
        screenImage.setIcon(currentScreen);
        if (gameStart) {
            this.add(screenImage);
            this.pack();
        } else {
            this.repaint();
        }
    }

    public void startGame(){
        changeCurrentScreen(homePage, true);

    }

    void chooseEarthling(Image earthling) {
    	this.earthling = earthling;
    }


    

    class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1:
                    if(currentScreen == charSelection) {
                        
                    }
                    break;
                case KeyEvent.VK_2:
                    if (currentScreen == charSelection) {
                    	
                    }
                    break;
                case KeyEvent.VK_3:
                    if (currentScreen == charSelection) {
                    
                    }
                    break;
                case KeyEvent.VK_ENTER:
                if (currentScreen == homePage) {
                    changeCurrentScreen(charSelection, false);  
                }
                break;
                
            }
        }
}
}