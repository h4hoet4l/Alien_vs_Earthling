import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class AlienVSEarthling extends JFrame{

    ImageIcon homePage = new ImageIcon(new ImageIcon("src/images/home_page.png", 
                    "Game main screen").getImage().getScaledInstance(Main.ScreenWidth, Main.ScreenHeight, Image.SCALE_SMOOTH));
    ImageIcon gameBackground = new ImageIcon(new ImageIcon("src/images/game_page.png", 
            "Spacey background").getImage().getScaledInstance(Main.ScreenWidth, Main.ScreenHeight, Image.SCALE_SMOOTH));
    ImageIcon loadingScreen = new ImageIcon(new ImageIcon("src/images/loading_page.png",
             "Game loading").getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH));
    ImageIcon charSelection = new ImageIcon(new ImageIcon("src/images/character_page.png", 
            "selection screen for earthling with 3 options").getImage().getScaledInstance(Main.ScreenWidth, 
                            Main.ScreenHeight, Image.SCALE_SMOOTH));
    ImageIcon currentScreen;
    JLabel screenImage = new JLabel();
    BGM music = new BGM("src/audio/MainPageBGM.wav", true);
    Earthling earthling;
    EarthlingPanel drawnEarthling;
    boolean endGame;
    Timer timer;
    
    
    
    
    public AlienVSEarthling() {
        
        setSize(Main.ScreenWidth, Main.ScreenHeight);
        changeCurrentScreen(homePage, true);
        setVisible(true);
        setTitle("Aliens vs Earthlings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        music.start();
        addKeyListener(new KeyListener());
        
    
        


    }
    public void drawCurrentScreen(Graphics g) {
        

    }
    class Refresher implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            updateScreen();
            
        }
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

    public void changeMusic(BGM music) {
        this.music.stop();
        this.music = music;
        music.start();
    }

    public void startGame(){
        changeCurrentScreen(gameBackground, false);
        changeMusic(new BGM("src/audio/FirstRoundBGM.wav", true));
        drawGame(this.getGraphics());
        this.timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateScreen();
        
                if (endGame) {
                }
            }    
        });
        timer.start();
        
    }

    void chooseEarthling(int earthling) {
        switch (earthling) {
            case 1:
                this.earthling = new Earthling(1, Main.ScreenHeight / 2, earthling);
                break;
            case 2:
                this.earthling = new Earthling(2, Main.ScreenHeight / 2, earthling);
                break;
            default:
                this.earthling = new Earthling(0, Main.ScreenHeight / 2, earthling);
                break;
        }
        
    }

    public void drawGame(Graphics g) {
        this.remove(screenImage);
        this.drawnEarthling = new EarthlingPanel();
        this.add(drawnEarthling);
        
    }
    public void updateScreen() {
        drawnEarthling.change(getGraphics());
    }
    

    // KeyListener inner class
    class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    if (firstRound.isCurrentStage()) {
                        firstRound.setMoveUp(true);
                    }
                    break;
                case KeyEvent.VK_S:
                    if (firstRound.isCurrentStage()) {
                        firstRound.setMoveDown(true);
                    }
                    break;
                case KeyEvent.VK_A:
                    if (firstRound.isCurrentStage()) {
                        firstRound.setMoveLeft(true);
                    }
                    break;
                case KeyEvent.VK_D:
                    if (firstRound.isCurrentStage()) {
                        firstRound.setMoveRight(true);
                    }
                    break;
                case KeyEvent.VK_R:
                    if (firstRound.isStageOver() && firstRound.isCurrentStage()) {
                        firstRound.reset();
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (firstRound.isCurrentStage()) {
                        firstRound.setShooting(true);
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (isHomePage) {
                        chooseCharacter();  // Transition to character selection page
                    }
                    break;
                case KeyEvent.VK_1:
                    if (isCharacterPage) {
                        firstRound.setEarthlingImage(
                            new ImageIcon("src/images/earthling.png").getImage());
                        gameStart();
                    }
                    break;
                case KeyEvent.VK_2:
                    if (isCharacterPage) {
                        firstRound.setEarthlingImage(
                            new ImageIcon("src/images/earthling2.png").getImage());
                        gameStart();
                    }
                    break;
                case KeyEvent.VK_3:
                    if (isCharacterPage) {
                        firstRound.setEarthlingImage(
                            new ImageIcon("src/images/earthling3.png").getImage());
                        gameStart();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    firstRound.setMoveUp(false);
                    break;
                case KeyEvent.VK_S:
                    firstRound.setMoveDown(false);
                    break;
                case KeyEvent.VK_A:
                    firstRound.setMoveLeft(false);
                    break;
                case KeyEvent.VK_D:
                    firstRound.setMoveRight(false);
                    break;
                case KeyEvent.VK_SPACE:
                    firstRound.setShooting(false);
                    break;
                default:
                    break;
            }
        }
    }

class EarthlingPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(gameBackground.getImage(), 0, 0, null);
    g.drawImage(earthling.getImage(), earthling.getCoordinates()[0],earthling.getCoordinates()[1], null);
    }
    void change(Graphics g) {
        g.drawImage(gameBackground.getImage(), 0, 0, null);
        g.drawImage(earthling.getImage(), earthling.getCoordinates()[0],earthling.getCoordinates()[1], null);
        revalidate();
    }
}

}
