package muni.fi.pa165.liquorbottles.serviceLayer.services;

import java.util.List;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.ProducerDTO;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public interface ProducerService {

    List<ProducerDTO> findAll();

    ProducerDTO findById(long id);

    ProducerDTO findByUsername(String userName);

    ProducerDTO findByName(String name);

    ProducerDTO findByAddress(String address);

    void insertProducer(ProducerDTO producer);

    void updateProducer(ProducerDTO producer);

    void deleteProducer(ProducerDTO producer);
}
