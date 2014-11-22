package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class BottleDAOImpl implements BottleDAO {

    @PersistenceContext
    EntityManager em;

    public BottleDAOImpl() {

    }

    public BottleDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Bottle> findAll() {

        try {
            TypedQuery<Bottle> allBottleQuerry = em.createQuery("SELECT b FROM Bottle b", Bottle.class);
            List<Bottle> allBottle = allBottleQuerry.getResultList();

            return allBottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Bottle findById(long id) {

        try {
            Bottle bottle = em.find(Bottle.class, id);

            return bottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public Bottle findByStamp(long stamp) {

        try {
            TypedQuery<Bottle> bottleByStampQuerry;
            bottleByStampQuerry = em.createQuery("SELECT b FROM Bottle b WHERE b.stamp= :stamp ", Bottle.class);
            bottleByStampQuerry.setParameter("stamp", stamp);
            Bottle bottle = bottleByStampQuerry.getSingleResult();

            return bottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Bottle> findByDate(Date date) {
        try {
            TypedQuery<Bottle> bottleByDateQuerry = em.createQuery("SELECT b FROM Bottle b "
                    + "WHERE b.dateOfBirth= :date", Bottle.class);
            bottleByDateQuerry.setParameter("date", date);
            List<Bottle> dateBottle = bottleByDateQuerry.getResultList();

            return dateBottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Bottle> findByToxicity(Toxicity toxic) {
        try {
            TypedQuery<Bottle> bottleByToxicityQuerry;
            bottleByToxicityQuerry = em.createQuery("SELECT b FROM Bottle b WHERE b.toxicity= :toxic", Bottle.class);
            bottleByToxicityQuerry.setParameter("toxic", toxic);
            List<Bottle> toxicityBottle = bottleByToxicityQuerry.getResultList();

            return toxicityBottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void insertBottle(Bottle bottle) {

        try {
            em.persist(bottle);

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void updateBottle(Bottle bottle) {

        try {
            em.merge(bottle);

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteBottle(Bottle bottle) {
        try {
            em.remove(em.contains(bottle) ? bottle : em.merge(bottle));

        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Bottle> findByBatchId(long id) {
        try {
            TypedQuery<Bottle> bottleByBatchIDQuerry = em.createQuery("SELECT b FROM Bottle b "
                    + "WHERE b.batchNumber=:id", Bottle.class);
            bottleByBatchIDQuerry.setParameter("id", id);
            List<Bottle> batchIdBottle = bottleByBatchIDQuerry.getResultList();

            return batchIdBottle;
        } catch (Exception ex) {
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Required
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
