package muni.fi.pa165.liquorbottles.serviceLayer.services.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor.DTOConvertor;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor.DozerProducerDTOConvertor;
import muni.fi.pa165.liquorbottles.serviceLayer.services.ProducerService;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class ProducerServiceImpl implements ProducerService {

    //replace by Spring injection later
    private ProducerDAO producerDao;
    private DTOConvertor<Producer, ProducerDTO> convertor = new DozerProducerDTOConvertor();

    public ProducerServiceImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "localDB");
        producerDao = new ProducerDAOImpl(emf);
    }

    @Override
    public List<ProducerDTO> findAll() {
        try {
            List<Producer> allProducers = producerDao.findAll();
            return convertor.fromEntityToDTO(allProducers);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public ProducerDTO findById(long id) {
        try {
            Producer producerDto = producerDao.findById(id);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public ProducerDTO findByUsername(String userName) {
        try {
            Producer producerDto = producerDao.findByUsername(userName);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public ProducerDTO findByName(String name) {
        try {
            Producer producerDto = producerDao.findByName(name);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public ProducerDTO findByAddress(String address) {
        try {
            Producer producerDto = producerDao.findByAddress(address);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public void insertProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.insertProducer(producer);
            producerDto.setId(producer.getId());
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public void updateProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.updateProducer(producer);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

    @Override
    public void deleteProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.deleteProducer(producer);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();//replace by service exception
        }
    }

}
