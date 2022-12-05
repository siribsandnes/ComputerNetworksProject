package no.ntnu.idata2304.group14.plantmonitor.logic;

import org.eclipse.paho.client.mqttv3.*;

import java.util.UUID;

public class SensorDataReciever implements MqttCallback {
    private String connectionURL;

    private MqttAsyncClient mqttAsyncClient;
    private SensorUpdateCallback callback;

    private boolean connected = false;

    /**
     * Creates a new SensorDataReciever.
     *
     * @param conectionURL URL to the MQTT broker to connect to
     */
    public SensorDataReciever(String conectionURL){
        this.connectionURL = conectionURL;
    }

    /**
     * Connects the client/reciever to the desired MQTT broker.
     */
    public void connect(SensorUpdateCallback callback) throws MqttException{
        this.callback = callback;
        String clientID = UUID.randomUUID().toString();

        mqttAsyncClient = new MqttAsyncClient(this.connectionURL, clientID);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttAsyncClient.setCallback(this);
        mqttAsyncClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                System.out.println("Connected");
                connected = true;
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable exception) {
                exception.printStackTrace();
                connected = false;
            }
        });

    }

    /**
     * Subscribes to a given topic.
     *
     * @param topic the topic you want your client to subscribe to.
     * @return true if subscribed to new topic, false if not
     */
    public boolean subscribeTopic(String topic){
        try{
            mqttAsyncClient.subscribe(topic, 1);
            return true;
        }catch (MqttException e){
            return false;
        }
    }

    /**
     * Checks if the Data reciever is conencted to a MQTT broker.
     * @return True if connected, false if not.
     */
    public boolean isConnected(){
        return connected;
    }

    @Override
    public void connectionLost(Throwable throwable){
        System.out.println("Connection lost");
        throwable.printStackTrace();
    }


    //TODO oppdatere felter med sensordata
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception{
        System.out.println("Message recieved from topic: " + s + "Message: " + mqttMessage.toString());
        //String[] sensoridStreng = s.split("/");
        //int sensorId = Integer.parseInt(sensoridStreng[sensoridStreng.length]); /// HVIS FEIL ER DET KANSKJE HER

        int sensorId = Integer.parseInt(s.substring(s.length() - 1));
        String[] splitStr = mqttMessage.toString().split(":");
        double value = Double.parseDouble(splitStr[1].trim());
        callback.updateMessageRecieved(sensorId, value);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken){
        System.out.println("Delivery complete");
    }




}
