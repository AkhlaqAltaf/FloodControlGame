package com.example.floodpipegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OpeningScreen.fxml"));

        Scene scene = new Scene(root,840,465);

        Image image=new Image("file:resources/FirstTheme/GameLogo.png");
        primaryStage.getIcons().add(image);
        scene.getStylesheets().add(getClass().getResource("CSS/MainView.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch();
    }
}