package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Jude on 12/27/2017.
 */
public class SettingsWindow {
    Stage stage;
    Scene scene;
    Button Ok;
    Button cancel;
    TextArea lower;
    TextArea upper;
    Text lowerLabel;
    Text upperLabel;
    Text dialogError;
    ButtonType ok = new ButtonType("Ok",ButtonBar.ButtonData.OK_DONE);
   // Button dialogOk = new Button("ok");
    Dialog dialog;
    //DialogPane dialogPane;


    SettingsWindow(){
        stage = new Stage();
        stage.setTitle("Settings");
        HBox organized = new HBox();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(1,1,1,1));
        gridPane.setAlignment(Pos.CENTER);
        scene = new Scene(gridPane,210,210);
        Ok = new Button("Ok");
        cancel= new Button("Cancel");
        lowerLabel = new Text("Lower");
        upperLabel = new Text("Upper");
        lower = new TextArea();
        upper = new TextArea();
        lower.setPrefColumnCount(2);
        upper.setPrefColumnCount(2);
        organized.getChildren().add(Ok);
        organized.getChildren().add(cancel);
        Text instructions = new Text("The song will play for a \nrandom amount of seconds \nbetween Lower and Upper \ninclusive");
        gridPane.add(instructions,1,1);
        gridPane.add(lowerLabel,1,2);
        gridPane.add(lower,2,2);
        gridPane.add(upperLabel,1,3);
        gridPane.add(upper,2,3);
        gridPane.add(organized,1,4);
        stage.setScene(scene);
    }
    void display(){
        stage.show();
    }
    Stage getStage(){
        return stage;
    }
    void constructDialog(){
        dialogError = new Text("Lower or Upper cannot be less than or equal to zero.\n Lower and Upper were not changed.");
        dialog = new Dialog();
        dialog.setDialogPane(new DialogPane());
        dialog.getDialogPane().getButtonTypes().add(ok);
        dialog.getDialogPane().setContent(dialogError);
        dialog.setTitle("Error");
    }
    Dialog  getDialog(){
        return dialog;
    }


}
