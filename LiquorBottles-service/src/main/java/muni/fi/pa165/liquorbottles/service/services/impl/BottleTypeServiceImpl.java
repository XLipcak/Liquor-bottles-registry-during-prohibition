package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerBottleTypeDTOConvertor;
import muni.fi.pa165.liquorbottles.api.services.BottleTypeService;
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
public class BottleTypeServiceImpl implements BottleTypeService {

    private BottleTypeDAO bottleTypeDAO;
    private DozerBottleTypeDTOConvertor bottleTypeDTOConvertor = new DozerBottleTypeDTOConvertor();

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<BottleTypeDTO> findAll() {
        try {
            List<BottleType> allBottleTypes = bottleTypeDAO.findAll();
            return bottleTypeDTOConvertor.fromEntityToDTO(allBottleTypes);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public BottleTypeDTO findById(long id) {
        try {
            BottleType bottleTypeDTO = bottleTypeDAO.findById(id);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypeDTO);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<BottleTypeDTO> findByAlcType(String alcType) {
        try {
            List<BottleType> bottleTypesByAlcType = bottleTypeDAO.findByAlcType(alcType);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypesByAlcType);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<BottleTypeDTO> findByPower(int power) {
        try {
            List<BottleType> bottleTypesByPower = bottleTypeDAO.findByPower(power);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypesByPower);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<BottleTypeDTO> findByVolume(int volume) {
        try {
            List<BottleType> bottleTypesByVolume = bottleTypeDAO.findByVolume(volume);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypesByVolume);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<BottleTypeDTO> findByProducer(long producerID) {
        try {
            List<BottleType> bottleTypesByProducer = bottleTypeDAO.findByProducer(producerID);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypesByProducer);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<BottleTypeDTO> findByFilter(long producerID, String name, String alcType, int power, int volume) {
        try {
            List<BottleType> bottleTypesByFilter = bottleTypeDAO.findByFilter(producerID, name, alcType, power, volume);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypesByFilter);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void insertBottleType(BottleTypeDTO bottleTypeDTO) {
        try {
            BottleType bottleType = bottleTypeDTOConvertor.fromDTOToEntity(bottleTypeDTO);
            bottleTypeDAO.insertBottleType(bottleType);
            bottleTypeDTO.setId(bottleType.getId());
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void updateBottleType(BottleTypeDTO bottleTypeDTO) {
        try {
            BottleType bottleType = bottleTypeDTOConvertor.fromDTOToEntity(bottleTypeDTO);
            bottleTypeDAO.updateBottleType(bottleType);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void deleteBottleType(BottleTypeDTO bottleTypeDTO) {
        try {
            BottleType bottleType = bottleTypeDTOConvertor.fromDTOToEntity(bottleTypeDTO);
            bottleTypeDAO.deleteBottleType(bottleType);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Required
    @Secured({"ROLE_ADMIN"})
    public void setBottleTypeDAO(BottleTypeDAO bottleTypeDAO) {
        this.bottleTypeDAO = bottleTypeDAO;
    }
}
