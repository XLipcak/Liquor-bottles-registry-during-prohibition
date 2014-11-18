package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.StoreDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerStoreDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.StoreService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matus Novak, Masaryk University
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    StoreDAO storeDAO;
    DozerStoreDTOConvertor dozerStoreDTOConvertor = new DozerStoreDTOConvertor();

    /*public StoreServiceImpl() {
        emf = Persistence.createEntityManagerFactory(
                "localDB");
        storeDAOImpl = new StoreDAOImpl(emf);
    }*/

    @Override
    public List<StoreDTO> findAll() {
        try {
            List<Store> store = storeDAO.findAll();
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);     
        }
    }

    @Override
    public StoreDTO findById(long id) {
        try {
            Store store = storeDAO.findById(id);
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);     
        }
    }

    @Override
    public StoreDTO findByAddress(String address) {
        try {
            Store store = storeDAO.findByAddress(address);
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);     
        }
    }

    @Override
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
    public void updateStore(StoreDTO storeDTO) {
        try {
            Store store = dozerStoreDTOConvertor.fromDTOToEntity(storeDTO);
            storeDAO.updateStore(store);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(ex.getMessage());     
        }
    }

    @Override
    public void deleteStore(StoreDTO storeDTO) {
        try {
            Store store = dozerStoreDTOConvertor.fromDTOToEntity(storeDTO);
            storeDAO.deleteStore(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);     
        }
    }
    @Required
    public void setStoreDAO(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

}
