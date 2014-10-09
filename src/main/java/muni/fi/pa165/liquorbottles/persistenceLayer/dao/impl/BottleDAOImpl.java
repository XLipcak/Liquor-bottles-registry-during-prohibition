package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.sql.Date;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
public class BottleDAOImpl implements BottleDAO{

    @Override
    public List<Bottle> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bottle findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bottle findByBatchId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bottle findByStamp(long stamp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bottle> findByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bottle> findByToxicity(boolean isToxic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertBottle(Bottle bottle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateBottle(Bottle bottle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBottle(Bottle bottle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
