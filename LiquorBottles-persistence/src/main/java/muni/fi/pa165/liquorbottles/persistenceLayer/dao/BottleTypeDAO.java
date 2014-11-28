package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;

/**
 * Interface BottleTypeDAO describes basic operations for returning desired BottleType
 * classes from database and also the CRUD operations to the database.
 *
 * @author Michal Taraj, Masaryk University
 */
public interface BottleTypeDAO {

    /**
     * This method returns all bottle types from database as a List
     * @return List of bottle types
     */
    List<BottleType> findAll();

    /**
     * This method returns the bottle types from database specified by id parameter
     * @param id of bottle type in the database
     * @return the bottle type with id as parameter
     */
    BottleType findById(long id);

    /**
     * This method returns the bottle types from database specified by alcohol type parameter
     * @param alcType of bottle type in the database
     * @return the bottle type with alcType as parameter
     */
    List<BottleType> findByAlcType(String alcType);

    /**
     * This method returns the bottle types from database specified by alcoholic strength as parameter
     * @param power of bottle type in the database
     * @return the bottle type with power as parameter
     */
    List<BottleType> findByPower(int power);

    /**
     * This method returns the bottle types from database specified by volume as parameter
     * @param volume of bottle type in the database
     * @return the bottle type with volume as parameter
     */
    List<BottleType> findByVolume(int volume);
    
    /**
     * This method returns the bottle types from database specified by producer id as parameter
     * @param producerID of bottle type in the database
     * @return the bottle types produced by specific producer
     */
    List<BottleType> findByProducer(long producerID);

    /**
     * This method inserts the bottle type as parameter to the database
     * @param bottleType to be inserted to the database
     */
    void insertBottleType(BottleType bottleType);

    /**
     * This method updates the bottle type as parameter to the database
     * @param bottleType to be updated to the database
     */
    void updateBottleType(BottleType bottleType);

    /**
     * This method deletes the bottle type as parameter from the database
     * @param bottleType to be deleted from the database
     */
    void deleteBottleType(BottleType bottleType);
}
