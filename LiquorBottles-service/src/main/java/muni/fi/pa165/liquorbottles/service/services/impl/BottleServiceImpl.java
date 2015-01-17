package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DTOConvertor;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerBottleDTOConvertor;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerToxicityDTOConvertor;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author Michal Taraj, Masaryk University
 */

@Service
@Transactional
public class BottleServiceImpl implements BottleService {

    private BottleDAO bottleDAO;
    private DozerBottleDTOConvertor bottleDTOConvertor = new DozerBottleDTOConvertor();

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE", "ROLE_PRODUCER"})
    public List<BottleDTO> findAll() {
        try {
            List<Bottle> allBottles = bottleDAO.findAll();
            return bottleDTOConvertor.fromEntityToDTO(allBottles);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE", "ROLE_PRODUCER"})
    public BottleDTO findById(long id) {
        try {
            Bottle bottleDTO = bottleDAO.findById(id);
            return bottleDTOConvertor.fromEntityToDTO(bottleDTO);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE"})
    public List<BottleDTO> findByBatchId(long id) {
        try {
            List<Bottle> bottlesByBatchId = bottleDAO.findByBatchId(id);
            return bottleDTOConvertor.fromEntityToDTO(bottlesByBatchId);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE"})
    public BottleDTO findByStamp(long stamp) {
        try {
            Bottle bottleDTO = bottleDAO.findByStamp(stamp);
            return bottleDTOConvertor.fromEntityToDTO(bottleDTO);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE"})
    public List<BottleDTO> findByDate(Date date) {
        try {
            List<Bottle> bottlesByDate = bottleDAO.findByDate(date);
            return bottleDTOConvertor.fromEntityToDTO(bottlesByDate);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE"})
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
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE"})
    public List<BottleDTO> findByFilter(long bottleTypeDAO_id, long producerDAO_id, long storeDAO_id, ToxicityDTO toxicDTO, Date startDate, Date endDate, long batch_id, long stamp) {
        try {
            DTOConvertor<Toxicity, ToxicityDTO> toxicityConvertor = new DozerToxicityDTOConvertor();
            Toxicity toxic = toxicityConvertor.fromDTOToEntity(toxicDTO);
            List<Bottle> bottlesByFilter = bottleDAO.findByFilter(bottleTypeDAO_id, producerDAO_id, storeDAO_id, toxic, startDate, endDate, batch_id, stamp);
            return bottleDTOConvertor.fromEntityToDTO(bottlesByFilter);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN","ROLE_STORE"})
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
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE"})
    public void updateBottle(BottleDTO bottleDTO) {
        try {
            Bottle bottle = bottleDTOConvertor.fromDTOToEntity(bottleDTO);
            bottleDAO.updateBottle(bottle);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void deleteBottle(BottleDTO bottleDTO) {
        try {
            Bottle bottle = bottleDTOConvertor.fromDTOToEntity(bottleDTO);
            bottleDAO.deleteBottle(bottle);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Required
    @Secured({"ROLE_ADMIN", "ROLE_STORE"})
    public void setBottleDAO(BottleDAO bottleDAO) {
        this.bottleDAO = bottleDAO;
    }

}
