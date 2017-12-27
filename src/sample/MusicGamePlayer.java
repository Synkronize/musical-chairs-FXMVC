package sample;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;
import java.util.prefs.Preferences;

/**
 * Created by Jude on 12/26/2017.
 */
public class MusicGamePlayer{
   private Media song;
   private MediaPlayer mediaPlayer;
   private boolean playing = false;
   private long randomNum;
   int lowerVal = 0;
   int upperVal = 0;
    Preferences lowerValPreferences = Preferences.userRoot().node(getClass().getName());
    Preferences upperValPreferences = Preferences.userRoot().node(getClass().getName());
    String lowerValue = "Lower Value";
    String upperValue = "Upper Value";
   private Thread game;
   MusicGamePlayer(){
      lowerVal = lowerValPreferences.getInt(lowerValue,lowerVal);
      upperVal = upperValPreferences.getInt(upperValue,upperVal);
   }
    Media getMedia(File directory, String songName){
         song = new Media((new File(directory,songName).toURI().toString()));
        return song;
    }
    MediaPlayer createMediaPlayer(Media song){
        if(mediaPlayer != null && playing)
            handleMusicAlreadyPlaying();
        mediaPlayer = new MediaPlayer(song);
        setOnStatus();
        return  mediaPlayer;
    }
    void playMedia(MediaPlayer mediaPlayer){
        interruptWaitingThread();
        mediaPlayer.play();
        mediaPlayer.getOnPlaying();
        game = new Thread(new Game());
        game.start();
    }
    private void pauseMedia(){

        mediaPlayer.pause();
        mediaPlayer.getOnPaused();
    }
    private void setOnStatus(){
        mediaPlayer.setOnPlaying(new Runnable() {
            @Override
            public void run() {
                playing = true;
            }
        });
        mediaPlayer.setOnPaused(new Runnable() {
            @Override
            public void run() {
                playing = false;
            }
        });
    }
    private void handleMusicAlreadyPlaying(){
        mediaPlayer.stop();
    }
    class Game implements Runnable{
        @Override
        public void run() {
            gameStart();
        }
    }
    void gameStart(){
        synchronized (game) {
            try {
                generateRandomNum();
               // System.out.println(randomNum);
                if(randomNum >= 60)
                    randomNum = 30;
                game.wait(randomNum * 1000);
                if (mediaPlayer != null) {
                    pauseMedia();
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }



        }
    }
    void generateRandomNum(){
        randomNum = ThreadLocalRandom.current().nextInt(lowerVal,upperVal+1);
    }
    void interruptWaitingThread(){
        if(game != null && game.getState().equals(Thread.State.TIMED_WAITING))
            game.interrupt();

    }
    MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }
}
