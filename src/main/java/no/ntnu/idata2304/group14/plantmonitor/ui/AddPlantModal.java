package no.ntnu.idata2304.group14.plantmonitor.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.idata2304.group14.plantmonitor.data.Plant;

public class AddPlantModal {
    private Stage window;
    private Label nameLabel = new Label("Plant name:");
    private Label idLabel = new Label("Sensor id:");
    private Label typeLabel = new Label("Plant type:");

    private Label desiredMoistureLabel = new Label("Desired Moisture:");

    private TextField nameField = new TextField("");
    private TextField idField = new TextField("");
    private TextField typeField = new TextField("");

    private TextField desiredMoistureField = new TextField("");

    private Button okButton = new Button("OK");
    private Button cancelButton = new Button("Cancel");
    private VBox layout;

    public Plant addPlant() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add plant");
        okButton.setOnAction(e -> window.close());
        cancelButton.setOnAction(e -> {
            idField.setText(null);
            window.close();
        });
        layout = new VBox(10);
        layout.setPrefWidth(Double.MAX_VALUE);

        HBox nameBox = new HBox(5);
        nameBox.getChildren().addAll(nameLabel, nameField);
        nameBox.setAlignment(Pos.CENTER);

        HBox idBox = new HBox(5);
        idBox.getChildren().addAll(idLabel, idField);
        idBox.setAlignment(Pos.CENTER);

        HBox typeBox = new HBox(5);
        typeBox.getChildren().addAll(typeLabel, typeField);
        typeBox.setAlignment(Pos.CENTER);

        HBox desiredMoistureBox = new HBox(5);
        desiredMoistureBox.getChildren().addAll(desiredMoistureLabel, desiredMoistureField);
        desiredMoistureBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(5);
        buttonBox.setPrefWidth(Double.MAX_VALUE);
        buttonBox.getChildren().addAll(okButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(nameBox, idBox, typeBox, desiredMoistureBox, buttonBox);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.showAndWait();
        if (idField.getText() == null) {
            System.out.println("Avbryter...");
            return null;
        }
        try {
            String idTekst = idField.getText();
            int id = Integer.parseInt(idTekst);
            String dblTekst = desiredMoistureField.getText();
            double dbl = Double.parseDouble(dblTekst);
            return new Plant(nameField.getText(), typeField.getText(), id, dbl);
        } catch (NumberFormatException numEx) {
            System.out.println("Kunne ikke lage sensor, feil id");
            return null;
        }
    }
}

