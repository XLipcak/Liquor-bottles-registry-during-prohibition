package muni.fi.pa165.liquorbottles.serviceLayer.services;

import java.util.List;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.StoreDTO;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public interface StoreService {

    List<StoreDTO> findAll();

    StoreDTO findById(long id);

    StoreDTO findByAddress(String address);

    void insertStore(StoreDTO store);

    void updateStore(StoreDTO store);

    void deleteStore(StoreDTO store);
}
