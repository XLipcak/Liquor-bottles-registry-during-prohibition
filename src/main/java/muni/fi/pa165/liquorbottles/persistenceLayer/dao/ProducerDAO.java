package muni.fi.pa165.liquorbottles.persistenceLayer.dao;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public interface ProducerDAO {
    
    List<Producer> findAll();
    Producer findById(long id);
    Producer findByUsername(String userName);
    Producer findByName(String userName);
    Producer findAddress(String userName);
   
    void insertProducer(Producer producer);
    void updateProducer(Producer producer);
    void deleteProducer(Producer producer);
    
}
