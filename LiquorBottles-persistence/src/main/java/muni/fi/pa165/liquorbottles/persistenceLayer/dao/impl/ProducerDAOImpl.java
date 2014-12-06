package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class ProducerDAOImpl implements ProducerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BottleDAOImpl.class);

    @PersistenceContext
    EntityManager em;

    UserDAO userDAO;

    public ProducerDAOImpl() {
    }

    public ProducerDAOImpl(EntityManager em) {
        this.em = em;
        userDAO = new UserDAOImpl(em);
    }

    @Override
    public List<Producer> findAll() {
        LOGGER.info("Finding all Producers.");

        try {
            TypedQuery<Producer> allProducerQuery = em.createQuery("SELECT p FROM Producer p", Producer.class);
            List<Producer> allProducer = allProducerQuery.getResultList();

            return allProducer;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Producer findById(long id) {
        LOGGER.info("Finding Producer by ID.");

        try {
            Producer producer = em.find(Producer.class, id);

            return producer;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Producer findByUsername(String userName) {
        LOGGER.info("Finding Producer by username.");

        try {
            TypedQuery<Producer> producerByUserNameQuery = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.username= :username", Producer.class);
            producerByUserNameQuery.setParameter("username", userName);
            Producer producer = producerByUserNameQuery.getSingleResult();

            return producer;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Producer findByName(String name) {
        LOGGER.info("Finding Producer by name.");

        try {
            TypedQuery<Producer> producerByNameQuery = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.name= :name", Producer.class);
            producerByNameQuery.setParameter("name", name);
            Producer producer = producerByNameQuery.getSingleResult();

            return producer;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Producer findByAddress(String address) {
        LOGGER.info("Finding Producer by address.");

        try {
            TypedQuery<Producer> producerByAddress = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.address= :address", Producer.class);
            producerByAddress.setParameter("address", address);
            Producer producer = producerByAddress.getSingleResult();

            return producer;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Producer> findByFilter(String name, String address) {
        LOGGER.info("Finding Store by filter.");
        try {
            TypedQuery<Producer> producerByFilterQuerry;

            String query = "SELECT p FROM Producer p WHERE ";
            if (!name.equals("")) {
                query += "p.name LIKE :name AND ";
            }
            if (!address.equals("")) {
                query += "p.address LIKE :address AND ";
            }

            query += "1=1";

            producerByFilterQuerry = em.createQuery(query, Producer.class);

            if (!name.equals("")) {
                producerByFilterQuerry.setParameter("name", '%' + name + '%');
            }
            if (!address.equals("")) {
                producerByFilterQuerry.setParameter("address", '%' + address + '%');
            }

            List<Producer> filterProducer = producerByFilterQuerry.getResultList();

            return filterProducer;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void insertProducer(Producer producer) {
        userDAO.insertUser(producer);
    }

    @Override
    public void updateProducer(Producer producer) {
        userDAO.updateUser(producer);
    }

    @Override
    public void deleteProducer(Producer producer) {
        userDAO.deleteUser(producer);
    }

    @Required
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Required
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
