package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.StoreDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;

/**
 *
 * @author Matúš Novák, Masaryk University
 */
public class StoreDAOImpl implements StoreDAO {

    EntityManagerFactory emf;

    public StoreDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Store> findAll() {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            TypedQuery<Store> allStoreQuerry = em.createQuery("SELECT s FROM Store s", Store.class);
            List<Store> allStore = allStoreQuerry.getResultList();
            em.getTransaction().commit();

            return allStore;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Store findById(long id) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Store store = em.find(Store.class, id);
            em.getTransaction().commit();

            return store;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Store findByAddress(String address) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            TypedQuery<Store> policeByAddress = em.createQuery("SELECT s FROM Store s "
                    + "WHERE s.address='" + address + "'", Store.class);
            Store store = policeByAddress.getSingleResult();
            em.getTransaction().commit();

            return store;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void insertStore(Store store) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(store);
            em.getTransaction().commit();

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void updateStore(Store store) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(store);
            em.getTransaction().commit();

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void deleteStore(Store store) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(em.contains(store) ? store : em.merge(store));
            em.getTransaction().commit();

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
