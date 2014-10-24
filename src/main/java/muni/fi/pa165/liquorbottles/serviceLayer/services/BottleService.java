package muni.fi.pa165.liquorbottles.serviceLayer.services;

import java.util.Date;
import java.util.List;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.ToxicityDTO;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public interface BottleService {

    List<BottleDTO> findAll();

    BottleDTO findById(long id);

    List<BottleDTO> findByBatchId(long id);

    BottleDTO findByStamp(long stamp);

    List<BottleDTO> findByDate(Date date);

    List<BottleDTO> findByToxicity(ToxicityDTO toxic);

    void insertBottle(BottleDTO bottle);

    void updateBottle(BottleDTO bottle);

    void deleteBottle(BottleDTO bottle);
}
