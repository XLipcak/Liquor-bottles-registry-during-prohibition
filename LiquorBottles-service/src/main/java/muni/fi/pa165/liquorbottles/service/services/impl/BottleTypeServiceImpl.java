package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import muni.fi.pa165.liquorbottles.service.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DTOConvertor;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerBottleTypeDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.BottleTypeService;
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
public class BottleTypeServiceImpl implements BottleTypeService {

    private BottleTypeDAO bottleTypeDAO;
    private DozerBottleTypeDTOConvertor bottleTypeDTOConvertor = new DozerBottleTypeDTOConvertor();

    /* public BottleTypeServiceImpl(BottleDAO bottleDAOImpl) {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("localDB");
     bottleTypeDAO = new BottleTypeDAOImpl(emf);
     }*/
    @Override
    public List<BottleTypeDTO> findAll() {
        try {
            List<BottleType> allBottleTypes = bottleTypeDAO.findAll();
            return bottleTypeDTOConvertor.fromEntityToDTO(allBottleTypes);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public BottleTypeDTO findById(long id) {
        try {
            BottleType bottleTypeDTO = bottleTypeDAO.findById(id);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypeDTO);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public List<BottleTypeDTO> findByAlcType(String alcType) {
        try {
            List<BottleType> bottleTypesByAlcType = bottleTypeDAO.findByAlcType(alcType);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypesByAlcType);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public List<BottleTypeDTO> findByPower(int power) {
        try {
            List<BottleType> bottleTypesByPower = bottleTypeDAO.findByPower(power);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypesByPower);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public List<BottleTypeDTO> findByVolume(int volume) {
        try {
            List<BottleType> bottleTypesByVolume = bottleTypeDAO.findByVolume(volume);
            return bottleTypeDTOConvertor.fromEntityToDTO(bottleTypesByVolume);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
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
    public void updateBottleType(BottleTypeDTO bottleTypeDTO) {
        try {
            BottleType bottleType = bottleTypeDTOConvertor.fromDTOToEntity(bottleTypeDTO);
            bottleTypeDAO.updateBottleType(bottleType);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    public void deleteBottleType(BottleTypeDTO bottleTypeDTO) {
        try {
            BottleType bottleType = bottleTypeDTOConvertor.fromDTOToEntity(bottleTypeDTO);
            bottleTypeDAO.deleteBottleType(bottleType);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Required
    public void setBottleTypeDAO(BottleTypeDAO bottleTypeDAO) {
        this.bottleTypeDAO = bottleTypeDAO;
    }

}
