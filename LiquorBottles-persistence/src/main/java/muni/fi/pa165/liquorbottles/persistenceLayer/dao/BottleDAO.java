package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.Date;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;

/**
 * Interface BottleDAO describes basic operations for returning desired Bottle
 * classes from database and also the CRUD operations to the database.
 *
 * @author Michal Taraj, Masaryk University
 */
public interface BottleDAO {
    /**
     * This method returns all Bottles from database as a List
     * @return List of all Bottles
     */
    List<Bottle> findAll();

    /**
     * This method returns the Bottle from database specified by id parameter
     * @param id of Bottle in the database
     * @return the Bottle with id as parameter
     */
    Bottle findById(long id);

    /**
     * This method returns the List of Bottles from database specified by id parameter
     * @param id batchId of Bottles in the database
     * @return List of Bottles with batchId
     */
    List<Bottle> findByBatchId(long id);

    /**
     * This method returns the Bottle from database specified by stamp parameter
     * @param stamp of Bottle in the database
     * @return Bottle with stamp as parameter
     */
    Bottle findByStamp(long stamp);

    /**
     * This method returns the List of Bottles from database specified by date of birth as parameter
     * @param date of birth of certain Bottles in the database
     * @return List of Bottles with date as parameter
     */
    List<Bottle> findByDate(Date date);

    /**
     * This method returns the List of Bottles from database specified by its toxicity as parameter
     * @param toxic of Bottles in the database
     * @return List of Bottles with toxic as parameter
     */
    List<Bottle> findByToxicity(Toxicity toxic);
 
    /**
     * This method returns the List of Bottles from database specified by the filter parameters
     * @param bottleTypeDAO_id
     * @param storeDAO_id
     * @param batch_id
     * @param startDate
     * @param endDate
     * @param stamp
     * @param toxic the toxicity
     * @param producerDAO_id
     * @return List of Bottles with toxic as parameter
     */
    List<Bottle> findByFilter(long bottleTypeDAO_id, long producerDAO_id, long storeDAO_id,
            Toxicity toxic, Date startDate, Date endDate, long batch_id, long stamp);
    
    /**
     * This method inserts the Bottle as parameter to the database
     * @param bottle to be inserted to the database
     */
    void insertBottle(Bottle bottle);

    /**
     * This method updates the Bottle as parameter to the database
     * @param bottle to be updated to the database
     */
    void updateBottle(Bottle bottle);

    /**
     * This method deletes the Bottle as parameter from the database
     * @param bottle to be deleted from the database
     */
    void deleteBottle(Bottle bottle);
}
