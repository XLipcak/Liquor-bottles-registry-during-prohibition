package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.StoreDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author MatÃºÅ¡ NovÃ¡k, Masaryk University
 */
public class StoreDAOImpl implements StoreDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BottleDAOImpl.class);

    @PersistenceContext
    EntityManager em;

    UserDAO userDAO;

    public StoreDAOImpl() {
    }

    public StoreDAOImpl(EntityManager em) {
        this.em = em;
        userDAO = new UserDAOImpl(em);
    }

    @Override
    public List<Store> findAll() {
        LOGGER.info("Finding all Stores.");

        try {
            TypedQuery<Store> allStoreQuerry = em.createQuery("SELECT s FROM Store s", Store.class);
            List<Store> allStore = allStoreQuerry.getResultList();

            return allStore;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Store findById(long id) {
        LOGGER.info("Finding Store by ID.");

        try {
            Store store = em.find(Store.class, id);

            return store;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Store findByAddress(String address) {
        LOGGER.info("Finding Store by address.");

        try {
            TypedQuery<Store> policeByAddress = em.createQuery("SELECT s FROM Store s "
                    + "WHERE s.address='" + address + "'", Store.class);
            Store store = policeByAddress.getSingleResult();

            return store;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Store> findByFilter(String name, String address) {
        LOGGER.info("Finding Store by filter.");
        try {
            TypedQuery<Store> storeByFilterQuerry;

            String query = "SELECT s FROM Store s WHERE ";
            // -1 may need to change
            // String Query lepenie CIASTOCNA ZHODA
            if (!name.equals("")) {
                query += "s.name LIKE :name AND ";
            }
            if (!address.equals("")) {
                query += "s.address LIKE :address AND ";
            }

            // osetrenie po AND + prazdnych argumentov
            query += "1=1";

            // naviazanie stringu 
            storeByFilterQuerry = em.createQuery(query, Store.class);

            // -1 may need to change
            // nastavenie parametrov
            // % je CIASTOCNA ZHODA
            if (!name.equals("")) {
                storeByFilterQuerry.setParameter("name", '%'+ name +'%');
            }
            if (!address.equals("")) {
                storeByFilterQuerry.setParameter("address", '%'+ address +'%');
            }

            List<Store> filterStore = storeByFilterQuerry.getResultList();

            return filterStore;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void insertStore(Store store) {
        store.setAuthority("ROLE_STORE");
        userDAO.insertUser(store);
    }

    @Override
    public void updateStore(Store store) {
        userDAO.updateUser(store);
    }

    @Override
    public void deleteStore(Store store) {
        userDAO.deleteUser(store);
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
