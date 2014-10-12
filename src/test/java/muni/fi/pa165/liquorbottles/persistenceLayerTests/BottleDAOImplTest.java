package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import muni.fi.pa165.liquorbottles.classes.DaoContext;
import muni.fi.pa165.liquorbottles.classes.Toxicity;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.StoreDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.BottleDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.BottleTypeDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.StoreDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BottleDAOImplTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private List<Bottle> bottlesInDb;

    public BottleDAOImplTest() {
        bottlesInDb = new ArrayList<>();
    }

    @BeforeMethod
    public void beforeMethod() {
        BottleDAO bottleDAO = new BottleDAOImpl(emf);
        StoreDAO storeDAO = new StoreDAOImpl(emf);
        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(emf);
        ProducerDAO producerDAO = new ProducerDAOImpl(emf);

        Store store = new Store("TestShop", "Alco1", "user123", "test");
        Producer producer = new Producer("TestProducer", "Vizovice", "user", "test");
        BottleType bottleType = new BottleType("TestBottleType", "Kalashnikov",
                55, 700, producer);

        Bottle bottle1 = new Bottle(store, bottleType, 123456, 001122,
                new Date(new Date().getTime()), Toxicity.TOXIC);
        Bottle bottle2 = new Bottle(store, bottleType, 11111, 445566,
                new Date(new Date().getTime()), Toxicity.UNCHECKED);
        Bottle bottle3 = new Bottle(store, bottleType, 0000, 9,
                new Date(new Date().getTime()), Toxicity.NON_TOXIC);

        storeDAO.insertStore(store);
        producerDAO.insertProducer(producer);
        bottleTypeDAO.insertBottleType(bottleType);

        bottleDAO.insertBottle(bottle1);
        bottleDAO.insertBottle(bottle2);
        bottleDAO.insertBottle(bottle3);

        bottlesInDb.add(bottle1);
        bottlesInDb.add(bottle2);
        bottlesInDb.add(bottle3);
    }

    @AfterMethod
    public void afterMethod() {
        bottlesInDb = new ArrayList<>();
    }

    /**
     * Test of findAll method, of class BottleDAOImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("Testing findAll.");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        List<Bottle> result = bottleDAO.findAll();

        int x = 0;
        for (Bottle b1 : result) {
            for (Bottle b2 : bottlesInDb) {
                if (b1.equals(b2)) {
                    x++;
                }
            }
        }
        assertEquals(x, bottlesInDb.size());
    }

    /**
     * Test of findById method, of class BottleDAOImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("Testing findById");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        for (int x = 0; x < bottlesInDb.size(); x++) {
            assertEquals(bottleDAO.findById(bottlesInDb.get(x).getId()),
                    bottlesInDb.get(x));
        }
    }

    /**
     * Test of findByStamp method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByStamp() {
        System.out.println("Testing findByStamp");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        for (int x = 0; x < bottlesInDb.size(); x++) {
            assertEquals(bottleDAO.findByStamp(bottlesInDb.get(x).getStamp()),
                    bottlesInDb.get(x));
        }
    }

    /**
     * Test of findByDate method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByDate() {
        System.out.println("Testing findByDate");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        assertEquals(bottleDAO.findByDate(new Date(new Date().getTime())),
                bottlesInDb);
    }

    /**
     * Test of findByToxicity method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByToxicity() {
        System.out.println("Testing findByToxicity");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        assertEquals(bottleDAO.findByToxicity(Toxicity.TOXIC).get(0),
                bottlesInDb.get(0));
        assertEquals(bottleDAO.findByToxicity(Toxicity.UNCHECKED).get(0),
                bottlesInDb.get(1));
        assertEquals(bottleDAO.findByToxicity(Toxicity.NON_TOXIC).get(0),
                bottlesInDb.get(2));
    }

    /**
     * Test of findByBatchId method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByBatchId() {
        System.out.println("Testing findByBatchId");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        for (int x = 0; x < bottlesInDb.size(); x++) {
            Bottle b1 = bottleDAO.findByBatchId(bottlesInDb.get(x).getBatchNumber()).get(0);
            Bottle b2 = bottlesInDb.get(x);
            assertEquals(bottleDAO.findByBatchId(bottlesInDb.get(x).getBatchNumber()).get(0),
                    bottlesInDb.get(x));
        }
    }

    /**
     * Test of insertBottle method, of class BottleDAOImpl.
     */
    @Test
    public void testInsertBottle() {
        System.out.println("Testing insertBottle");

        Store store = new Store("TestShop", "Alco1", "userAlco", "test");
        Producer producer = new Producer("TestProducer", "Vizovice", "userProducer", "test");
        BottleType bottleType = new BottleType("TestBottleType", "Kalashnikov",
                55, 700, producer);

        Bottle bottle = new Bottle(store, bottleType, 123456, 001122,
                new Date(new Date().getTime()), Toxicity.TOXIC);

        BottleDAO bottleDAO = new BottleDAOImpl(emf);
        StoreDAO storeDAO = new StoreDAOImpl(emf);
        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(emf);
        ProducerDAO producerDAO = new ProducerDAOImpl(emf);

        storeDAO.insertStore(store);
        producerDAO.insertProducer(producer);
        bottleTypeDAO.insertBottleType(bottleType);
        bottleDAO.insertBottle(bottle);
        bottlesInDb.add(bottle);

        try {
            bottleDAO.insertBottle(bottle);
            fail("Same bottle cannot be inserted twice.");
        } catch (PersistenceException p) {
            //ok
        }

        try {
            bottleDAO.insertBottle(new Bottle(null, null, 0, 0, null, Toxicity.TOXIC));
            fail("Bottle with null references cannot be inserted.");
        } catch (PersistenceException p) {
            //ok
        }
    }

    /**
     * Test of updateBottle method, of class BottleDAOImpl.
     */
    @Test
    public void testUpdateBottle() {
        System.out.println("Testing updateBottle");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        Bottle bottle = bottlesInDb.get(0);
        bottle.setBatchNumber(1010);
        bottle.setStamp(777);

        bottleDAO.updateBottle(bottle);
        assertEquals(1010, bottleDAO.findById(bottle.getId()).getBatchNumber());
        assertEquals(777, bottleDAO.findById(bottle.getId()).getStamp());

        try {
            Bottle bottle2 = new Bottle(null, null, 123456, 001122,
                    new Date(new Date().getTime()), Toxicity.TOXIC);
            bottleDAO.updateBottle(bottle2);
            fail("Non persisted bottle cannot be updated.");
        } catch (PersistenceException ex) {
            //ok
        }
    }

    /**
     * Test of deleteBottle method, of class BottleDAOImpl.
     */
    @Test
    public void testDeleteBottle() {
        System.out.println("Testing deleteBottle");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        for (int x = bottlesInDb.size(); x > 0; x--) {
            assertEquals(bottleDAO.findAll().size(), x);
            bottleDAO.deleteBottle(bottlesInDb.get(x - 1));
        }
        assertEquals(bottleDAO.findAll().size(), 0);
    }

}
