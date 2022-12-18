package gui;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Window;
import javafx.util.Duration;
import logic.ModelPg;
import logic.PipeDisplayer;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import logic.FirstTheme;
import logic.SecondTheme;
import logic.ViewModel;

import java.io.*;
import java.net.URL;
import java.util.*;

public class MainWindowsController implements Initializable {
    @FXML
    PipeDisplayer pipeDisplayer;
    @FXML
    Label countStep;
    @FXML
    Label TimerLabel;
    @FXML
    private BorderPane MainWindow;


    ViewModel viewmodel;
    private ListProperty<char[]> pgboard;
    ModelPg modelpg;
    ThemeDisplayer theme;
    private DoubleProperty timeSeconds = new SimpleDoubleProperty(0);
    private StringProperty timeLeft;
    private TimerTask task;
    private Timer timer;
    private double timeleft = 0;

    public MainWindowsController() {
        modelpg = new ModelPg();
        viewmodel = new ViewModel(modelpg);
        this.pgboard = new SimpleListProperty<>();
        this.pgboard.bind(viewmodel.pgboard);
        viewmodel.countStep.addListener((observable, oldValue, newValue) -> countStep.setText(Integer.toString(viewmodel.countStep.get())));
    }

    public void MouseClick() {
        pipeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            pipeDisplayer.requestFocus();
        });
        pipeDisplayer.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                double H = pipeDisplayer.getH();
                System.out.println(H);
                double W = pipeDisplayer.getW();
                System.out.println(W);
                int j = (int) (event.getX() / W);
                int i = (int) (event.getY() / H);
                System.out.println(i + "      " + j);
                viewmodel.switchCell(i, j);
                viewmodel.countStep.set(viewmodel.countStep.get() + 1);
                pipeDisplayer.setpipeboard(pgboard);
                if (viewmodel.isGoal()) {

                    System.out.println("YOU WON!!!");
                    stopTimer();
                    wonMessage();
                    resetTimer();
                    viewmodel.countStep.set(0);
                    pipeDisplayer.setDisable(true);
                }
            }
        });

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEnd();
       setStart();


        countStep.setText("0");
        pipeDisplayer.setDisable(true);
        pipeDisplayer.setPipeData(pgboard, new FirstTheme());
        MouseClick();



    }

    public void start() {
        pipeDisplayer.setDisable(false);
        System.out.println("start the game!\n");
        startTimer();

    }

    public void stopTheGame() throws IOException {
        stopTimer();
        //theme.stopMusic();
        System.out.println("stop the game!\n");

    }

    public void solve() {
        List<String> sol = this.viewmodel.solve();

        Thread th = new Thread(() -> {

            try {
                System.out.println("Solution:");
                for (int k = 0; k < sol.size(); k++) {
                    String line = sol.get(k);
                    int i = Integer.parseInt(line.split(",")[0]);
                    int j = Integer.parseInt(line.split(",")[1]);
                    int times = Integer.parseInt(line.split(",")[2]);
                    System.out.println(line);
                    for (int w = 0; w < times; w++) {
                        viewmodel.switchCell(i, j);
                    }
                    pipeDisplayer.setpipeboard(pgboard);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        th.start();
    }

    public void openFile() throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("open saved level");
        fc.setInitialDirectory(new File("./resources/levels/"));
        File chosen = fc.showOpenDialog(null);
        if (chosen != null) {

            System.out.println(chosen.getName());
            viewmodel.load(chosen);
            pipeDisplayer.redraw();
        }
    }

    public void startTimer() {
        TimerLabel.textProperty().bind(timeSeconds.asString());
        timer = new Timer(true);
        task = new TimerTask() {
            @Override
            public void run() {
                timeleft += 0.1;
                Platform.runLater(new Runnable() {
                    public void run() {
                        timeSeconds.set(Double.parseDouble(String.format("%,.2f", timeleft)));
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(task, 0, 100);

    }


    public void resetTimer() {
        timeleft = 0;
    }

    public void stopTimer() {
        timer.cancel();
    }

    public Double saveTimer() {
        return timeleft;
    }

    public void setFirstTheme() {
        ThemeDisplayer firstTheme = new FirstTheme();
        pipeDisplayer.setPipeTheme(firstTheme);
    }

    public void setSecondTheme() {
        ThemeDisplayer secondTheme = new SecondTheme();
        pipeDisplayer.setPipeTheme(secondTheme);
    }

    public void save() throws IOException {
        if (viewmodel.save()) {
            saveMessage();
        } else
            wonMessage();
    }



    //alert for the player, won,lose,save game
    public void wonMessage() {


        setAnimatedRectangle();
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Well Done");
//        alert.setHeaderText(null);
//        alert.setContentText("You Won! :)");
//        alert.showAndWait();
    }

    public void LossMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sorry you loss");
        alert.setHeaderText(null);
        alert.setContentText("You loss :( ");
        alert.showAndWait();
    }

    public void saveMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Saved");
        alert.setHeaderText(null);
        alert.setContentText("Game saved");
        alert.showAndWait();
    }

    public void errorMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("OOPS Something is wrong");
        alert.setHeaderText(null);
        alert.setContentText("Game error");
        alert.showAndWait();
    }

    //a dialog function with the player
    //configuration Window show the port and the ip
    public void configurationWindow() throws FileNotFoundException {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        ButtonType saveButtonType = new ButtonType("save", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        TextField port = new TextField();
        port.setPromptText(String.valueOf(modelpg.port));
        TextField ip = new TextField();
        ip.setPromptText(modelpg.host);
        grid.add(new Label("ip:"), 0, 1);
        grid.add(ip, 1, 1);
        grid.add(new Label("Port number:"), 0, 0);
        grid.add(port, 1, 0);
        Node loginButton = dialog.getDialogPane().lookupButton(saveButtonType);
        loginButton.setDisable(true);
        port.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> port.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Pair<>(port.getText(), ip.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        if (result.isPresent()) {
            String resultPort = result.get().getKey();
            String resultIp = result.get().getValue();
            modelpg.setPort(Integer.parseInt(resultPort));
            modelpg.setHost(resultIp);
        }

    }

    public void setAnimatedRectangle(){

        System.out.println(MainWindow.getHeight()+ " Window Size " +MainWindow.getHeight() );
       Double y=MainWindow.getHeight();
       Double x=MainWindow.getWidth();
        AnimateWater water=new AnimateWater();
        MainWindow.getChildren().add(water.getRectangle(y,x));
    }

    public void setStart()  {

        FirstTheme theme1=new FirstTheme();

        InputStream stream = null;
        try {
            stream = new FileInputStream(theme1.getStartConect());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        //Setting the image view parameters
        imageView.setX(80);
        imageView.setY(70);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(true);

        MainWindow.getChildren().add(imageView);
    }
    public void setEnd()  {

        FirstTheme theme1=new FirstTheme();

        InputStream stream = null;
        try {
            stream = new FileInputStream(theme1.getStartConect());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: brown");
        Image image = new Image(stream);

        Glow glow = new Glow();

        //setting level of the glow effect
        glow.setLevel(0.9);

        pane.setEffect(glow);

        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        //Setting the image view parameters
        imageView.setX(800);
        imageView.setY(430);
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);
         MainWindow.setPrefWidth(900);

        MainWindow.setStyle("-fx-background-color:#1f0d0d");
        MainWindow.getChildren().add(imageView);
    }

}
