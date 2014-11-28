package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.PoliceDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class PoliceDAOImpl implements PoliceDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BottleDAOImpl.class);

    @PersistenceContext
    EntityManager em;

    UserDAO userDAO;

    public PoliceDAOImpl() {
    }

    public PoliceDAOImpl(EntityManager em) {
        this.em = em;
        userDAO = new UserDAOImpl(em);
    }

    @Override
    public List<Police> findAll() {
        LOGGER.info("Finding all Police.");

        try {
            TypedQuery<Police> allPoliceQuery = em.createQuery("SELECT p FROM Police p", Police.class);
            List<Police> allPolice = allPoliceQuery.getResultList();

            return allPolice;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Police findById(long id) {
        LOGGER.info("Finding Police by ID.");

        try {
            Police police = em.find(Police.class, id);

            return police;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Police findByUsername(String userName) {
        LOGGER.info("Finding Police by username.");

        try {
            TypedQuery<Police> policeByUserNameQuery = em.createQuery("SELECT p FROM Police p "
                    + "WHERE p.username='" + userName + "'", Police.class);
            Police police = policeByUserNameQuery.getSingleResult();

            return police;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Police findByName(String name) {
        LOGGER.info("Finding Police by name.");

        try {
            TypedQuery<Police> policeByNameQuery = em.createQuery("SELECT p FROM Police p "
                    + "WHERE p.name='" + name + "'", Police.class);
            Police police = policeByNameQuery.getSingleResult();

            return police;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Police findByAddress(String address) {
        LOGGER.info("Finding Police by address.");

        try {
            TypedQuery<Police> policeByAddress = em.createQuery("SELECT p FROM Police p "
                    + "WHERE p.address='" + address + "'", Police.class);
            Police police = policeByAddress.getSingleResult();

            return police;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Police> findByFilter(String name, String address) {
        LOGGER.info("Finding Store by filter.");
        try {
            TypedQuery<Police> policeByFilterQuerry;

            String query = "SELECT p FROM Police p WHERE ";
            // -1 may need to change
            // String Query lepenie CIASTOCNA ZHODA
            if (!name.equals("")) {
                query += "p.name LIKE :name AND ";
            }
            if (!address.equals("")) {
                query += "p.address LIKE :address AND ";
            }

            // osetrenie po AND + prazdnych argumentov
            query += "1=1";

            // naviazanie stringu 
            policeByFilterQuerry = em.createQuery(query, Police.class);

            // -1 may need to change
            // nastavenie parametrov
            // % je CIASTOCNA ZHODA
            if (!name.equals("")) {
                policeByFilterQuerry.setParameter("name", '%'+ name +'%');
            }
            if (!address.equals("")) {
                policeByFilterQuerry.setParameter("address", '%'+ address +'%');
            }

            List<Police> filterPolice = policeByFilterQuerry.getResultList();

            return filterPolice;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void insertPolice(Police police) {
        userDAO.insertUser(police);
    }

    @Override
    public void updatePolice(Police police) {
        userDAO.updateUser(police);
    }

    @Override
    public void deletePolice(Police police) {
        userDAO.deleteUser(police);
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
