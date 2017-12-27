package MusicalChairs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Created by Jude on 12/24/2017.
 */
 class PlayList {
    ObservableList<String> playList;
    File directory;
    List<String> list;
    Preferences preferences = Preferences.userRoot().node(getClass().getName());
    String ID = "Preferred Directory";

    PlayList(){
        preferences.get(ID,"");
    }
    private void getSongs(File directory){
        list = new LinkedList<String>(Arrays.asList(directory.list(
                new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".mp3");
                    }
                }
        )));
    }
    ObservableList<String> getPlayList(){
        getSongs(directory);
        if(playList != null && !playList.isEmpty()){
            System.out.println("We in conditional");
            System.out.println(playList);
            clear();
            int i;
            for(i = 0; i < list.size(); i++) {
                System.out.println("Adding in" + list.get(i));
                addItems(list.get(i), i);
            }
            return playList;
        }
        else{
            playList = FXCollections.observableList(list);
            return playList;
        }

    }
    private void addItems(String song,int index){
        System.out.println(playList);
        playList.add(index,song);
    }
    private void clear(){
       int i;
       int elementsToRemove = playList.size();
       for(i = 0; i < elementsToRemove; i++) {
           playList.remove(0);//Our lists size decreases so lets just delete the front node every time, number of elements to remove times.
       }
    }



}
