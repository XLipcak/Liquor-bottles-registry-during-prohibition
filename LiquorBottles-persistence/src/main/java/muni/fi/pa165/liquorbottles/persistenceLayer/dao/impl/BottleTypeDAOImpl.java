package muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class BottleTypeDAOImpl implements BottleTypeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BottleDAOImpl.class);

    @PersistenceContext
    EntityManager em;

    public BottleTypeDAOImpl() {

    }

    public BottleTypeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<BottleType> findAll() {
        LOGGER.info("Finding all Bottle types.");

        try {
            TypedQuery<BottleType> allBottleTypeQuerry = em.createQuery("SELECT b FROM BottleType b", BottleType.class);
            List<BottleType> allBottle = allBottleTypeQuerry.getResultList();

            return allBottle;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }

    }

    @Override
    public BottleType findById(long id) {
        LOGGER.info("Finding Bottle type by ID.");

        try {
            BottleType bottleType = em.find(BottleType.class, id);

            return bottleType;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<BottleType> findByAlcType(String alcType) {
        LOGGER.info("Finding Bottle types by AlcType.");

        try {
            TypedQuery<BottleType> bottleTypeByAlcTypeQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.alcType= :alcType", BottleType.class);
            bottleTypeByAlcTypeQuerry.setParameter("alcType", alcType);
            List<BottleType> alcTypeBottle = bottleTypeByAlcTypeQuerry.getResultList();

            return alcTypeBottle;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<BottleType> findByPower(int power) {
        LOGGER.info("Finding Bottle types by power.");

        try {
            TypedQuery<BottleType> bottleTypeByPowerQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.power= :power", BottleType.class);
            bottleTypeByPowerQuerry.setParameter("power", power);
            List<BottleType> powerBottle = bottleTypeByPowerQuerry.getResultList();

            return powerBottle;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<BottleType> findByVolume(int volume) {
        LOGGER.info("Finding Bottle types by volume.");

        try {
            TypedQuery<BottleType> bottleTypeByVolumeQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.volume= :volume", BottleType.class);
            bottleTypeByVolumeQuerry.setParameter("volume", volume);
            List<BottleType> volumeBottle = bottleTypeByVolumeQuerry.getResultList();

            return volumeBottle;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<BottleType> findByProducer(long producerID) {
        LOGGER.info("Finding Bottle types by producer.");

        try {
            TypedQuery<BottleType> bottleTypeByProducerQuerry = em.createQuery("SELECT b FROM BottleType b "
                    + "WHERE b.producer.id= :producerID", BottleType.class);
            bottleTypeByProducerQuerry.setParameter("producerID", producerID);
            List<BottleType> producerBottle = bottleTypeByProducerQuerry.getResultList();

            return producerBottle;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<BottleType> findByFilter(long producerID, String name, String alcType, int power, int volume) {
        LOGGER.info("Finding bottle by filter.");
        try {
            TypedQuery<BottleType> bottleTypeByFilterQuerry;

            String query = "SELECT bt FROM BottleType bt WHERE ";

            if (producerID != -1) {
                query += "bt.producer.id= :producerID AND ";
            }
            if (!name.equals("")) {
                query += "bt.name= :name AND ";
            }
            if (!alcType.equals("")) {
                query += "bt.alcType= :alcType AND ";
            }
            if (power != -1) {
                query += "bt.power= :power AND ";
            }
            if (volume != -1) {
                query += "bt.volume= :volume AND ";
            }
            // osetrenie po AND + prazdnych argumentov
            query += "1=1";

            // naviazanie stringu 
            bottleTypeByFilterQuerry = em.createQuery(query, BottleType.class);

            // nastavenie parametrov
            if (producerID != -1) {
                bottleTypeByFilterQuerry.setParameter("producerID", producerID);
            }
            if (!name.equals("")) {
                bottleTypeByFilterQuerry.setParameter("name", name);
            }
            if (!alcType.equals("")) {
                bottleTypeByFilterQuerry.setParameter("alcType", alcType);
            }
            if (power != -1) {
                bottleTypeByFilterQuerry.setParameter("power", power);
            }
            if (volume != -1) {
                bottleTypeByFilterQuerry.setParameter("volume", volume);
            }

            List<BottleType> filterBottleType = bottleTypeByFilterQuerry.getResultList();

            return filterBottleType;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void insertBottleType(BottleType bottleType) {
        LOGGER.info("Inserting Bottle type.");

        try {
            em.persist(bottleType);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void updateBottleType(BottleType bottleType) {
        LOGGER.info("Updating Bottle type.");

        try {
            em.merge(bottleType);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new PersistenceException("Transaction failed. \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteBottleType(BottleType bottleType) {
        LOGGER.info("Deleting Bottle type.");

        try {
            em.remove(em.contains(bottleType) ? bottleType : em.merge(bottleType));

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
