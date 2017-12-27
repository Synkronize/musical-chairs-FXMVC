package sample;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Created by Jude on 12/24/2017.
 */
public class Window {
    Stage primaryStage;
    BorderPane primaryBp = new BorderPane();
    Scene primaryScene = new Scene(primaryBp,260,200);
    Button play = new Button();
    MenuBar menuBar = new MenuBar();
    Menu menuFile = new Menu("File");
    Menu menuSettings = new Menu("Settings");
    MenuItem chooseDirectory = new MenuItem("Select Music Folder");
    MenuItem setBounds = new MenuItem("Set Bounds");
    DirectoryChooser directoryChooser = new DirectoryChooser();
    ListView<String> playListView;

    Window(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    Scene getScene(){
         return  primaryScene;
    }
    DirectoryChooser getDirectoryChooser(){
        return directoryChooser;
    }
    Stage getPrimaryStage(){
        return primaryStage;
    }
    void makeWindowElements(ObservableList model){
        this.primaryStage = primaryStage;
        new Image(getClass().getResourceAsStream("Play24.gif"));
        play.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Play24.gif"))));
        menuFile.getItems().add(chooseDirectory);
        menuSettings.getItems().add(setBounds);
        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuSettings);
        primaryBp.setBottom(play);
        primaryBp.setTop(menuBar);
        playListView = new ListView<>(model);
        primaryBp.setLeft(playListView);

    }
    String getSelectedSong(){
        return playListView.getSelectionModel().getSelectedItem();
    }
    ListView<String> getPlayListView(){
        return playListView;
    }









}
