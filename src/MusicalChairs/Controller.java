package MusicalChairs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


import java.io.File;

public class Controller {
    private Window window;
    private PlayList playList;
    private MusicGamePlayer musicGamePlayer;
    private SettingsWindow settingsWindow;
    Controller(Window window,PlayList playList,MusicGamePlayer musicGamePlayer,SettingsWindow settingsWindow){
        this.window = window;
        this.playList = playList;
        this.musicGamePlayer = musicGamePlayer;
        this.settingsWindow = settingsWindow;
        this.settingsWindow.constructDialog();
        this.window.menuFile.setOnAction(new FileButtonHandler());
        window.play.setOnMouseClicked(new playHandler());
        window.setBounds.setOnAction(new settingsButtonHandler());
        setUpOnStartUp();
    }
    class FileButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            setDirectory();
        }
    }
    class listDoubleClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            if(event.getClickCount() == 2){
              musicGamePlayer.playMedia(musicGamePlayer.createMediaPlayer(musicGamePlayer.getMedia(playList.directory, window.getSelectedSong())));

            }
        }
    }
    class playHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            musicGamePlayer.playMedia(musicGamePlayer.getMediaPlayer());
        }
    }
    class okHandler implements  EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            int temp1 = Integer.parseInt(settingsWindow.lower.getText());
            int temp2 = Integer.parseInt(settingsWindow.upper.getText());
            if(temp1 <= 0 || temp2 <= 0)
                settingsWindow.getDialog().show();

            else {
                musicGamePlayer.lowerVal = temp1;
                musicGamePlayer.upperVal = temp2;
                musicGamePlayer.lowerValPreferences.putInt(musicGamePlayer.lowerValue, musicGamePlayer.lowerVal);
                musicGamePlayer.upperValPreferences.putInt(musicGamePlayer.upperValue, musicGamePlayer.upperVal);
                settingsWindow.getStage().close();
            }

        }
    }
    class cancelHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            settingsWindow.getStage().close();
        }
    }
    class settingsButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            displaySettingsWindow();
        }
    }

    void setDirectory(){
            playList.directory = window.getDirectoryChooser().showDialog(window.primaryStage);
            playList.preferences.put(playList.ID, playList.directory.toString());
            playList.getPlayList();
    }

    void displayView(){
        window.makeWindowElements(playList.getPlayList());
        window.getPlayListView().setOnMouseClicked(new listDoubleClickHandler());
        window.primaryStage.setScene(window.getScene());
        window.primaryStage.show();
    }
    void setUpOnStartUp(){
        if(playList.preferences.get(playList.ID,"").equals(""))
        {
            playList.directory = window.getDirectoryChooser().showDialog(window.primaryStage);
            playList.preferences.put(playList.ID,playList.directory.toString());
            displayView();

        }
        else
        {
            playList.directory = new File(playList.preferences.get(playList.ID,""));
            displayView();
        }
        if((musicGamePlayer.lowerValPreferences.getInt(musicGamePlayer.lowerValue, musicGamePlayer.lowerVal) == 0) &&
                (musicGamePlayer.upperValPreferences.getInt(musicGamePlayer.upperValue, musicGamePlayer.upperVal) == 0))
        {
            displaySettingsWindow();
        }

    }
    void displaySettingsWindow(){
        settingsWindow.Ok.setOnMouseClicked(new okHandler());
        settingsWindow.cancel.setOnMouseClicked(new cancelHandler());
        settingsWindow.lower.clear();
        settingsWindow.upper.clear();
        settingsWindow.lower.insertText(0,Integer.toString(musicGamePlayer.lowerVal));
        settingsWindow.upper.insertText(0,Integer.toString(musicGamePlayer.upperVal));
        settingsWindow.display();
    }

}
