package muni.fi.pa165.liquorbottles.service.services;

import java.util.List;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;

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

    void insertProducer(ProducerDTO producerDto);

    void updateProducer(ProducerDTO producerDto);

    void deleteProducer(ProducerDTO producerDto);
}
