package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.classes.Toxicity;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;

/**
 * Implementation of BottleDAO executing basic operations described in BottleDAO 
 * for returning desired Bottle classes from database 
 * and also the CRUD operations to the database.
 * @author Michal Štora, Masaryk University
 */
public class BottleDAOImpl implements BottleDAO{
    
    EntityManagerFactory emf;
    
    public BottleDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }
    
    /**
     * This method returns all Bottles from database as a List
     * @return List containing all Bottles from database
     */
    @Override
    public List<Bottle> findAll() {
        EntityManager em = emf.createEntityManager();
        
        try{
             em.getTransaction().begin();
             TypedQuery<Bottle> allBottleQuerry = em.createQuery("SELECT b FROM Bottle b", Bottle.class);
             List<Bottle> allBottle = allBottleQuerry.getResultList();
             em.getTransaction().commit();
             
             return allBottle;
        }
        catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }

    /**
     * This method returns the Bottle from database specified by id parameter
     * @param id id of Bottle in the database
     * @return Bottle with id as parameter
     */
    @Override
    public Bottle findById(long id) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            Bottle bottle = em.find(Bottle.class, id);
            em.getTransaction().commit();
            
            return bottle;
        }catch(Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }

    /**
     * This method returns the Bottle from database specified by stamp parameter
     * @param stamp of BottleType in the database
     * @return Bottle with stamp as parameter
     */
    @Override
    public Bottle findByStamp(long stamp) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            TypedQuery<Bottle> bottleByStampQuerry;
            bottleByStampQuerry = em.createQuery("SELECT b FROM Bottle b WHERE b.stamp= :stamp "  , Bottle.class);
            bottleByStampQuerry.setParameter("stamp", stamp);
            Bottle bottle = bottleByStampQuerry.getSingleResult();
            em.getTransaction().commit();
            
            return bottle;
        }catch(Exception ex){ 
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }
       
    /**
     * This method returns all Bottles from database specified by date parameter
     * @param date of Bottle in the database
     * @return List containing all Bottles with date as parameter from database
     */
    @Override
    public List<Bottle> findByDate(Date date) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Bottle> bottleByDateQuerry = em.createQuery("SELECT b FROM Bottle b "
                    + "WHERE b.dateOfBirth= :date", Bottle.class); 
            bottleByDateQuerry.setParameter("date", date);
            List<Bottle> dateBottle = bottleByDateQuerry.getResultList();
            em.getTransaction().commit();
            
            return dateBottle;
        }catch(Exception ex){ 
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }
    
    /**
     * This method returns all Bottles from database specified by toxicity parameter
     * @param toxic of Bottle in the database
     * @return List containing all Bottles with toxic as parameter from database
     */
    @Override
    public List<Bottle> findByToxicity(Toxicity toxic) {
       EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Bottle> bottleByToxicityQuerry;
            bottleByToxicityQuerry = em.createQuery("SELECT b FROM Bottle b WHERE b.toxicity= :toxic" , Bottle.class);
            bottleByToxicityQuerry.setParameter("toxic", toxic);
            List<Bottle> toxicityBottle = bottleByToxicityQuerry.getResultList();
            em.getTransaction().commit();
            
            return toxicityBottle;
        }catch(Exception ex){ 
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }

    /**
     * This method inserts the Bottle as parameter to the database
     * @param bottle  to be inserted to the database
     */
    @Override
    public void insertBottle(Bottle bottle) {
          EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(bottle);
            em.getTransaction().commit();

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * This method updates the Bottle as parameter to the database
     * @param bottle to be updated in the database
     */
    @Override
    public void updateBottle(Bottle bottle) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(bottle);
            em.getTransaction().commit();

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * This method deletes the Bottle as parameter from the database
     * @param bottle to be deleted from the database
     */
    @Override
    public void deleteBottle(Bottle bottle) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(bottle) ? bottle : em.merge(bottle));
            em.getTransaction().commit();

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * This method returns all Bottles from database specified by batchId parameter
     * @param id of Bottle in the database
     * @return List containing all Bottles with batchNumber as parameter from database
     */
    @Override
    public List<Bottle> findByBatchId(long id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Bottle> bottleByBatchIDQuerry = em.createQuery("SELECT b FROM Bottle b "
                    + "WHERE b.batchNumber=:id", Bottle.class);
            bottleByBatchIDQuerry.setParameter("id", id);
            List<Bottle> batchIdBottle = bottleByBatchIDQuerry.getResultList();
            em.getTransaction().commit();
            
            return batchIdBottle;
        }catch(Exception ex){ 
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }
}
