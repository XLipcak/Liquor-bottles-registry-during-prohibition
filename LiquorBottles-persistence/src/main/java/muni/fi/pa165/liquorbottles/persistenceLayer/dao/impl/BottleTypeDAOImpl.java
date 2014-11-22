package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class BottleTypeDAOImpl implements BottleTypeDAO {

    @PersistenceContext
    EntityManager em;

    public BottleTypeDAOImpl() {

    }

    public BottleTypeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<BottleType> findAll() {
        try {
            TypedQuery<BottleType> allBottleTypeQuerry = em.createQuery("SELECT b FROM BottleType b", BottleType.class);
            List<BottleType> allBottle = allBottleTypeQuerry.getResultList();

            return allBottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }

    }

    @Override
    public BottleType findById(long id) {
        try {
            BottleType bottleType = em.find(BottleType.class, id);

            return bottleType;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<BottleType> findByAlcType(String alcType) {
        try {
            TypedQuery<BottleType> bottleTypeByAlcTypeQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.alcType= :alcType", BottleType.class);
            bottleTypeByAlcTypeQuerry.setParameter("alcType", alcType);
            List<BottleType> alcTypeBottle = bottleTypeByAlcTypeQuerry.getResultList();

            return alcTypeBottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<BottleType> findByPower(int power) {
        try {
            TypedQuery<BottleType> bottleTypeByPowerQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.power= :power", BottleType.class);
            bottleTypeByPowerQuerry.setParameter("power", power);
            List<BottleType> powerBottle = bottleTypeByPowerQuerry.getResultList();

            return powerBottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<BottleType> findByVolume(int volume) {
        try {
            TypedQuery<BottleType> bottleTypeByVolumeQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.volume= :volume", BottleType.class);
            bottleTypeByVolumeQuerry.setParameter("volume", volume);
            List<BottleType> volumeBottle = bottleTypeByVolumeQuerry.getResultList();

            return volumeBottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void insertBottleType(BottleType bottleType) {

        try {
            em.persist(bottleType);

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void updateBottleType(BottleType bottleType) {

        try {
            em.merge(bottleType);

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteBottleType(BottleType bottleType) {
        try {
            em.remove(em.contains(bottleType) ? bottleType : em.merge(bottleType));

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Required
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
