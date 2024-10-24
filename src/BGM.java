public class BGM {
    public Clip clip;
    public File audioFile;
    public AudioInputStream audioInputStream;
    public boolean isLoop;
}

public BGM(String pathName, boolean isLoop) {
    try {
        clip = AudioSystem.getClip();  // Initialize clip
        audioFile = new File(pathName);  // Load the audio file
        audioInputStream = AudioSystem.getAudioInputStream(audioFile);  // Get audio stream
        clip.open(audioInputStream);  // Open the clip with the audio stream
    } catch (LineUnavailableException e) {
        e.printStackTrace();  // Handle exception for unavailable audio line
    } catch (IOException e) {
        e.printStackTrace();  // Handle exception for I/O error
    } catch (UnsupportedAudioFileException e) {
        e.printStackTrace();  // Handle exception for unsupported audio file type
    }
}

