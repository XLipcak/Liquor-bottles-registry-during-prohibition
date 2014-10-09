package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
public interface StoreDAO {
    List<Store> findAll();
    Store findById(long id);
    Store findByAdress(String adress);
    
    void insertStore(Store store);
    void updateStore(Store store);
    void deleteStore(Store store);
}
