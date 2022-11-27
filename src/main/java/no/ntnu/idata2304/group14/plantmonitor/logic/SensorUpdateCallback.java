package no.ntnu.idata2304.group14.plantmonitor.logic;

public interface SensorUpdateCallback {
    public void updateMessageRecieved(int sensorID, int newValue);
}

