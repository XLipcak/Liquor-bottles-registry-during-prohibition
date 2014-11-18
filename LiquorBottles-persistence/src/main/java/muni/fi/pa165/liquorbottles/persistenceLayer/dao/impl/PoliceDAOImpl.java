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
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class PoliceDAOImpl implements PoliceDAO {

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

        try {
            //em.getTransaction().begin();
            TypedQuery<Police> allPoliceQuery = em.createQuery("SELECT p FROM Police p", Police.class);
            List<Police> allPolice = allPoliceQuery.getResultList();
            //em.getTransaction().commit();

            return allPolice;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
        }
    }

    @Override
    public Police findById(long id) {

        try {
            //em.getTransaction().begin();
            Police police = em.find(Police.class, id);
            //em.getTransaction().commit();

            return police;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
        }
    }

    @Override
    public Police findByUsername(String userName) {

        try {
            //em.getTransaction().begin();
            TypedQuery<Police> policeByUserNameQuery = em.createQuery("SELECT p FROM Police p "
                    + "WHERE p.username='" + userName + "'", Police.class);
            Police police = policeByUserNameQuery.getSingleResult();
            //em.getTransaction().commit();

            return police;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
        }
    }

    @Override
    public Police findByName(String name) {

        try {
            //em.getTransaction().begin();
            TypedQuery<Police> policeByNameQuery = em.createQuery("SELECT p FROM Police p "
                    + "WHERE p.name='" + name + "'", Police.class);
            Police police = policeByNameQuery.getSingleResult();
            //em.getTransaction().commit();

            return police;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
        }
    }

    @Override
    public Police findByAddress(String address) {

        try {
            //em.getTransaction().begin();
            TypedQuery<Police> policeByAddress = em.createQuery("SELECT p FROM Police p "
                    + "WHERE p.address='" + address + "'", Police.class);
            Police police = policeByAddress.getSingleResult();
            //em.getTransaction().commit();

            return police;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                // //em.close();
            }
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
