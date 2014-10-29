package muni.fi.pa165.liquorbottles.serviceLayer.services.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.PoliceDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.PoliceDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.PoliceDTO;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor.DozerPoliceDTOConvertor;
import muni.fi.pa165.liquorbottles.serviceLayer.services.PoliceService;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class PoliceServiceImpl implements PoliceService {

    EntityManagerFactory emf;
    PoliceDAO policeDAOImpl;
    DozerPoliceDTOConvertor dozerPoliceDTOConvertor = new DozerPoliceDTOConvertor();

    public PoliceServiceImpl() {
        emf = Persistence.createEntityManagerFactory(
                "localDB");
        policeDAOImpl = new PoliceDAOImpl(emf);
    }
    
    public void close(){
        emf.close();
    }

    @Override
    public List<PoliceDTO> findAll() {
        try {
            List<Police> police = policeDAOImpl.findAll();
            return dozerPoliceDTOConvertor.fromEntityToDTO(police);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();     //replace by service exception
        }
    }

    @Override
    public PoliceDTO findById(long id) {
        try {
            Police police = policeDAOImpl.findById(id);
            return dozerPoliceDTOConvertor.fromEntityToDTO(police);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();     //replace by service exception
        }
    }

    @Override
    public PoliceDTO findByUsername(String userName) {
        try {
            Police police = policeDAOImpl.findByUsername(userName);
            return dozerPoliceDTOConvertor.fromEntityToDTO(police);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();     //replace by service exception
        }
    }

    @Override
    public PoliceDTO findByName(String name) {
        try {
            Police police = policeDAOImpl.findByName(name);
            return dozerPoliceDTOConvertor.fromEntityToDTO(police);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();     //replace by service exception
        }
    }

    @Override
    public PoliceDTO findByAddress(String address) {
        try {
            Police police = policeDAOImpl.findByAddress(address);
            return dozerPoliceDTOConvertor.fromEntityToDTO(police);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();     //replace by service exception
        }
    }

    @Override
    public void insertPolice(PoliceDTO policeDTO) {
        try {
            Police police = dozerPoliceDTOConvertor.fromDTOToEntity(policeDTO);
            policeDAOImpl.insertPolice(police);
            policeDTO.setId(police.getId());
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();     //replace by service exception
        }
    }

    @Override
    public void updatePolice(PoliceDTO policeDTO) {
        try {
            Police police = dozerPoliceDTOConvertor.fromDTOToEntity(policeDTO);
            policeDAOImpl.updatePolice(police);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();     //replace by service exception
        }
    }

    @Override
    public void deletePolice(PoliceDTO policeDTO) {
        try {
            Police police = dozerPoliceDTOConvertor.fromDTOToEntity(policeDTO);
            policeDAOImpl.deletePolice(police);
        } catch (PersistenceException ex) {
            throw new IllegalMonitorStateException();     //replace by service exception
        }
    }

}
