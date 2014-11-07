package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class UserDAOImpl implements UserDAO {

    EntityManagerFactory emf;

    public UserDAOImpl() {

    }

    public UserDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<User> findAll() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            TypedQuery<User> allUsersQuery = em.createQuery("SELECT u FROM User u", User.class);
            List<User> allUsers = allUsersQuery.getResultList();
            em.getTransaction().commit();

            return allUsers;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public User findById(long id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            em.getTransaction().commit();

            return user;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public User findByUsername(String userName) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            TypedQuery<User> userByUserNameQuery = em.createQuery("SELECT u FROM User u "
                    + "WHERE u.username='" + userName + "'", User.class);
            User user = userByUserNameQuery.getSingleResult();
            em.getTransaction().commit();

            return user;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public String findPassByUsername(String userName) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            TypedQuery<String> passByNameQuery = em.createQuery("SELECT u.password FROM User u "
                    + "WHERE u.username='" + userName + "'", String.class);
            String password = passByNameQuery.getSingleResult();
            em.getTransaction().commit();

            return password;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void insertUser(User user) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
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
    public void deleteUser(User user) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(em.contains(user) ? user : em.merge(user));
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
    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Required
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

}
