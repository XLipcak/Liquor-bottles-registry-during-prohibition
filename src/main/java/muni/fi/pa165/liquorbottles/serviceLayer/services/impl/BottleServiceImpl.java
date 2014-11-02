package muni.fi.pa165.liquorbottles.serviceLayer.services.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.BottleDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor.DTOConvertor;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor.DozerBottleDTOConvertor;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor.DozerToxicityDTOConvertor;
import muni.fi.pa165.liquorbottles.serviceLayer.services.BottleService;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
public class BottleServiceImpl implements BottleService{
    
    // TODO replace by Spring injection later
    private BottleDAO bottleDAO;
    private DTOConvertor<Bottle, BottleDTO> convertor = new DozerBottleDTOConvertor();

    public BottleServiceImpl(BottleDAO bottleDao) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localDB");
        bottleDAO = new BottleDAOImpl(emf);
    }

    @Override
    public List<BottleDTO> findAll() {
        try {
            List<Bottle> allBottles = bottleDAO.findAll();
            return convertor.fromEntityToDTO(allBottles);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public BottleDTO findById(long id) {
        try {
            Bottle bottleDTO = bottleDAO.findById(id);
            return convertor.fromEntityToDTO(bottleDTO);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public List<BottleDTO> findByBatchId(long id) {
        try {
            List<Bottle> bottlesByBatchId = bottleDAO.findByBatchId(id);
            return convertor.fromEntityToDTO(bottlesByBatchId);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public BottleDTO findByStamp(long stamp) {
        try {
            Bottle bottleDTO = bottleDAO.findByStamp(stamp);
            return convertor.fromEntityToDTO(bottleDTO);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public List<BottleDTO> findByDate(Date date) {
        try {
            List<Bottle> bottlesByDate = bottleDAO.findByDate(date);
            return convertor.fromEntityToDTO(bottlesByDate);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public List<BottleDTO> findByToxicity(ToxicityDTO toxicDTO) {
        try {
            DTOConvertor<Toxicity, ToxicityDTO> toxicityConvertor = new DozerToxicityDTOConvertor();
            Toxicity toxic = toxicityConvertor.fromDTOToEntity(toxicDTO);
            List<Bottle> bottlesByToxicity = bottleDAO.findByToxicity(toxic);
            return convertor.fromEntityToDTO(bottlesByToxicity);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public void insertBottle(BottleDTO bottleDTO) {
        try {
            Bottle bottle = convertor.fromDTOToEntity(bottleDTO);
            bottleDAO.insertBottle(bottle);
            bottleDTO.setId(bottle.getId());
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public void updateBottle(BottleDTO bottleDTO) {
        try {
            Bottle bottle = convertor.fromDTOToEntity(bottleDTO);
            bottleDAO.updateBottle(bottle);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public void deleteBottle(BottleDTO bottleDTO) {
        try {
            Bottle bottle = convertor.fromDTOToEntity(bottleDTO);
            bottleDAO.deleteBottle(bottle);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }
    
}
