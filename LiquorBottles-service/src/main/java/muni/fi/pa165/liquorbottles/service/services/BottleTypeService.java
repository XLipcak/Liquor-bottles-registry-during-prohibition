package muni.fi.pa165.liquorbottles.service.services;

import java.util.List;
import muni.fi.pa165.liquorbottles.service.dto.BottleTypeDTO;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public interface BottleTypeService {

    List<BottleTypeDTO> findAll();

    BottleTypeDTO findById(long id);

    List<BottleTypeDTO> findByAlcType(String alcType);

    List<BottleTypeDTO> findByPower(int power);

    List<BottleTypeDTO> findByVolume(int volume);

    void insertBottleType(BottleTypeDTO bottleType);

    void updateBottleType(BottleTypeDTO bottleType);

    void deleteBottleType(BottleTypeDTO bottleType);
}
