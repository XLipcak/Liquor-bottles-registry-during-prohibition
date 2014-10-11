package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;

/**
 * Interface ProducerDAO describes basic operations for returning desired Producer classes from database 
 * and also the CRUD operations to the database.
 * @author Matus Novak, Masaryk University
 */
public interface ProducerDAO {
    
    /**
     * This method returns all Producer from database as a List
     * @return List containing all Producers from database
     */
    List<Producer> findAll();
    
    /**
     * This method returns the Producer from database specified by id parameter
     * @param id id of Producer in the database
     * @return Producer with id as parameter
     */
    Producer findById(long id);
    
    /**
     * This method returns the Producer from database specified by userName parameter
     * @param userName userName of Producer in the database
     * @return Producer with userName as parameter
     */
    Producer findByUsername(String userName);
    
    /**
     * This method returns the Producer from database specified by name parameter
     * @param name name of Producer in the database
     * @return Producer with name as parameter
     */
    Producer findByName(String name);
    
    /**
     * This method returns the Producer from database specified by address parameter
     * @param address address of Producer in the database
     * @return Producer with address as parameter
     */
    Producer findByAddress(String address);
   
    /**
     * This method inserts the Producer as parameter to the database
     * @param producer to be inserted to the database
     */
    void insertProducer(Producer producer);
    
    /**
     * This method updates the Producer as parameter to the database
     * @param producer to be updated to the database
     */
    void updateProducer(Producer producer);
    
    /**
     * This method deletes the Producer as parameter from the database
     * @param producer to be deleted from the database
     */
    void deleteProducer(Producer producer);
    
}
