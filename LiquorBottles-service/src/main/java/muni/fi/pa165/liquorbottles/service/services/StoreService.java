package muni.fi.pa165.liquorbottles.service.services;

import java.util.List;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public interface StoreService {

    List<StoreDTO> findAll();

    StoreDTO findById(long id);

    StoreDTO findByAddress(String address);
    
    List<StoreDTO> findByFilter(String name, String address);

    void insertStore(StoreDTO storeDTO);

    void updateStore(StoreDTO storeDTO);

    void deleteStore(StoreDTO storeDTO);
}
