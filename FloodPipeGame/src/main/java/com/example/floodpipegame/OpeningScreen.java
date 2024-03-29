package com.example.floodpipegame;

import gui.MainWindowsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logic.FirstTheme;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpeningScreen implements Initializable {
     @FXML
     private MediaView mv;
     @FXML
     private ImageView water,transWater;
     @FXML
     private MediaPlayer mediaPlayer;
     @FXML
     private File file;
     @FXML
     private Media media;
     @FXML

     private Button PlayButton;
     @FXML
     private FXMLLoader loader;
     @FXML
     private AnchorPane openingScreen;
     @FXML
     private TextField player;
     @FXML
     private BorderPane MainWindow;
     @FXML
     private AnchorPane anchorPane;
     @FXML
     private Rectangle rectangle;
     private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {










        MainWindowsController controller=new MainWindowsController();
      FirstTheme obj=new FirstTheme();
        String musicFile = obj.getVideo();
        media = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
         playVideo();



       PlayButton.setOnMouseClicked(e->{





           stopVideo();
         stage=(Stage) PlayButton.getScene().getWindow();

           Parent root;
           try {
              root=loader.load(getClass().getResource("MainWindows.fxml"));
           } catch (IOException ex) {
               throw new RuntimeException(ex);
           }





//           openingScreen.getChildren().setAll(root);

           Scene scene =new Scene(root);
           stage.setScene(scene);

           stage.show();


       });


    }
public void playVideo(){
         mediaPlayer.play();
    mv.setMediaPlayer(mediaPlayer);

}
public void stopVideo(){
mediaPlayer.stop();

}
public String getPlayer(){
    return player.getText();
}


public void setAnimation(){



    Parent root;
    try {
        root=loader.load(getClass().getResource("WaterAnimation.fxml"));
    } catch (IOException ex) {
        throw new RuntimeException(ex);
    }

}


}
