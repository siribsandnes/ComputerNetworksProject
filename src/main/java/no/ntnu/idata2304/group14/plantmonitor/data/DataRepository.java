package no.ntnu.idata2304.group14.plantmonitor.data;

import java.util.List;

public interface DataRepository {

    /**
     * Connect to the database
     *
     * @return True if connection was successfull, false if not.
     */
    boolean connect();

    /**
     * Get a list of all the plants in the database
     *
     * @return A list of all plants in the database
     */
    List<Plant> getAllPlants();

    /**
     * Delete a plant from the database
     *
     * @return true if plant was deleted, false if not.
     */
    boolean deletePlant(Plant plant);

    /**
     * Adds a plant to the database
     *
     * @return Returns true if plant was added, false if not.
     */
    boolean addPlant(Plant plant);
}
