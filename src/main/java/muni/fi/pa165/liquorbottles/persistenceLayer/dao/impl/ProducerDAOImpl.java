package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;

/**
 * Implementation of ProducerDAO executing basic operations described in ProducerDAO 
 * for returning desired Producer classes from database 
 * and also the CRUD operations to the database.
 * @author Michal Štora, Masaryk University
 */
public class ProducerDAOImpl implements ProducerDAO{

    EntityManagerFactory emf;
    UserDAO userDAO;

    public ProducerDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
        userDAO = new UserDAOImpl(emf);
    }
    
    /**
     * This method returns all Producer from database as a List
     * @return List containing all Producers from database
     */
    @Override
    public List<Producer> findAll() {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            TypedQuery<Producer> allProducerQuery = em.createQuery("SELECT p FROM Producer p", Producer.class);
            List<Producer> allProducer = allProducerQuery.getResultList();
            em.getTransaction().commit();

            return allProducer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * This method returns the Producer from database specified by id parameter
     * @param id id of Producer in the database
     * @return Producer with id as parameter
     */
    @Override
    public Producer findById(long id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            Producer producer = em.find(Producer.class, id);
            em.getTransaction().commit();

            return producer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * This method returns the Producer from database specified by userName parameter
     * @param userName userName of Producer in the database
     * @return Producer with userName as parameter
     */
    @Override
    public Producer findByUsername(String userName) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            TypedQuery<Producer> producerByUserNameQuery = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.username= :username", Producer.class);
            producerByUserNameQuery.setParameter("username", userName);
            Producer producer = producerByUserNameQuery.getSingleResult();
            em.getTransaction().commit();

            return producer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * This method returns the Producer from database specified by name parameter
     * @param name name of Producer in the database
     * @return Producer with name as parameter
     */
    @Override
    public Producer findByName(String name) {
         EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            TypedQuery<Producer> producerByNameQuery = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.name= :name", Producer.class);
            producerByNameQuery.setParameter("name", name);
            Producer producer = producerByNameQuery.getSingleResult();
            em.getTransaction().commit();

            return producer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

     /**
     * This method returns the Producer from database specified by address parameter
     * @param address address of Producer in the database
     * @return Producer with address as parameter
     */
    @Override
    public Producer findByAddress(String address) {
         EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            TypedQuery<Producer> producerByAddress = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.address= :address", Producer.class);
            producerByAddress.setParameter("address", address);
            Producer producer = producerByAddress.getSingleResult();
            em.getTransaction().commit();

            return producer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * This method inserts the Producer as parameter to the database
     * @param producer to be inserted to the database
     */
    @Override
    public void insertProducer(Producer producer) {
        userDAO.insertUser(producer);
    }

    /**
     * This method updates the Producer as parameter to the database
     * @param producer to be updated in the database
     */
    @Override
    public void updateProducer(Producer producer) {
        userDAO.updateUser(producer);
    }

     /**
     * This method deletes the Producer as parameter from the database
     * @param producer to be deleted from the database
     */
    @Override
    public void deleteProducer(Producer producer) {
        userDAO.deleteUser(producer);
    }
    
}
