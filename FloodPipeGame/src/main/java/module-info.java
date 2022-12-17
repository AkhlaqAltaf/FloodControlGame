module com.example.floodpipegame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens com.example.floodpipegame to javafx.fxml;
//    exports com.example.floodpipegame;
    exports gui;
    opens gui to javafx.fxml;
    exports logic;
    opens logic to javafx.fxml;
    exports com.example.floodpipegame;
}