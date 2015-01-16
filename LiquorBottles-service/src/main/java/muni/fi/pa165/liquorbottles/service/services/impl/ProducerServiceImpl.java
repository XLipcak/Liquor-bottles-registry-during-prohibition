package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DTOConvertor;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerProducerDTOConvertor;
import muni.fi.pa165.liquorbottles.api.services.ProducerService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

    private ProducerDAO producerDao;
    private BottleTypeDAO bottleTypeDao;
    private DTOConvertor<Producer, ProducerDTO> convertor = new DozerProducerDTOConvertor();

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<ProducerDTO> findAll() {
        try {
            List<Producer> allProducers = producerDao.findAll();
            return convertor.fromEntityToDTO(allProducers);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public ProducerDTO findById(long id) {
        try {
            Producer producerDto = producerDao.findById(id);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public ProducerDTO findByUsername(String userName) {
        try {
            Producer producerDto = producerDao.findByUsername(userName);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public ProducerDTO findByName(String name) {
        try {
            Producer producerDto = producerDao.findByName(name);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public ProducerDTO findByAddress(String address) {
        try {
            Producer producerDto = producerDao.findByAddress(address);
            return convertor.fromEntityToDTO(producerDto);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void insertProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.insertProducer(producer);
            producerDto.setId(producer.getId());
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void updateProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.updateProducer(producer);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void deleteProducer(ProducerDTO producerDto) {
        try {
            Producer producer = convertor.fromDTOToEntity(producerDto);
            producerDao.deleteProducer(producer);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<ProducerDTO> findByFilter(String name, String address) {
        try {
            List<Producer> producers = producerDao.findByFilter(name, address);
            return convertor.fromEntityToDTO(producers);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Required
    @Secured({"ROLE_ADMIN"})
    public void setProducerDao(ProducerDAO producerDao) {
        this.producerDao = producerDao;
    }

    @Required
    @Secured({"ROLE_ADMIN"})
    public void setBottleTypeDao(BottleTypeDAO bottleTypeDao) {
        this.bottleTypeDao = bottleTypeDao;
    }

    //@Required
    @Secured({"ROLE_ADMIN"})
    public void setConvertor(DTOConvertor<Producer, ProducerDTO> convertor) {
        this.convertor = convertor;
    }
    
}
