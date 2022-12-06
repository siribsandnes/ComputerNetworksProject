package no.ntnu.idata2304.group14.plantmonitor.ui;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import no.ntnu.idata2304.group14.plantmonitor.data.DataRepository;
import no.ntnu.idata2304.group14.plantmonitor.data.Plant;
import no.ntnu.idata2304.group14.plantmonitor.logic.SensorDataReciever;
import no.ntnu.idata2304.group14.plantmonitor.logic.SqlDataRepository;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TODO: This class
public class MainSceneController {
    
    private HashMap<Plant, VBox> plants;
    private String connectURL ="tcp://129.241.152.12:1883";
    private String baseTopic = "inashouse/house/livingroom/moisture/group14/";

    private SensorDataReciever sensorDataReciever;
    private Map<Integer, Label> sensorMap = new HashMap<>();

    private Map<Integer, Label> feedbackMap = new HashMap<>();

    private DataRepository repository = new SqlDataRepository();



    @FXML
    private Button deletePlantButton;

    @FXML
    private Button addPlantButton;

    @FXML
    private FlowPane flowPane;


    public MainSceneController() {
        this.plants = new HashMap<>();
    }

    @FXML
    private void onAddPlantButton(ActionEvent event){
        System.out.println("Adding plant...");

        AddPlantModal addPlantModal = new AddPlantModal();
        Plant plant = addPlantModal.addPlant();

        if(plant != null){
            VBox plantvBox = createPlantGUIElement(plant);
            this.plants.put(plant, plantvBox);
            flowPane.getChildren().add(plantvBox);
            flowPane.setAlignment(Pos.CENTER);
            flowPane.setHgap(20);
            boolean success = sensorDataReciever.subscribeTopic(baseTopic + plant.getSensorID());
        }else{
            System.out.println("No plant to add");
        }

    }

    @FXML
    private void onDeletePlantButton(ActionEvent event){
        DeletePlantModal deletePlantModal = new DeletePlantModal(this.plants);
        Plant plant = deletePlantModal.deletePlant();
        this.plants.remove(plant);
        updatePlantView();
    }

    @FXML
    void initialize(){
        System.out.println("Init");
        sensorDataReciever = new SensorDataReciever(connectURL);
        if(repository.connect()){
            try{
                sensorDataReciever.connect(((sensorID, newValue) -> {
                    System.out.println("Controller: " + sensorID + ": " + newValue);
                    final Label valueLabel = sensorMap.get(sensorID);
                    Platform.runLater(() -> valueLabel.setText(Double.toString(newValue)));

                    //DISKUTER MED PAPPA HER LEGGES current moisture til I riktig PLANT ELEMENT
                    for(Map.Entry<Plant,VBox> plantVBoxEntry : this.plants.entrySet()){
                        Plant plant = plantVBoxEntry.getKey();
                        if(plant.getSensorID() == sensorID){
                            plant.setCurrentMoistureLevel(newValue);
                            System.out.println("Plant " + plant.getName() + " current moist" + plant.getCurrentMoistureLevel());
                        }

                        final Label feedbackLabel = feedbackMap.get(sensorID);
                        updateMoistureFeedback(feedbackLabel, plant);
                    }

                }));

            } catch(MqttException mqttException){
                mqttException.printStackTrace();
            }
        }else {
            System.err.println("Could not connect to database");
        }
    }

    /**
     * Creates a GUI Elemenet in form of a VBox for a plant.
     *
     * @param plant the plant ypu want to create a vBox for
     * @return vBox to display
     */
    private VBox createPlantGUIElement(Plant plant){
        VBox vBox = new VBox(25);

        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setPrefWidth(250);
        vBox.setPrefHeight(270);
        vBox.setAlignment(Pos.CENTER);

        HBox plantNameBox = new HBox(20);
        Label plantName = new Label(plant.getName());
        plantName.setId("underTitle");
        plantNameBox.getChildren().add(plantName);
        plantNameBox.setAlignment(Pos.CENTER);


        HBox sensorIdBox = new HBox(20);
        sensorIdBox.getChildren().addAll(new Label("Sensor id:"), new Label(Integer.toString(plant.getSensorID())));
        sensorIdBox.setAlignment(Pos.CENTER);

        HBox plantTypeBox = new HBox(20);
        plantTypeBox.getChildren().addAll(new Label("Plant Type:"), new Label(plant.getType().toString()));
        plantTypeBox.setAlignment(Pos.CENTER);

        HBox desiredMoistureLevelBox = new HBox(20);
        desiredMoistureLevelBox.getChildren().addAll(new Label("Desired moisture:"), new Label(Double.toString(plant.getDesiredMoistureLevel())));
        desiredMoistureLevelBox.setAlignment(Pos.CENTER);

        HBox currentMoistureLevelBox = new HBox(20);
        Label sensorValue = new Label( Double.toString(plant.getCurrentMoistureLevel()));
        currentMoistureLevelBox.getChildren().addAll(new Label("Current moisture:"), sensorValue);
        currentMoistureLevelBox.setAlignment(Pos.CENTER);

        HBox moistureFeedback = new HBox();
        Label moistureLabel = new Label();
        moistureFeedback.getChildren().add(moistureLabel);
        moistureFeedback.setAlignment(Pos.CENTER);


        vBox.getChildren().addAll(plantNameBox, sensorIdBox, plantTypeBox, desiredMoistureLevelBox, currentMoistureLevelBox, moistureFeedback);
        feedbackMap.put(plant.getSensorID(), moistureLabel);
        sensorMap.put(plant.getSensorID(), sensorValue);

        return vBox;
    }

    private void updatePlantView(){
        flowPane.getChildren().clear();
        for(Map.Entry<Plant,VBox> plantVBoxEntry : this.plants.entrySet()){
            flowPane.getChildren().add(plantVBoxEntry.getValue());
        }

    }

    private void updateMoistureFeedback(Label feedBackLabel, Plant plant ){
        if (plant.getCurrentMoistureLevel() - plant.getDesiredMoistureLevel() <=5 && plant.getCurrentMoistureLevel() - plant.getDesiredMoistureLevel() >= -5 ) {
            Platform.runLater(() -> {
                feedBackLabel.setText("Perfect amount of water");
                feedBackLabel.setStyle("-fx-text-fill: green");
            });
        }else if(plant.getCurrentMoistureLevel() < plant.getDesiredMoistureLevel()){
            Platform.runLater(() -> {
                feedBackLabel.setText("Too much water");
                feedBackLabel.setStyle("-fx-text-fill: blue");
            });
        }else{
           Platform.runLater(() -> {
               feedBackLabel.setText("Too little water");
               feedBackLabel.setStyle("-fx-text-fill: red");
           });
        }
    }
}
