package gui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimateWater implements Initializable {
   @FXML
   private Rectangle rectangle;
    @FXML
    private AnchorPane anchor;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        rectangle = new Rectangle(100, 40, 100, 100);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setFill(Color.VIOLET);

        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), rectangle);
        tt.setByX(200f);
        tt.setCycleCount(32);
        tt.setAutoReverse(true);
        tt.play();



    }

    public Rectangle getRectangle(){
   return rectangle;
    }


}
