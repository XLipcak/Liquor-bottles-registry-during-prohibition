package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;

/**
 * Interface PoliceDAO describes basic operations for returning desired Police classes from database 
 * and also the CRUD operations to the database.
 * @author Matus Novak, Masaryk University
 */
public interface PoliceDAO {
    
    /**
     * This method returns all Police from database as a List 
     */
    List<Police> findAll();
    
    /**
     * This method returns the Police from database specified by id parameter
     * @param id of Police in the database
     * @return the Police with id as parameter
     */
    Police findById(long id);
    
    /**
     * This method returns the Police from database specified by userName parameter
     * @param userName userName of Police in the database
     * @return Police with userName as parameter
     */
    Police findByUsername(String userName);
    
    /**
     * This method returns the Police from database specified by name parameter
     * @param name name of Police in the database
     * @return Police with name as parameter
     */
    Police findByName(String name);
    
    /**
     * This method returns the Police from database specified by address parameter
     * @param address address of Police in the database
     * @return Police with address as parameter
     */
    Police findByAddress(String address);
   
    /**
     * This method inserts the police as parameter to the database
     * @param police to be inserted to the database
     */
    void insertPolice(Police police);
    
    /**
     * This method updates the police as parameter to the database
     * @param police to be updated to the database
     */
    void updatePolice(Police police);
    
    /**
     * This method deletes the police as parameter from the database
     * @param police to be deleted from the database
     */
    void deletePolice(Police police);
    
}
