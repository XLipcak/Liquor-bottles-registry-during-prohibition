package muni.fi.pa165.liquorbottles.serviceLayer.services;

import java.util.List;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.PoliceDTO;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public interface PoliceService {

    List<PoliceDTO> findAll();

    PoliceDTO findById(long id);

    PoliceDTO findByUsername(String userName);

    PoliceDTO findByName(String name);

    PoliceDTO findByAddress(String address);

    void insertPolice(PoliceDTO policeDTO);

    void updatePolice(PoliceDTO policeDTO);

    void deletePolice(PoliceDTO policeDTO);
}
