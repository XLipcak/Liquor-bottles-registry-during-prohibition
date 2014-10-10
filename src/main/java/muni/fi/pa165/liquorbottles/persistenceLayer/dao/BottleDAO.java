package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.Date;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
public interface BottleDAO {
    List<Bottle> findAll();
    Bottle findById(long id);
    List<Bottle> findByBatchId(long id);
    Bottle findByStamp(long stamp);
    List<Bottle> findByDate(Date date); 
    List<Bottle> findByToxicity(Toxicity isToxic);
    public enum Toxicity{
        toxic, unchecked, nonToxic; 
    }
    void insertBottle(Bottle bottle);
    void updateBottle(Bottle bottle);
    void deleteBottle(Bottle bottle);
}
