package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.StoreDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.StoreDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerStoreDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.StoreService;
import org.springframework.dao.NonTransientDataAccessResourceException;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class StoreServiceImpl implements StoreService {

    EntityManagerFactory emf;
    StoreDAO storeDAOImpl;
    DozerStoreDTOConvertor dozerStoreDTOConvertor = new DozerStoreDTOConvertor();

    public StoreServiceImpl() {
        emf = Persistence.createEntityManagerFactory(
                "localDB");
        storeDAOImpl = new StoreDAOImpl(emf);
    }
    
    public void closeEMF(){
        this.emf.close();
    }

    @Override
    public List<StoreDTO> findAll() {
        try {
            List<Store> store = storeDAOImpl.findAll();
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public StoreDTO findById(long id) {
        try {
            Store store = storeDAOImpl.findById(id);
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public StoreDTO findByAddress(String address) {
        try {
            Store store = storeDAOImpl.findByAddress(address);
            return dozerStoreDTOConvertor.fromEntityToDTO(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public void insertStore(StoreDTO storeDTO) {
        try {
            Store store = dozerStoreDTOConvertor.fromDTOToEntity(storeDTO);
            storeDAOImpl.insertStore(store);
            storeDTO.setId(store.getId());
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(ex.getMessage());     
        }
    }

    @Override
    public void updateStore(StoreDTO storeDTO) {
        try {
            Store store = dozerStoreDTOConvertor.fromDTOToEntity(storeDTO);
            storeDAOImpl.updateStore(store);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException(ex.getMessage());     
        }
    }

    @Override
    public void deleteStore(StoreDTO storeDTO) {
        try {
            Store store = dozerStoreDTOConvertor.fromDTOToEntity(storeDTO);
            storeDAOImpl.deleteStore(store);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

}
