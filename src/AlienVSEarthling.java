import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * The AlienVSEarthling class represents the main frame for the 
 * "Alien vs Earthling" game. It manages the game states, user 
 * input, and rendering of game components.
 *
 * @Author: Aldair Pedro
 * @ID: 1589296
 *
 * @Author: Gihak Kim
 * @ID: 2083132
 */
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

    /**
     * Constructs an instance of the AlienVSEarthling game.
     * Initializes the game window, sets its properties, and
     * starts background music. The game is set to the home page 
     * state initially.
     */
    public AlienVSEarthling() {
        setTitle("Alien vs Earthling");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
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

    class KeyListener extends KeyAdapter {
        /**
        * Handles the key press events for controlling the game.
        * Depending on the key pressed, it can trigger movements, 
        * shooting, or transitions between different game states such as 
        * the character selection screen and game start.
        *
        * @param e the KeyEvent containing information about the key press
        */
        @SuppressWarnings("override")
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
                        EarthlingAttack.earthlingColor = 1; 
                        gameStart();
                    }
                }
                case KeyEvent.VK_2 -> {
                    if (isCharacterPage) {
                        firstRound.setEarthlingImage(
                                new ImageIcon("src/images/earthling2.png").getImage());
                        EarthlingAttack.earthlingColor = 2; 
                        gameStart();
                    }
                }
                case KeyEvent.VK_3 -> {
                    if (isCharacterPage) {
                        firstRound.setEarthlingImage(
                                new ImageIcon("src/images/earthling3.png").getImage());
                        EarthlingAttack.earthlingColor = 3; 
                        gameStart();
                    }
                }
                case KeyEvent.VK_ESCAPE -> System.exit(0);
                default -> {
                }
            }
        }

        /**
        * Handles the key release events for controlling the game.
        * It stops movements or shooting actions when the respective keys 
        * are released.
        *
        * @param e the KeyEvent containing information about the key release
        */
        @SuppressWarnings("override")
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
    
    /**
     * Paints the game components on the JFrame.
     * It creates a buffer image to draw on and then draws that 
     * image onto the frame. This method is called to refresh 
     * the screen whenever there are changes to be displayed.
     *
     * @param g the Graphics context to draw on
     */
    @SuppressWarnings("override")
    public void paint(Graphics g) {
        bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage, 0, 0, null);
    }
    
    /**
     * Draws the current game screen based on the current state.
     * It selects which image to render (home page, loading page, 
     * character selection page, or game page) and also calls the 
     * firstRound's draw method to render the game components.
     *
     * @param g the Graphics context to draw on
     */
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
