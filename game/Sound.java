package game;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound{
    //shuffle
    public void shuffleSound(){
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File("single_card_deal.wav").getAbsoluteFile()));
            sound.start();
        } catch (Exception exc) {//throws exception if didn't find the file
            System.out.println("Error playing sound: " + exc.getMessage());
            System.exit(0); // Stop program execution
        }
    }
    //win 
    public void winSound(){
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File("winSound.wav").getAbsoluteFile()));
            sound.start();
        } catch (Exception exc) {
            System.out.println("Error playing sound: " + exc.getMessage());
            System.exit(0); // Stop program execution
        }
    }
    //drum roll
    public void drumRollSound(){
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File("drum_roll.wav").getAbsoluteFile()));
            sound.start();
        } catch (Exception exc) {
            System.out.println("Error playing sound: " + exc.getMessage());
            System.exit(0); // Stop program execution
        }
    }
}
