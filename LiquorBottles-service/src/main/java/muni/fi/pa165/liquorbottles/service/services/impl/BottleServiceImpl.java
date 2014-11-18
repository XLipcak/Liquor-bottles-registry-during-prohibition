package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.service.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.service.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DTOConvertor;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerBottleDTOConvertor;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerToxicityDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.BottleService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
@Service
@Transactional
public class BottleServiceImpl implements BottleService {

    private BottleDAO bottleDAO;
    private DozerBottleDTOConvertor bottleDTOConvertor = new DozerBottleDTOConvertor();

    /*public BottleServiceImpl(BottleDAO bottleDao) {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("localDB");
     bottleDAO = new BottleDAOImpl(emf);
     }*/
    @Override
    public List<BottleDTO> findAll() {
        try {
            List<Bottle> allBottles = bottleDAO.findAll();
            return bottleDTOConvertor.fromEntityToDTO(allBottles);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public BottleDTO findById(long id) {
        try {
            Bottle bottleDTO = bottleDAO.findById(id);
            return bottleDTOConvertor.fromEntityToDTO(bottleDTO);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public List<BottleDTO> findByBatchId(long id) {
        try {
            List<Bottle> bottlesByBatchId = bottleDAO.findByBatchId(id);
            return bottleDTOConvertor.fromEntityToDTO(bottlesByBatchId);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public BottleDTO findByStamp(long stamp) {
        try {
            Bottle bottleDTO = bottleDAO.findByStamp(stamp);
            return bottleDTOConvertor.fromEntityToDTO(bottleDTO);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public List<BottleDTO> findByDate(Date date) {
        try {
            List<Bottle> bottlesByDate = bottleDAO.findByDate(date);
            return bottleDTOConvertor.fromEntityToDTO(bottlesByDate);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public List<BottleDTO> findByToxicity(ToxicityDTO toxicDTO) {
        try {
            DTOConvertor<Toxicity, ToxicityDTO> toxicityConvertor = new DozerToxicityDTOConvertor();
            Toxicity toxic = toxicityConvertor.fromDTOToEntity(toxicDTO);
            List<Bottle> bottlesByToxicity = bottleDAO.findByToxicity(toxic);
            return bottleDTOConvertor.fromEntityToDTO(bottlesByToxicity);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public void insertBottle(BottleDTO bottleDTO) {
        try {
            Bottle bottle = bottleDTOConvertor.fromDTOToEntity(bottleDTO);
            bottleDAO.insertBottle(bottle);
            bottleDTO.setId(bottle.getId());
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public void updateBottle(BottleDTO bottleDTO) {
        try {
            Bottle bottle = bottleDTOConvertor.fromDTOToEntity(bottleDTO);
            bottleDAO.updateBottle(bottle);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public void deleteBottle(BottleDTO bottleDTO) {
        try {
            Bottle bottle = bottleDTOConvertor.fromDTOToEntity(bottleDTO);
            bottleDAO.deleteBottle(bottle);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Required
    public void setBottleDAO(BottleDAO bottleDAO) {
        this.bottleDAO = bottleDAO;
    }

}
