package no.ntnu.idata2304.group14.plantmonitor.data;

import java.util.List;

public interface DataRepository {

    /**
     *
     * @return
     */
    boolean connect();

    /**
     *
     * @return
     */
    List<Plant> getAllPlants();

    /**
     *
     * @return
     */
    boolean deletePlant(Plant plant);

    /**
     *
     * @return
     */
    boolean addPlant(Plant plant);
}
