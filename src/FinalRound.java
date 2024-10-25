import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class FinalRound extends Thread {
    private int delay = 20;
    private long pretime;
    private int cnt;
    private int score;

    private FirstRound game = new FirstRound();
    private Image earthling = new ImageIcon("src/images/earthling.png").getImage();

    private int earthlingX;
    private int earthlingY;
    private final int earthlingWidth = earthling.getWidth(null);
    private final int earthlingHeight = earthling.getHeight(null);
    private int earthlingHp = 30;
    private final int earthlingSpeed = 5;
    
    private boolean thisStage2;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean shooting;
    private boolean isOver;
    private boolean isComplete;
    private boolean gameOver = false;

    private final ArrayList<EarthlingAttack> earthlingAttackList = new ArrayList<>();
    private final ArrayList<AlienBossAttack> bossAttackList = new ArrayList<>();
    private final ArrayList<Alien> alienList = new ArrayList<>();
    private final ArrayList<AlienAttack> alienAttackList = new ArrayList<>();
    private final ArrayList<EarnHealth> healthList = new ArrayList<>();

    private EarthlingAttack earthlingAttack;
    private AlienBoss boss = new AlienBoss(900, 0);
    private AlienBossAttack bossAttack;
    private Alien alien;
    private AlienAttack alienAttack;
    private EarnHealth health;

    private BGM backgroundMusic;
    private BGM hitSound;
    private BGM itemSound;
    private BGM winSound;

    @Override
    public void run() {
        backgroundMusic = new BGM("src/audio/FinalRoundBGM.wav", true);
        hitSound = new BGM("src/audio/HitSound.wav", false);
        itemSound = new BGM("src/audio/HealthItem.wav", false);
        winSound = new BGM("src/audio/EndGame.wav", false);

        reset();
        while (true) {
            while (!isOver) {
                pretime = System.currentTimeMillis();
                if (System.currentTimeMillis() - pretime < delay) {
                    try {
                        Thread.sleep(delay - System.currentTimeMillis() + pretime);
                        thisStage2 = true;
                        keyProcess();
                        earthlingAttackProcess();
                        bossAttackProcess();
                        alienAppearProcess();
                        alienMoveProcess();
                        alienAttackProcess();
                        healthAppearProcess();
                        healthItemMove();
                        healthProcess();
                        earthlingSelect();
                        cnt++;
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

    private void earthlingSelect() {
        if (EarthlingAttack.earthlingColor == 2) {
            earthling = new ImageIcon("src/images/earthling2.png").getImage();
        } else if (EarthlingAttack.earthlingColor == 3) {
            earthling = new ImageIcon("src/images/earthling3.png").getImage();
        }
    }

    public void reset() {
        isOver = false;
        gameOver = false;
        cnt = 0;
        score = 0;
        earthlingX = 10;
        earthlingY = (Main.ScreenHeight - earthlingHeight) / 2;
        earthlingHp = 30;

        backgroundMusic.start();

        earthlingAttackList.clear();
        bossAttackList.clear();
        alienAttackList.clear();
        alienList.clear();
        healthList.clear();
    }

    private void healthAppearProcess() {
        if (cnt % 95 == 0) {
            health = new EarnHealth(1120, (int) (Math.random() * 621));
            healthList.add(health);
        }
    }

    private void healthItemMove() {
        for (int i = 0; i < healthList.size(); i++) {
            health = healthList.get(i);
            health.move();
        }
    }

    private void keyProcess() {
        if (up && earthlingY - earthlingSpeed > 0) earthlingY -= earthlingSpeed;

        if (down && earthlingY + earthlingHeight 
            + earthlingSpeed < Main.ScreenWidth) earthlingY += earthlingSpeed;

        if (left && earthlingX - earthlingSpeed > 0) earthlingX -= earthlingSpeed;

        if (right && earthlingX + earthlingWidth 
            + earthlingSpeed < Main.ScreenWidth) earthlingX += earthlingSpeed;
        if (shooting && cnt % 15 == 0) {
            earthlingAttack = new EarthlingAttack(earthlingX + 222, earthlingY + 100);
            earthlingAttackList.add(earthlingAttack);
        }
    }

    private void earthlingAttackProcess() {
        for (int i = 0; i < earthlingAttackList.size(); i++) {
            earthlingAttack = earthlingAttackList.get(i);
            earthlingAttack.shoot();

            for (int j = 0; j < alienList.size(); j++) {
                alien = alienList.get(j);
                if (earthlingAttack.x > alien.x && earthlingAttack.x < alien.x + alien.width 
                    && earthlingAttack.y > alien.y && earthlingAttack.y < alien.y + alien.height) {
                    alien.health -= earthlingAttack.attack;
                    earthlingAttackList.remove(earthlingAttack);
                }
                if (alien.health <= 0) {
                    hitSound.start();
                    alienList.remove(alien);
                    score += 1000;
                }
            }

            if (earthlingAttack.x > boss.x && earthlingAttack.x < boss.x + boss.width
                && earthlingAttack.y > boss.y && earthlingAttack.y < boss.y + boss.height) {
                boss.hp -= earthlingAttack.attack;
                earthlingAttackList.remove(earthlingAttack);
            }
            if (boss.hp <= 0) {
                hitSound.start();
                winSound.start();
            }
        }
    }

    private void alienAppearProcess() {
        if (cnt % 50 == 0) {
            alien = new Alien(1120, (int) (Math.random() * 621));
            alienList.add(alien);
        }
    }

    private void alienMoveProcess() {
        for (int i = 0; i < alienList.size(); i++) {
            alien = alienList.get(i);
            alien.move();
        }
    }

    private void alienAttackProcess() {
        if (cnt % 50 == 0) {
            alienAttack = new AlienAttack(alien.x - 79, alien.y + 35);
            alienAttackList.add(alienAttack);
        }

        for (int i = 0; i < alienAttackList.size(); i++) {
            alienAttack = alienAttackList.get(i);
            alienAttack.shoot();

            if (alienAttack.x > earthlingX & alienAttack.x < earthlingX + earthlingWidth 
                && alienAttack.y > earthlingY && alienAttack.y < earthlingY + earthlingHeight) {
                hitSound.start();
                earthlingHp -= alienAttack.attack;
                alienAttackList.remove(alienAttack);
                if (earthlingHp <= 0) {
                    isOver = true;
                    gameOver = true;
                }
            }
        }
    }

    private void healthProcess() {
        for (int i = 0; i < healthList.size(); i++) {
            health = healthList.get(i);

            if (health.x > earthlingX & health.x < earthlingX + earthlingWidth
                && health.y > earthlingY && health.y < earthlingY + earthlingHeight) {
                itemSound.start();
                if (earthlingHp == 30) {
                    earthlingHp += 0;
                } else {
                    earthlingHp += health.hp;
                }

                healthList.remove(health);
            }
        }
    }

    private void bossAttackProcess() {
        if (cnt % 60 == 0) {
            bossAttack = new AlienBossAttack(boss.x - 79, boss.y + 125);
            bossAttackList.add(bossAttack);
            bossAttack = new AlienBossAttack(boss.x - 79, boss.y + 70);
            bossAttackList.add(bossAttack);
        }

        for (int i = 0; i < bossAttackList.size(); i++) {
            bossAttack = bossAttackList.get(i);
            bossAttack.shoot();

            if (bossAttack.x > earthlingX & bossAttack.x < earthlingX + earthlingWidth 
                && bossAttack.y > earthlingY && bossAttack.y < earthlingY + earthlingHeight) {
                hitSound.start();
                earthlingHp -= bossAttack.attack;
                bossAttackList.remove(bossAttack);
                if (earthlingHp <= 0) {
                    isOver = true;
                    gameOver = true;
                }
            }
        }
    }

    public void bossGameDraw(Graphics g) {
        earthlingDraw(g);
        bossDraw(g);
        alienDraw(g);
        healthDraw(g);
        infoDraw(g);
    }

    public void infoDraw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("SCORE : " + score, 40, 80);
        g.drawString("MISSION : Defeat Boss", 750, 80);
        if (isOver() && gameOver()) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 80));

            g.drawString("Press R to restart", 295, 380);
        }
        if (boss.hp == 0) {
            isOver = true;
            isComplete = true;
            backgroundMusic.stop();

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 68));
            g.drawString("YOU WIN!", 450, 360);
            g.drawString("Press M to Return to Main", 175, 420);
        }
    }

    public void earthlingDraw(Graphics g) {
        g.drawImage(earthling, earthlingX, earthlingY, null);
        g.setColor(Color.GREEN);
        g.fillRect(earthlingX - 1, earthlingY - 40, earthlingHp * 6, 20);
        for (int i = 0; i < earthlingAttackList.size(); i++) {
            earthlingAttack = earthlingAttackList.get(i);
            g.drawImage(earthlingAttack.image, earthlingAttack.x, earthlingAttack.y, null);
        }
    }

    public void bossDraw(Graphics g) {
        g.drawImage(boss.getBoss(), boss.x, boss.y = (Main.ScreenHeight - boss.height) / 2, null);
        g.setColor(Color.GREEN);
        g.fillRect(boss.x - 1, boss.y - 40, boss.hp * 15, 20);

        for (int i = 0; i < bossAttackList.size(); i++) {
            bossAttack = bossAttackList.get(i);
            g.drawImage(bossAttack.image, bossAttack.x, bossAttack.y, null);
        }
    }

    public void healthDraw(Graphics g) {
        for (int i = 0; i < healthList.size(); i++) {
            health = healthList.get(i);
            g.drawImage(health.image, health.x, health.y, null);
        }
    }

    public void alienDraw(Graphics g) {
        for (int i = 0; i < alienList.size(); i++) {
            alien = alienList.get(i);
            g.drawImage(alien.image, alien.x, alien.y, null);
            g.setColor(Color.GREEN);
            g.fillRect(alien.x + 1, alien.y - 40, alien.health * 15, 20);
        }
        for (int i = 0; i < alienAttackList.size(); i++) {
            alienAttack = alienAttackList.get(i);
            g.drawImage(alienAttack.image, alienAttack.x, alienAttack.y, null);
        }
    }

    public boolean isOver() {
        return isOver;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean gameOver() {
        return gameOver;
    }

    public boolean getThisStage2() {
        return this.thisStage2;
    }

    public void setUp2(boolean up) {
        this.up = up;
    }

    public void setDown2(boolean down) {
        this.down = down;
    }

    public void setLeft2(boolean left) {
        this.left = left;
    }

    public void setRight2(boolean right) {
        this.right = right;
    }

    public void setShooting2(boolean shooting) {
        this.shooting = shooting;
    }
}
