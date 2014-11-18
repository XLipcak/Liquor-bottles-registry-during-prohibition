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
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class ProducerDAOImpl implements ProducerDAO {

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

        try {
            //em.getTransaction().begin();
            TypedQuery<Producer> allProducerQuery = em.createQuery("SELECT p FROM Producer p", Producer.class);
            List<Producer> allProducer = allProducerQuery.getResultList();
            //em.getTransaction().commit();

            return allProducer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
        }
    }

    @Override
    public Producer findById(long id) {

        try {
            //em.getTransaction().begin();
            Producer producer = em.find(Producer.class, id);
            //em.getTransaction().commit();

            return producer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
        }
    }

    @Override
    public Producer findByUsername(String userName) {

        try {
            //em.getTransaction().begin();
            TypedQuery<Producer> producerByUserNameQuery = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.username= :username", Producer.class);
            producerByUserNameQuery.setParameter("username", userName);
            Producer producer = producerByUserNameQuery.getSingleResult();
            //em.getTransaction().commit();

            return producer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
        }
    }

    @Override
    public Producer findByName(String name) {

        try {
            //em.getTransaction().begin();
            TypedQuery<Producer> producerByNameQuery = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.name= :name", Producer.class);
            producerByNameQuery.setParameter("name", name);
            Producer producer = producerByNameQuery.getSingleResult();
            //em.getTransaction().commit();

            return producer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
        }
    }

    @Override
    public Producer findByAddress(String address) {

        try {
            //em.getTransaction().begin();
            TypedQuery<Producer> producerByAddress = em.createQuery("SELECT p FROM Producer p "
                    + "WHERE p.address= :address", Producer.class);
            producerByAddress.setParameter("address", address);
            Producer producer = producerByAddress.getSingleResult();
            //em.getTransaction().commit();

            return producer;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
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
