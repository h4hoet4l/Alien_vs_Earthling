import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The FirstRound class represents a game round 
 * where an Earthling character defends against alien attacks.
 * It handles the game logic, rendering graphics, 
 * and user input for controlling the Earthling's movement
 * and attacks. The class extends Thread to run the game loop in a separate thread.
 *
 * @Author: Aldair Pedro
 * @ID: 1589296
 *
 * @Author: Gihak Kim
 * @ID: 2083132
 */
public class FirstRound extends Thread {

    // Constants and Variables
    private EarthlingAttack currentEarthlingAttack;
    private Alien currentAlien;
    private AlienAttack currentAlienAttack;

    private final ArrayList<EarthlingAttack> earthlingAttacks = new ArrayList<>();
    private final ArrayList<Alien> aliens = new ArrayList<>();
    private final ArrayList<AlienAttack> alienAttacks = new ArrayList<>();

    private final int frameDelay = 20;
    private long previousTime;
    private int frameCount;
    private int totalScore;

    private BGM backgroundMusic;
    private BGM hitEffectSound;
    
    private final int earthlingSpeed = 10;
    private int earthlingHealth = 30;
    private boolean currentStage;
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean isShooting;
    private boolean isStageOver;
    private boolean isNextStage = false;
    private boolean isGameOver = false;
    private Image earthlingImage = new ImageIcon("src/images/earthling.png").getImage();
    private int earthlingPositionX;
    private int earthlingPositionY;
    private final int earthlingWidth = earthlingImage.getWidth(null);
    private final int earthlingHeight = earthlingImage.getHeight(null);

    // Getters and Setters
    public boolean isNextStage() {
        return isNextStage;
    }

    public boolean isStageOver() {
        return isStageOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isCurrentStage() {
        return this.currentStage;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public void setEarthlingImage(Image earthlingImage) {
        this.earthlingImage = earthlingImage;
    }

    public Image getEarthlingImage() {
        return this.earthlingImage;
    }

    @Override
    public void run() {
        backgroundMusic = new BGM("src/audio/FirstRoundBGM.wav", true);
        hitEffectSound = new BGM("src/audio/HitSound.wav", false);

        reset();
        while (true) {
            while (!isStageOver) {
                previousTime = System.currentTimeMillis();
                if (System.currentTimeMillis() - previousTime < frameDelay) {
                    try {
                        Thread.sleep(frameDelay - System.currentTimeMillis() + previousTime);
                        currentStage = true;
                        processKeyInput();
                        processEarthlingAttacks();
                        spawnAliens();
                        moveAliens();
                        processAlienAttacks();
                        frameCount++;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    * Resets the game state to its initial conditions, including resetting 
    * the health of the Earthling character, clearing existing attacks, 
    * and starting background music.
    */
    public void reset() {
        isStageOver = false;
        isGameOver = false;
        frameCount = 0;
        totalScore = 0;
        earthlingPositionX = 10;
        earthlingPositionY = (Main.SCREEN_HEIGHT - earthlingHeight) / 2;
        earthlingHealth = 35;

        backgroundMusic.start();

        earthlingAttacks.clear();
        aliens.clear();
        alienAttacks.clear();
    }

    /**
    * Processes the player's key input to control the Earthling's movement 
    * and shooting actions. It updates the Earthling's position based on 
    * directional movement flags and generates a new Earthling attack 
    * when the shooting condition is met.
    */
    private void processKeyInput() {
        if (moveUp && earthlingPositionY - earthlingSpeed > 0) {
            earthlingPositionY -= earthlingSpeed;
        }
        if (moveDown && earthlingPositionY 
            + earthlingHeight + earthlingSpeed < Main.SCREEN_HEIGHT) {
            earthlingPositionY += earthlingSpeed;
        }
        if (moveLeft && earthlingPositionX - earthlingSpeed > 0) {
            earthlingPositionX -= earthlingSpeed;
        }
        if (moveRight && earthlingPositionX 
            + earthlingWidth + earthlingSpeed < Main.SCREEN_WIDTH) {
            earthlingPositionX += earthlingSpeed;
        }
        if (isShooting && frameCount % 15 == 0) {
            currentEarthlingAttack = new EarthlingAttack (
                earthlingPositionX + 222, earthlingPositionY + 100);
            earthlingAttacks.add(currentEarthlingAttack);
        }
    }

    /**
    * Handles the logic for processing Earthling attacks. It iterates through 
    * the list of Earthling attacks and checks for collisions with 
    * the aliens. If an attack hits an alien, the alien's health is reduced, 
    * and if the alien's health drops to zero, it is removed from the game.
    */
    private void processEarthlingAttacks() {
        for (int i = 0; i < earthlingAttacks.size(); i++) {
            currentEarthlingAttack = earthlingAttacks.get(i);
            currentEarthlingAttack.shoot();

            for (int j = 0; j < aliens.size(); j++) {
                currentAlien = aliens.get(j);
                if (currentEarthlingAttack.x > currentAlien.x  
                    && currentEarthlingAttack.x < currentAlien.x + currentAlien.width
                    && currentEarthlingAttack.y > currentAlien.y 
                    && currentEarthlingAttack.y < currentAlien.y + currentAlien.height) {
                    currentAlien.health -= currentEarthlingAttack.attack;
                    earthlingAttacks.remove(currentEarthlingAttack);
                }
                if (currentAlien.health <= 0) {
                    hitEffectSound.start();
                    aliens.remove(currentAlien);
                    totalScore += 1000;
                }
            }
        }
    }

    /**
    * Spawns new aliens at random vertical positions on the right side of the screen 
    * at regular intervals defined by the frame count. The aliens are added to 
    * the aliens list to be rendered and processed in the game.
    */
    public void spawnAliens() {
        if (frameCount % 35 == 0) {
            currentAlien = new Alien(1121, (int) (Math.random() * 622));
            aliens.add(currentAlien);
        }
    }

    private void moveAliens() {
        for (int i = 0; i < aliens.size(); i++) {
            currentAlien = aliens.get(i);
            currentAlien.move();
        }
    }

    /**
    * Processes alien attacks against the Earthling character. It checks for 
    * collisions between the alien attacks and the Earthling. If a collision 
    * occurs, the Earthling's health is reduced accordingly. If the Earthling's 
    * health drops to zero, the game stage is marked as over.
    */
    private void processAlienAttacks() {
        if (frameCount % 50 == 0) {
            currentAlienAttack = new AlienAttack(currentAlien.x - 79, currentAlien.y + 35);
            alienAttacks.add(currentAlienAttack);
        }

        for (int i = 0; i < alienAttacks.size(); i++) {
            currentAlienAttack = alienAttacks.get(i);
            currentAlienAttack.shoot();

            if (currentAlienAttack.x > earthlingPositionX 
                && currentAlienAttack.x < earthlingPositionX + earthlingWidth
                && currentAlienAttack.y > earthlingPositionY 
                && currentAlienAttack.y < earthlingPositionY + earthlingHeight) {
                hitEffectSound.start();
                earthlingHealth -= currentAlienAttack.attack;
                alienAttacks.remove(currentAlienAttack);
                if (earthlingHealth <= 0) {
                    isStageOver = true;
                    isGameOver = true;
                }
            }
        }
    }

    /**
    * Draws the current game state onto the provided graphics context. 
    * This includes drawing the Earthling, any alien characters, and 
    * the game information such as score and health status.
    *
    * @param g The graphics context used for rendering the game elements.
    */
    public void drawGame(Graphics g) {
        drawEarthling(g);
        drawAliens(g);
        drawGameInfo(g);
    }

    /**
    * Draws the Earthling character on the provided graphics context. 
    * It also displays the Earthling's health bar above the character 
    * and renders any active Earthling attacks.
    *
    * @param g The graphics context used for rendering the Earthling and its health.
    */
    public void drawEarthling(Graphics g) {
        g.drawImage(earthlingImage, earthlingPositionX, earthlingPositionY, null);
        g.setColor(Color.GREEN);
        g.fillRect(earthlingPositionX - 1, earthlingPositionY - 40, earthlingHealth * 6, 20);
        for (int i = 0; i < earthlingAttacks.size(); i++) {
            currentEarthlingAttack = earthlingAttacks.get(i);
            g.drawImage(currentEarthlingAttack.image, 
                currentEarthlingAttack.x, currentEarthlingAttack.y, null);
        }
    }

    /**
    * Draws all active aliens and their respective health bars on the provided 
    * graphics context. It also renders any active alien attacks.
    *
    * @param g The graphics context used for rendering the aliens and their health.
    */
    public void drawAliens(Graphics g) {
        for (int i = 0; i < aliens.size(); i++) {
            currentAlien = aliens.get(i);
            g.drawImage(currentAlien.appearance, currentAlien.x, currentAlien.y, null);
            g.setColor(Color.GREEN);
            g.fillRect(currentAlien.x + 1, currentAlien.y - 40, currentAlien.health * 15, 20);
        }
        for (int i = 0; i < alienAttacks.size(); i++) {
            currentAlienAttack = alienAttacks.get(i);
            g.drawImage(currentAlienAttack.image, currentAlienAttack.x, currentAlienAttack.y, null);
        }
    }

    /**
    * Draws the game information on the provided graphics context, including 
    * the player's score, mission objectives, and stage results. This method 
    * displays messages when the stage is over, indicating success or failure.
    *
    * @param g The graphics context used for rendering game information.
    */
    public void drawGameInfo(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 25));
        g.drawString("POINTS : " + totalScore, 20, 70);
        g.setFont(new Font("Times New Roman", Font.BOLD, 25));
        g.drawString("MISSION : REACH 10000 POINTS", 880, 70);
        if (isStageOver && isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", Font.BOLD, 50));
            g.drawString("FAIL! THE ALIENS INVADED THE PLANET!", 110, 380);
            g.setColor(Color.GREEN);
            g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            g.drawString("Press R Key To Re-attempt The Game.", 400, 450);
        }
        if (totalScore == 10000) {
            isNextStage = true;
            isStageOver = true;
            backgroundMusic.stop();
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            g.drawString("SUCCESS! YOU HAVE SAVED THE PLANET!", 230, 380);

            g.setColor(Color.GREEN);
            g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            g.drawString("Press ESC Key To Exit The Game.", 450, 450);
        }
    }
}
