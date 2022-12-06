package no.ntnu.idata2304.group14.plantmonitor.logic;

import no.ntnu.idata2304.group14.plantmonitor.data.DataRepository;
import no.ntnu.idata2304.group14.plantmonitor.data.Plant;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;

public class SqlDataRepository implements DataRepository {

    String CONNECTION_STRING = "jdbc:mysql://localhost:3306/PlantMonitor";
    String USERNAME = "root";
    String PASSWORD = "Password";

    private Connection connection;

    @Override
    public boolean connect(){
     boolean success = false;

     try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         this.connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
         success = true;
     } catch (ClassNotFoundException e){
         e.printStackTrace();
         System.out.println("Unable to initialize MySQL JDBC driver");
     } catch (SQLException throwables){
         throwables.printStackTrace();
         System.out.println("Could not connect to database");
     }

     return success;
    }

    @Override
    public List<Plant> getAllPlants() {
        List<Plant> plants = new ArrayList<>();
        try{
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT plantName, plantType, sensorId, desiredMoisture FROM Plant  ");
            while(resultSet.next()){
                String plantName = resultSet.getString("plantName");
                String plantType = resultSet.getString("plantType");
                int sensorId = resultSet.getInt("sensorId");
                double desiredMoisture = resultSet.getDouble("desiredMoisture");
                plants.add(new Plant(plantName, plantType, sensorId, desiredMoisture));
            }

        }catch (SQLException e){
            System.out.println("An error occured when processing the SQL getAllPlants statement");
        }

        return plants;
    }

    @Override
    public boolean deletePlant() {
        return false;
    }

    @Override
    public boolean addPlant() {
        return false;
    }
}
