package no.ntnu.idata2304.group14.plantmonitor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent mainPaneLoader = FXMLLoader.load(getClass().getResource("ui/gui/MainSceneExtra.fxml"));
        primaryStage.setScene(new Scene(mainPaneLoader,1000,700));

        primaryStage.setTitle("Plant monitor");
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(1000);
        primaryStage.show();

    }
    public static void main(String[] args){launch(args);}
}
