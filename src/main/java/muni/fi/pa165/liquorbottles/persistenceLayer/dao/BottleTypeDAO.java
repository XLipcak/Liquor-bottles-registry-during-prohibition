package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
public interface BottleTypeDAO {
    List<BottleType> findAll();
    BottleType findById(long id);
    List<BottleType> findByAlcType(String alcType);
    List<BottleType> findByPower(int power);
    List<BottleType> findByVolume(int volume); 
    
    void insertBottleType(BottleType bottleType);
    void updateBottleType(BottleType bottleType);
    void deleteBottleType(BottleType bottleType);
}
