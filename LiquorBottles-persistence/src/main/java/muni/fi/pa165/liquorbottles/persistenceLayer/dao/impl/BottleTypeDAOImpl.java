package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;

/**
 * 
 * @author Michal Štora, Masaryk University
 */
public class BottleTypeDAOImpl implements BottleTypeDAO{

    EntityManagerFactory emf;
    
    public BottleTypeDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }
   
    @Override
    public List<BottleType> findAll() {
        EntityManager em = emf.createEntityManager();
        try{
             em.getTransaction().begin();
             TypedQuery<BottleType> allBottleTypeQuerry = em.createQuery("SELECT b FROM BottleType b", BottleType.class);
             List<BottleType> allBottle = allBottleTypeQuerry.getResultList();
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
    public BottleType findById(long id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            BottleType bottleType = em.find(BottleType.class, id);
            em.getTransaction().commit();
            
            return bottleType;
        }catch(Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }
    
    @Override
    public List<BottleType> findByAlcType(String alcType) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<BottleType> bottleTypeByAlcTypeQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.alcType= :alcType", BottleType.class);
            bottleTypeByAlcTypeQuerry.setParameter("alcType", alcType);
            List<BottleType> alcTypeBottle = bottleTypeByAlcTypeQuerry.getResultList();
            em.getTransaction().commit();
            
            return alcTypeBottle;
        }catch(Exception ex){ 
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }

    @Override
    public List<BottleType> findByPower(int power) {
         EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<BottleType> bottleTypeByPowerQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.power= :power", BottleType.class);
            bottleTypeByPowerQuerry.setParameter("power", power);
            List<BottleType> powerBottle = bottleTypeByPowerQuerry.getResultList();
            em.getTransaction().commit();
            
            return powerBottle;
        }catch(Exception ex){ 
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }
    
    @Override
    public List<BottleType> findByVolume(int volume) { 
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<BottleType> bottleTypeByVolumeQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.volume= :volume", BottleType.class);
            bottleTypeByVolumeQuerry.setParameter("volume", volume);
            List<BottleType> volumeBottle = bottleTypeByVolumeQuerry.getResultList();
            em.getTransaction().commit();
            
            return volumeBottle;
        }catch(Exception ex){ 
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }finally{
            if (em != null){
                em.close();
            }       
        }
    }

    @Override
    public void insertBottleType(BottleType bottleType) {
         EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(bottleType);
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
    public void updateBottleType(BottleType bottleType) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(bottleType);
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
    public void deleteBottleType(BottleType bottleType) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(bottleType) ? bottleType : em.merge(bottleType));
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
