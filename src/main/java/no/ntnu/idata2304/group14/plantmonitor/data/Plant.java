package no.ntnu.idata2304.group14.plantmonitor.data;

public class Plant {
    String name;
    Planttype type;
    int sensorID;
    double currentMoistureLevel;
    double desiredMoistureLevel;

    public Plant(String name, Planttype type, int sensorID, double currentMoistureLevel, double desiredMoistureLevel) {
        this.name = name;
        this.type = type;
        this.sensorID = sensorID;
        this.currentMoistureLevel = currentMoistureLevel;
        this.desiredMoistureLevel = desiredMoistureLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Planttype getType() {
        return type;
    }

    public void setType(Planttype type) {
        this.type = type;
    }

    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    public double getCurrentMoistureLevel() {
        return currentMoistureLevel;
    }

    public void setCurrentMoistureLevel(double currentMoistureLevel) {
        this.currentMoistureLevel = currentMoistureLevel;
    }

    public double getDesiredMoistureLevel() {
        return desiredMoistureLevel;
    }

    public void setDesiredMoistureLevel(double desiredMoistureLevel) {
        this.desiredMoistureLevel = desiredMoistureLevel;
    }
}
