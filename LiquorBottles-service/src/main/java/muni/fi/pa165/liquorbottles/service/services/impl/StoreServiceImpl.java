package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.StoreDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import muni.fi.pa165.liquorbottles.api.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.api.dto.UserDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerStoreDTOConvertor;
import muni.fi.pa165.liquorbottles.api.services.StoreService;
import muni.fi.pa165.liquorbottles.api.services.UserService;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author Matus Novak, Masaryk University
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    StoreDAO storeDAO;
    DozerStoreDTOConvertor dozerStoreDTOConvertor = new DozerStoreDTOConvertor();

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE", "ROLE_PRODUCER"})
    public List<StoreDTO> findAll() {
        try {
            List<Store> store = storeDAO.findAll();
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE", "ROLE_STORE", "ROLE_PRODUCER"})
    public StoreDTO findById(long id) {
        try {
            Store store = storeDAO.findById(id);
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    public StoreDTO findByAddress(String address) {
        try {
            Store store = storeDAO.findByAddress(address);
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    public List<StoreDTO> findByFilter(String name, String address) {
        try {
            List<Store> store = storeDAO.findByFilter(name, address);
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void insertStore(StoreDTO storeDTO) {
        try {
            Store store = dozerStoreDTOConvertor.fromDTOToEntity(storeDTO);
            storeDAO.insertStore(store);
            storeDTO.setId(store.getId());
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(ex.getMessage());
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void updateStore(StoreDTO storeDTO) {
        try {
            Store store = dozerStoreDTOConvertor.fromDTOToEntity(storeDTO);
            if(store.getPassword().equals("")){
                store.setPassword(storeDAO.findById(store.getId()).getPassword());
            }
            storeDAO.updateStore(store);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(ex.getMessage());
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void deleteStore(StoreDTO storeDTO) {
        try {
            Store store = dozerStoreDTOConvertor.fromDTOToEntity(storeDTO);
            storeDAO.deleteStore(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Required
    @Secured({"ROLE_ADMIN"})
    public void setStoreDAO(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

}
