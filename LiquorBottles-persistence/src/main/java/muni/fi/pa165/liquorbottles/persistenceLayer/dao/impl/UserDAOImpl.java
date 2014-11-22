package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.StaticMetamodel;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class UserDAOImpl implements UserDAO {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BottleDAOImpl.class);

    @PersistenceContext
    EntityManager em;

    public UserDAOImpl() {
    }

    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Finding all users.");

        try {
            TypedQuery<User> allUsersQuery = em.createQuery("SELECT u FROM User u", User.class);
            List<User> allUsers = allUsersQuery.getResultList();

            return allUsers;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public User findById(long id) {
        LOGGER.info("Finding User by ID.");

        try {
            User user = em.find(User.class, id);

            return user;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public User findByUsername(String userName) {
        LOGGER.info("Finding User by username.");

        try {
            TypedQuery<User> userByUserNameQuery = em.createQuery("SELECT u FROM User u "
                    + "WHERE u.username='" + userName + "'", User.class);
            User user = userByUserNameQuery.getSingleResult();

            return user;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public String findPassByUsername(String userName) {
        LOGGER.info("Finding password by username.");

        try {
            TypedQuery<String> passByNameQuery = em.createQuery("SELECT u.password FROM User u "
                    + "WHERE u.username='" + userName + "'", String.class);
            String password = passByNameQuery.getSingleResult();

            return password;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void insertUser(User user) {
        LOGGER.info("Inserting user.");

        try {
            em.persist(user);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteUser(User user) {
        LOGGER.info("Deleting user.");

        try {
            em.remove(em.contains(user) ? user : em.merge(user));

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void updateUser(User user) {
        LOGGER.info("Updating user.");

        try {
            em.merge(user);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Required
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
