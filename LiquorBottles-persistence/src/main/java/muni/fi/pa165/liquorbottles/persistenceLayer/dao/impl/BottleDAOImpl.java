package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;

/**
 * 
 * @author Michal Štora, Masaryk University
 */
public class BottleDAOImpl implements BottleDAO{
    
    EntityManagerFactory emf;
    
    public BottleDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }
    
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