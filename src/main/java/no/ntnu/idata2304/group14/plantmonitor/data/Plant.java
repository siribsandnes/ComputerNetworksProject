package no.ntnu.idata2304.group14.plantmonitor.data;

public class Plant {
    String name;
    String type;
    int sensorID;
    double currentMoistureLevel;
    double desiredMoistureLevel;

    /**
     * Creates an instance of Plant.
     *
     * @param name the name of the plant
     * @param type the plant type
     * @param sensorID the id of te sensor
     * @param currentMoistureLevel current moisturelevel
     * @param desiredMoistureLevel desired moisturelevel
     */
    public Plant(String name, String type, int sensorID, double currentMoistureLevel, double desiredMoistureLevel) {
        this.name = name;
        this.type = type;
        this.sensorID = sensorID;
        this.currentMoistureLevel = currentMoistureLevel;
        this.desiredMoistureLevel = desiredMoistureLevel;
    }

    /**
     * Creates an instance of Plant
     *
     * @param name the name of the plant
     * @param type the plant type
     * @param sensorID the id of te sensor
     * @param desiredMoistureLevel desired moisturelevel
     */
    public Plant(String name, String type, int sensorID, double desiredMoistureLevel) {
        this.name = name;
        this.type = type;
        this.sensorID = sensorID;
        this.desiredMoistureLevel = desiredMoistureLevel;
    }

    /**
     * Gives the name of the plant
     *
     * @return the name of the plant
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the plant
     *
     * @param name
     */
    private void setName(String name) {
        this.name = name;
    }


    /**
     * Gives the plant type
     * @return the plant type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the plant type
     *
     * @param type
     */
    private void setType(String type) {
        this.type = type;
    }

    /**
     * Gives the sensor ID
     *
     * @return the ID of the sensor connected to the plant
     */
    public int getSensorID() {
        return sensorID;
    }

    /**
     * Sets the sensor ID
     *
     * @param sensorID
     */
    private void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    /**
     * Gives the current moisture level of the plant
     *
     * @return current moisture level
     */
    public double getCurrentMoistureLevel() {
        return currentMoistureLevel;
    }

    /**
     * Sets the current moisture level
     * @param currentMoistureLevel
     */
    public void setCurrentMoistureLevel(double currentMoistureLevel) {
        this.currentMoistureLevel = currentMoistureLevel;
    }

    /**
     * Gives the desired moisture level
     *
     * @return The desiren moisture level
     */
    public double getDesiredMoistureLevel() {
        return desiredMoistureLevel;
    }

    /**
     * Sets the desired moisture level
     * @param desiredMoistureLevel
     */
    private void setDesiredMoistureLevel(double desiredMoistureLevel) {
        this.desiredMoistureLevel = desiredMoistureLevel;
    }
}
