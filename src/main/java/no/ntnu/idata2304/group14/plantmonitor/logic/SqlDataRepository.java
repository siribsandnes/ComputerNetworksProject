package no.ntnu.idata2304.group14.plantmonitor.logic;

import no.ntnu.idata2304.group14.plantmonitor.data.DataRepository;
import no.ntnu.idata2304.group14.plantmonitor.data.Plant;
import java.sql.Connection;
import java.sql.DriverManager;


import java.sql.SQLException;
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
         Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
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
        return null;
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
