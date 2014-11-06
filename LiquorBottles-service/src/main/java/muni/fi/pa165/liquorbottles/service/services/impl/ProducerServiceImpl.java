package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DTOConvertor;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerProducerDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.ProducerService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

    private ProducerDAO producerDao;
    private DTOConvertor<Producer, ProducerDTO> convertor = new DozerProducerDTOConvertor();

    /*public ProducerServiceImpl() {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory(
     "localDB");
     producerDao = new ProducerDAOImpl(emf);
     }*/
    @Override
    public List<ProducerDTO> findAll() {
        try {
            List<Producer> allProducers = producerDao.findAll();
            return convertor.fromEntityToDTO(allProducers);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");
        }
    }

    @Override
    public ProducerDTO findById(long id) {
        try {
            Producer producerDto = producerDao.findById(id);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");
        }
    }

    @Override
    public ProducerDTO findByUsername(String userName) {
        try {
            Producer producerDto = producerDao.findByUsername(userName);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");
        }
    }

    @Override
    public ProducerDTO findByName(String name) {
        try {
            Producer producerDto = producerDao.findByName(name);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");
        }
    }

    @Override
    public ProducerDTO findByAddress(String address) {
        try {
            Producer producerDto = producerDao.findByAddress(address);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");
        }
    }

    @Override
    public void insertProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.insertProducer(producer);
            producerDto.setId(producer.getId());
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");
        }
    }

    @Override
    public void updateProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.updateProducer(producer);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");
        }
    }

    @Override
    public void deleteProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.deleteProducer(producer);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");
        }
    }

    @Required
    public void setProducerDao(ProducerDAO producerDao) {
        this.producerDao = producerDao;
    }

    //@Required
    public void setConvertor(DTOConvertor<Producer, ProducerDTO> convertor) {
        this.convertor = convertor;
    }

}
