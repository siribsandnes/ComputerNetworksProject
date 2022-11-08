package no.ntnu.idata2304.group14.plantmonitor.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainWindow extends Application{

    private Stage primaryStage;

    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        FXMLLoader mainPaneLoader = new FXMLLoader(getClass().getResource("gui/MainScene.fxml"));
        Parent mainPane = mainPaneLoader.load();
        this.mainScene = new Scene(mainPane, 1000, 700);

        MainSceneController mainSceneController = mainPaneLoader.getController();

        primaryStage.setTitle("Plant monitor");
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(1000);
        primaryStage.setScene(this.mainScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
