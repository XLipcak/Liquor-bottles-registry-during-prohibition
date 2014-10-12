package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;

/**
 * Interface StoreDAO describes basic operations for returning desired Store
 * classes from database and also the CRUD operations to the database.
 *
 * @author Michal Taraj, Masaryk University
 */
public interface StoreDAO {

    /**
     * This method returns all stores from database as a List
     * @return List of stores
     */
    List<Store> findAll();

    /**
     * This method returns the Store from database specified by id parameter
     * @param id of Store in the database
     * @return the Store with id as parameter
     */
    Store findById(long id);

    /**
     * This method returns the Store from database specified by address parameter
     * @param address of Store in the database
     * @return the Store with adress as parameter
     */
    Store findByAddress(String address);

    /**
     * This method inserts the Store as parameter to the database
     * @param store to be inserted to the database
     */
    void insertStore(Store store);

    /**
     * This method updates the Store as parameter to the database
     * @param store to be updated to the database
     */
    void updateStore(Store store);

    /**
     * This method deletes the Store as parameter from the database
     * @param store to be deleted from the database
     */
    void deleteStore(Store store);
}
