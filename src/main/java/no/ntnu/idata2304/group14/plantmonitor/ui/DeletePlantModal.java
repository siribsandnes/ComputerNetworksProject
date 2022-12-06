package no.ntnu.idata2304.group14.plantmonitor.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.idata2304.group14.plantmonitor.data.Plant;

import java.util.HashMap;
import java.util.Map;

public class DeletePlantModal {
    private HashMap<Plant, VBox> plants;

    private Stage window;
    private Label plantLabel = new Label("Select plant to delete:");

    private ChoiceBox plantChoiceBox = new ChoiceBox();

    private Button okButton = new Button("OK");
    private Button cancelButton = new Button("Cancel");
    private VBox layout;

    public DeletePlantModal (HashMap<Plant, VBox> plants) {
        this.plants = plants;
    }

    public Plant deletePlant() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add plant");
        okButton.setOnAction(e -> window.close());
        cancelButton.setOnAction(e -> {
            window.close();
        });

        layout = new VBox(10);
        layout.setPrefWidth(Double.MAX_VALUE);

        HBox titleBox = new HBox(5);
        Label titleLabel = new Label("Delete plant");
        titleLabel.setFont(new Font(20));
        titleBox.getChildren().add(titleLabel);
        titleBox.setAlignment(Pos.CENTER);



        for(Map.Entry<Plant,VBox> plantVBoxEntry : this.plants.entrySet()){
            plantChoiceBox.getItems().add(plantVBoxEntry.getKey().getName());
        }

        HBox deleteBox = new HBox(5);
        deleteBox.getChildren().addAll(plantLabel, plantChoiceBox);
        deleteBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(5);
        buttonBox.setPrefWidth(Double.MAX_VALUE);
        buttonBox.getChildren().addAll(okButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));


        layout.getChildren().addAll(titleBox, deleteBox, buttonBox);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.showAndWait();

        String selectedPlant = plantChoiceBox.getSelectionModel().getSelectedItem().toString();
        Plant plantToDelete = null;


        for(Map.Entry<Plant,VBox> plantVBoxEntry : this.plants.entrySet()){
            if(plantVBoxEntry.getKey().getName().equals(selectedPlant)){
                plantToDelete = plantVBoxEntry.getKey();
            }
        }

        System.out.println("Plant to delete: " + plantToDelete);

        return plantToDelete;
    }
}
