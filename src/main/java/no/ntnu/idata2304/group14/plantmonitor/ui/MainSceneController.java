package no.ntnu.idata2304.group14.plantmonitor.ui;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import no.ntnu.idata2304.group14.plantmonitor.data.Plant;
import no.ntnu.idata2304.group14.plantmonitor.logic.SensorDataReciever;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.HashMap;
import java.util.Map;

//TODO: This class
public class MainSceneController {
    private String connectURL ="tcp://10.0.0.3:1883";
    private String baseTopic = "topic/sensor-";

    private SensorDataReciever sensorDataReciever;
    private Map<Integer, Label> sensorMap = new HashMap<>();

    @FXML
    private Button deletePlantButton;

    @FXML
    private Button addPlantButton;

    @FXML
    private FlowPane flowPane;

    @FXML
    private void onAddPlantButton(ActionEvent event){
        System.out.println("Adding plant...");

        AddPlantModal addPlantModal = new AddPlantModal();
        Plant plant = addPlantModal.addPlant();

        if(plant != null){
            VBox plantvBox = createPlantGUIElement(plant);
            flowPane.getChildren().add(plantvBox);
            boolean success = sensorDataReciever.subscribeTopic(baseTopic + plant.getSensorID());
        }else{
            System.out.println("No plant to add");
        }

    }

    @FXML
    private void onDeletePlantButton(ActionEvent event){

    }

    @FXML
    void initialize(){
        System.out.println("Init");
        sensorDataReciever = new SensorDataReciever(connectURL);
        try{
           sensorDataReciever.connect(((sensorID, newValue) -> {
               System.out.println("Controller: " + sensorID + ": " + newValue);
               final Label valueLabel = sensorMap.get(sensorID);
               Platform.runLater(() -> valueLabel.setText(Integer.toString(newValue)));
           }));

        } catch(MqttException mqttException){
            mqttException.printStackTrace();
        }
    }

    /**
     * Creates a GUI Elemenet in form of a VBox for a plant.
     *
     * @param plant the plant ypu want to create a vBox for
     * @return vBox to display
     */
    private VBox createPlantGUIElement(Plant plant){
        VBox vBox = new VBox(80);

        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 250), CornerRadii.EMPTY, Insets.EMPTY)));

        HBox plantNameBox = new HBox(20);
        plantNameBox.getChildren().addAll(new Label("Plant Name:"), new Label(plant.getName()));

        HBox sensorIdBox = new HBox(20);
        sensorIdBox.getChildren().addAll(new Label("Sensor id:"), new Label(Integer.toString(plant.getSensorID())));

        HBox plantTypeBox = new HBox(20);
        plantTypeBox.getChildren().addAll(new Label("Plant Type:"), new Label(plant.getType().toString()));


        HBox desiredMoistureLevelBox = new HBox(20);
        desiredMoistureLevelBox.getChildren().addAll(new Label("Desired mosisture:"), new Label(Double.toString(plant.getDesiredMoistureLevel())));

        HBox currentMoistureLevelBox = new HBox(20);
        Label sensorValue = new Label( Double.toString(plant.getCurrentMoistureLevel()));
        currentMoistureLevelBox.getChildren().addAll(new Label("Current mosture:"), sensorValue);

        vBox.getChildren().addAll(plantNameBox, sensorIdBox, plantTypeBox, desiredMoistureLevelBox, currentMoistureLevelBox);
        sensorMap.put(plant.getSensorID(), sensorValue);

        return vBox;
    }
}
