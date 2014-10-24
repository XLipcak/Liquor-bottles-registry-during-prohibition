package muni.fi.pa165.liquorbottles.serviceLayer.services.impl;

import java.util.Date;
import java.util.List;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.serviceLayer.services.BottleService;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class BottleServiceImpl implements BottleService{

    @Override
    public List<BottleDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BottleDTO findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BottleDTO> findByBatchId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BottleDTO findByStamp(long stamp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BottleDTO> findByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BottleDTO> findByToxicity(ToxicityDTO toxic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertBottle(BottleDTO bottle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateBottle(BottleDTO bottle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBottle(BottleDTO bottle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
