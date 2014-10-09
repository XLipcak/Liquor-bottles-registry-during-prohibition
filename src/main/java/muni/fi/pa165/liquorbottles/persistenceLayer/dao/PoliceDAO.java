package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public interface PoliceDAO {
    
    List<Police> findAll();
    Police findById(long id);
    Police findByUsername(String userName);
    Police findByName(String userName);
    Police findAddress(String userName);
   
    void insertPolice(Police police);
    void updatePolice(Police police);
    void deletePolice(Police police);
    
}
