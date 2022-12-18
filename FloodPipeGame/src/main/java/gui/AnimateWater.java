package gui;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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






    }

    public Rectangle getRectangle(double y,double x){

        rectangle = new Rectangle(200, 95, 100, 50);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);
        rectangle.setFill(Color.color(0.231,0.254,0.255,0.4));

        TranslateTransition tt = new TranslateTransition(Duration.millis(4000), rectangle);
        tt.setByX(400);

       double getx= tt.getFromX();
       System.out.println("Get From Z"+getx);


       if(getx>300){

           tt.setByY(500);
           tt.setByX(0.0);
       }

        tt.play();
        tt.setOnFinished(e->{
            tt.setByX(0.0);
            rectangle.setWidth(60);
            rectangle.setHeight(200);
            tt.setByY(300);
            tt.play();


            tt.setOnFinished(end->{
                rectangle.setHeight(90);
                rectangle.setWidth(0);
                tt.setByX(100);
                tt.setByY(0.0);
                tt.play();

            });

        });
        tt.setAutoReverse(false);




   return rectangle;
    }


    public  Rectangle timelineT(){

        final Rectangle rectPath = new Rectangle (0, 0, 40, 40);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);

        Path path = new Path();
        path.getElements().add(new MoveTo(20,20));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        return rectangle;
    }


}
