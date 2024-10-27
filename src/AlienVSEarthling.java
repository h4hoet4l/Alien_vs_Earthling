import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class AlienVSEarthling extends JFrame {
    
    // Game components
    private final FirstRound firstRound = new FirstRound();
    private BGM backgroundMusic;

    // Game states
    private boolean isHomePage;
    private boolean isLoadingPage;
    private boolean isGamePage;
    private boolean isCharacterPage;

    // Images
    private Image bufferImage;
    private Graphics screenGraphic;
    private final Image homePage = new ImageIcon("src/images/home_page.png").getImage();
    private final Image loadingPage = new ImageIcon("src/images/loading_page.png").getImage();
    private final Image gamePage = new ImageIcon("src/images/game_page.png").getImage();
    private final Image characterPage = new ImageIcon("src/images/character_page.png").getImage();

    // Constructor
    public AlienVSEarthling() {
        setTitle("Alien vs Earthling");
        setSize(Main.ScreenWidth, Main.ScreenHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        init(); // Initialize the game
    }

    // Initialize method
    private void init() {
        isHomePage = true;
        isLoadingPage = isCharacterPage = isGamePage = false;

        backgroundMusic = new BGM("src/audio/MainPageBGM.wav", true);
        backgroundMusic.start();

        addKeyListener(new KeyListener());
    }

    // Choose character screen
    private void chooseCharacter() {
        isHomePage = false;
        isCharacterPage = true;
    }

    // Game start method with loading
    private void gameStart() {
        isHomePage = false;
        isLoadingPage = true;

        Timer loadingTimer = new Timer();
        TimerTask loadingTask = new TimerTask() {
            @Override
            public void run() {
                backgroundMusic.stop();
                isLoadingPage = false;
                isGamePage = true;
                firstRound.start();
            }
        };
        loadingTimer.schedule(loadingTask, 5000);
    }

    // KeyListener inner class
    class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> {
                    if (firstRound.isCurrentStage()) {
                        firstRound.setMoveUp(true);
                    }
                }
                case KeyEvent.VK_S -> {
                    if (firstRound.isCurrentStage()) {
                        firstRound.setMoveDown(true);
                    }
                }
                case KeyEvent.VK_A -> {
                    if (firstRound.isCurrentStage()) {
                        firstRound.setMoveLeft(true);
                    }
                }
                case KeyEvent.VK_D -> {
                    if (firstRound.isCurrentStage()) {
                        firstRound.setMoveRight(true);
                    }
                }
                case KeyEvent.VK_R -> {
                    if (firstRound.isStageOver() && firstRound.isCurrentStage()) {
                        firstRound.reset();
                    }
                }
                case KeyEvent.VK_SPACE -> {
                    if (firstRound.isCurrentStage()) {
                        firstRound.setShooting(true);
                    }
                }
                case KeyEvent.VK_ENTER -> {
                    if (isHomePage) {
                        chooseCharacter();  // Transition to character selection page
                    }
                }
                case KeyEvent.VK_1 -> {
                    if (isCharacterPage) {
                        firstRound.setEarthlingImage(
                                new ImageIcon("src/images/earthling.png").getImage());
                        gameStart();
                    }
                }
                case KeyEvent.VK_2 -> {
                    if (isCharacterPage) {
                        firstRound.setEarthlingImage(
                                new ImageIcon("src/images/earthling2.png").getImage());
                        gameStart();
                    }
                }
                case KeyEvent.VK_3 -> {
                    if (isCharacterPage) {
                        firstRound.setEarthlingImage(
                                new ImageIcon("src/images/earthling3.png").getImage());
                        gameStart();
                    }
                }
                case KeyEvent.VK_ESCAPE -> System.exit(0);
                default -> {
                }
            }
        }

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> firstRound.setMoveUp(false);
                case KeyEvent.VK_S -> firstRound.setMoveDown(false);
                case KeyEvent.VK_A -> firstRound.setMoveLeft(false);
                case KeyEvent.VK_D -> firstRound.setMoveRight(false);
                case KeyEvent.VK_SPACE -> firstRound.setShooting(false);
                default -> {
                }
            }
        }
    }
    
 // Paint and screenDraw methods
    public void paint(Graphics g) {
        bufferImage = createImage(Main.ScreenWidth, Main.ScreenHeight);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage, 0, 0, null);
    }

    public void screenDraw(Graphics g) {
        if (isHomePage) {
            g.drawImage(homePage, 0, 0, null);
        }
        if (isCharacterPage) {
            g.drawImage(characterPage, 0, 0, null);
        }
        if (isLoadingPage) {
            g.drawImage(loadingPage, 0, 0, null);
        }
        if (isGamePage) {
            g.drawImage(gamePage, 0, 0, null);
            firstRound.drawGame(g);
        }
        this.repaint();
    }
}
