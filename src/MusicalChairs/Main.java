package MusicalChairs;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
     //   Parent root = FXMLLoader.load(getClass().getResource("MusicalChairs.fxml"));
        primaryStage.setTitle("Musical Chairs");
        Window window = new Window(primaryStage);
        Controller controller = new Controller(window,new PlayList(),new MusicGamePlayer(),new SettingsWindow());



    }


    public static void main(String[] args) {
        launch(args);
    }
}
