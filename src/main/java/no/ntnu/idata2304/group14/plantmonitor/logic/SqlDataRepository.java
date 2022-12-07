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

    /**
     * Connects to the database
     *
     * @return true if connection was successfull, false if not
     */
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

    /**
     * Gets a list of all plant elements in the database.
     *
     * @return a list of all plants in the database
     */
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

    /**
     * Deletes a specified plant in the database
     * @param plant the plant to delete
     * @return true if plant was deleted, false if not
     */
    @Override
    public boolean deletePlant(Plant plant) {
        boolean deleted = false;
        try{
            String query = "DELETE FROM Plant " + "WHERE sensorId = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1,plant.getSensorID());
            int updatedRowCount = s.executeUpdate();
            deleted = updatedRowCount == 1;
        }catch (SQLException e) {
            System.out.println("An error occured when deleting a plant to the database");
        }

        return deleted;
    }

    /**
     * Adds a plant to the database
     * @param plant the plant to add to the database
     * @return true if plant was added, false if not.
     */
    @Override
    public boolean addPlant(Plant plant) {
        boolean added = false;

        try{
            String query = "INSERT INTO Plant (plantName, plantType, sensorId, desiredMoisture)VALUES(?,?,?,?)";
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, plant.getName());
            s.setString(2, plant.getType());
            s.setInt(3, plant.getSensorID());
            s.setDouble(4, plant.getDesiredMoistureLevel());

            int updatedRowCount = s.executeUpdate();
            added = updatedRowCount == 1;
        }catch (SQLException e) {
            System.out.println("An error occured when adding a a plant to the database");
        }

        return added;

    }
}
