import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * Represents background music (BGM) for the game. This class manages
 * loading, starting, and stopping audio clips.
 *
 * @Author: Aldair Pedro
 * @ID: 1589296
 *
 * @Author: Gihak Kim
 * @ID: 2083132
 */
public class BGM {
    public Clip clip;
    public File audioFile;
    public AudioInputStream audioInputStream;
    public boolean isLoop;

    /**
     * Constructs a BGM object with the specified audio file path and looping behavior.
     *
     * @param pathName the path to the audio file to be played
     * @param isLoop a boolean indicating whether the audio should loop continuously
     */
    public BGM(String pathName, boolean isLoop) {
        try {
            clip = AudioSystem.getClip();
            audioFile = new File(pathName);
            audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Starts playing the background music from the beginning.
     * If the isLoop flag is set to true, the music will loop continuously.
     */
    public void start() {
        clip.setFramePosition(0);
        clip.start();
        if (isLoop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        clip.stop();
    }
}
