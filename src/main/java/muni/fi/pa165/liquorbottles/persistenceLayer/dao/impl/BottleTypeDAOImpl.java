package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;

/**
 * Implementation of BottleTypeDAO executing basic operations described in BottleTypeDAO 
 * for returning desired BottleTypes classes from database 
 * and also the CRUD operations to the database.
 * @author Michal Štora, Masaryk University
 */
public class BottleTypeDAOImpl implements BottleTypeDAO{

    EntityManagerFactory emf;
    
    public BottleTypeDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }
    
    /**
     * This method returns all BottleTypes from database as a List
     * @return List containing all BottleTypes from database
     */
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

    /**
     * This method returns the BottleType from database specified by id parameter
     * @param id id of BottleType in the database
     * @return BottleType with id as parameter
     */
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
    
    /**
     * This method returns all BottleTypes from database specified by alcType parameter
     * @param alcType of BottleType in the database
     * @return List containing all BottleTypes from database
     */
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

    /**
     * This method returns all BottleTypes from database specified by power parameter
     * @param power  of BottleType in the database
     * @return List containing all BottleTypes from database
     */
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
    
    /**
     * This method returns all BottleTypes from database specified by volume parameter
     * @param volume  of BottleType in the database
     * @return List containing all BottleTypes from database
     */
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

    /**
     * This method inserts the BottleType as parameter to the database
     * @param bottleType  to be inserted to the database
     */
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
   
    /**
     * This method updates the BottleType as parameter to the database
     * @param bottleType to be updated in the database
     */
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

     /**
     * This method deletes the BottleType as parameter from the database
     * @param bottleType to be deleted from the database
     */
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
