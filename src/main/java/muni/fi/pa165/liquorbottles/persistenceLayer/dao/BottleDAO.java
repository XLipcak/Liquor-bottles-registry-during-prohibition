package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.sql.Date;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
public interface BottleDAO {
    List<Bottle> findAll();
    Bottle findById(long id);
    Bottle findByBatchId(long id);
    Bottle findByStamp(long stamp);
    List<Bottle> findByDate(Date date); // mozno treba iny import (teraz je sql.Date)
    List<Bottle> findByToxicity(boolean isToxic);
    
    void insertBottle(Bottle bottle);
    void updateBottle(Bottle bottle);
    void deleteBottle(Bottle bottle);
}
