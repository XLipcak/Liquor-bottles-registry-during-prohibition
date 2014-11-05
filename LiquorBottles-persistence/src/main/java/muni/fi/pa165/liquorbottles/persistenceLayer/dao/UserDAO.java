package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;

/**
 * Interface UserDAO describes basic operations for returning desired User classes from database 
 * and also the CRUD operations to the database.
 * @author Matus Novak, Masaryk University
 */
public interface UserDAO {
    
    /**
     * This method returns all Users from database as a List
     * @return List containing all Users from database
     */
    List<User> findAll();
    
    /**
     * This method returns the User from database specified by id parameter
     * @param id id of User in the database
     * @return User with id as parameter
     */
    User findById(long id);
    
    /**
     * This method returns the User from database specified by userName parameter
     * @param userName userName of User in the database
     * @return the User with userName as parameter
     */
    User findByUsername(String userName);
    
    /**
     * This method returns String which is a password of a user from database specified by userName parameter
     * @param userName userName of User in the database
     * @return String which is a password of the User
     */
    String findPassByUsername(String userName);
    
    /**
     * This method inserts the user as parameter to the database
     * @param user to be inserted to the database
     */
    void insertUser(User user);
    
    /**
     * This method deletes the user as parameter from the database
     * @param user to be deleted from the database
     */
    void deleteUser(User user);
    
    /**
     * This method updates the user as parameter to the database
     * @param user to be updated to the database
     */
    void updateUser(User user);
    
}
