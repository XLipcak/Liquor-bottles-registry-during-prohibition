package muni.fi.pa165.liquorbottles.api.services;

import java.util.Date;
import java.util.List;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.dto.ToxicityDTO;

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

    List<BottleDTO> findByToxicity(ToxicityDTO toxicDTO);

    List<BottleDTO> findByFilter(long bottleTypeDAO_id, long producerDAO_id, long storeDAO_id,
            ToxicityDTO toxicDTO, Date startDate, Date endDate, long batch_id, long stamp
    );

    void insertBottle(BottleDTO bottle);

    void updateBottle(BottleDTO bottle);

    void deleteBottle(BottleDTO bottle);
}
