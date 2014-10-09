package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public interface UserDAO {
    
    List<User> findAll();
    User findById(long id);
    User findByUsername(String userName);
    User findPassByUsername(String userName);
    void deleteById(long id);
    
}
