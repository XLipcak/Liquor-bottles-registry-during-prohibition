package muni.fi.pa165.liquorbottles.serviceLayer.services.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.BottleTypeDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor.DTOConvertor;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor.DozerBottleTypeDTOConvertor;
import muni.fi.pa165.liquorbottles.serviceLayer.services.BottleTypeService;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
public class BottleTypeServiceImpl implements BottleTypeService{
    
    // TODO replace by Spring injection later
    private BottleTypeDAO bottleTypeDAO;
    private DTOConvertor<BottleType, BottleTypeDTO> convertor = new DozerBottleTypeDTOConvertor();

    public BottleTypeServiceImpl(BottleDAO bottleDAOImpl) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localDB");
        bottleTypeDAO = new BottleTypeDAOImpl(emf);
    }

    @Override
    public List<BottleTypeDTO> findAll() {
        try {
            List<BottleType> allBottleTypes = bottleTypeDAO.findAll();
            return convertor.fromEntityToDTO(allBottleTypes);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(); //replace by service exception
        }
    }

    @Override
    public BottleTypeDTO findById(long id) {
        try {
            BottleType bottleTypeDTO = bottleTypeDAO.findById(id);
            return convertor.fromEntityToDTO(bottleTypeDTO);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public List<BottleTypeDTO> findByAlcType(String alcType) {
        try {
            List<BottleType> bottleTypesByAlcType = bottleTypeDAO.findByAlcType(alcType);
            return convertor.fromEntityToDTO(bottleTypesByAlcType);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(); //replace by service exception
        }
    }

    @Override
    public List<BottleTypeDTO> findByPower(int power) {
        try {
            List<BottleType> bottleTypesByPower = bottleTypeDAO.findByPower(power);
            return convertor.fromEntityToDTO(bottleTypesByPower);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(); //replace by service exception
        }
    }

    @Override
    public List<BottleTypeDTO> findByVolume(int volume) {
        try {
            List<BottleType> bottleTypesByVolume = bottleTypeDAO.findByVolume(volume);
            return convertor.fromEntityToDTO(bottleTypesByVolume);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(); //replace by service exception
        }
    }

    @Override
    public void insertBottleType(BottleTypeDTO bottleTypeDTO) {
        try {
            BottleType bottleType = convertor.fromDTOToEntity(bottleTypeDTO);
            bottleTypeDAO.insertBottleType(bottleType);
            bottleTypeDTO.setId(bottleType.getId());
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public void updateBottleType(BottleTypeDTO bottleTypeDTO) {
        try {
            BottleType bottleType = convertor.fromDTOToEntity(bottleTypeDTO);
            bottleTypeDAO.updateBottleType(bottleType);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public void deleteBottleType(BottleTypeDTO bottleTypeDTO) {
        try {
            BottleType bottleType = convertor.fromDTOToEntity(bottleTypeDTO);
            bottleTypeDAO.deleteBottleType(bottleType);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }
    
}
