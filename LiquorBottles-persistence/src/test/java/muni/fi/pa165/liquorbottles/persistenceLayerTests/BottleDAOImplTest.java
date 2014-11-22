package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
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
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class BottleDAOImplTest {

    //TODO: inject this values from XML
    private final int NUMBER_OF_RECORDS = 50;
    private final String NAME_OF_DB = "testDB";

    private EntityManagerFactory emf;
    private EntityManager em;

    private List<Bottle> bottlesInDb;

    public BottleDAOImplTest() {
        bottlesInDb = new ArrayList<>();
    }

    @BeforeMethod
    public void beforeMethod() {
        //prepare EntityManagerFactory
        emf = Persistence.createEntityManagerFactory(NAME_OF_DB);
        em = emf.createEntityManager();
        em.getTransaction().begin();

        //prepare records in db
        BottleDAO bottleDAO = new BottleDAOImpl(em);
        StoreDAO storeDAO = new StoreDAOImpl(em);
        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);
        ProducerDAO producerDAO = new ProducerDAOImpl(em);

        for (int x = 0; x < NUMBER_OF_RECORDS; x++) {

            Store store = new Store("TestShop", "Alco1", "userStore" + x, "test");
            Producer producer = new Producer("TestProducer", "Vizovice", "userProducer" + x, "test");
            BottleType bottleType = new BottleType("TestBottleType" + x, "Kalashnikov" + x,
                    55, 700, producer);

            Bottle bottle1 = new Bottle(store, bottleType, 123456, x,
                    new Date(new Date().getTime()), Toxicity.values()[x % 3]);

            storeDAO.insertStore(store);
            producerDAO.insertProducer(producer);
            bottleTypeDAO.insertBottleType(bottleType);

            bottleDAO.insertBottle(bottle1);

            bottlesInDb.add(bottle1);
        }
        em.getTransaction().commit();
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
        BottleDAO bottleDAO = new BottleDAOImpl(em);

        List<Bottle> result = bottleDAO.findAll();
        assertEquals(result, bottlesInDb);
    }

    /**
     * Test of findById method, of class BottleDAOImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("Testing findById");
        BottleDAO bottleDAO = new BottleDAOImpl(em);

        for (Bottle bottle : bottlesInDb) {
            assertEquals(bottleDAO.findById(bottle.getId()), bottle);
        }
    }

    /**
     * Test of findByStamp method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByStamp() {
        System.out.println("Testing findByStamp");
        BottleDAO bottleDAO = new BottleDAOImpl(em);

        for (Bottle bottle : bottlesInDb) {
            Bottle b1 = bottleDAO.findByStamp(bottle.getStamp());
            Bottle b2 = bottle;
            assertEquals(b1, b2);
        }
    }

    /**
     * Test of findByDate method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByDate() {
        System.out.println("Testing findByDate");
        BottleDAO bottleDAO = new BottleDAOImpl(em);

        assertEquals(bottleDAO.findByDate(new Date(new Date().getTime())),
                bottlesInDb);
    }

    /**
     * Test of findByToxicity method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByToxicity() {
        System.out.println("Testing findByToxicity");
        BottleDAO bottleDAO = new BottleDAOImpl(em);

        List<Bottle> toxicBottles = bottleDAO.findByToxicity(Toxicity.TOXIC);
        int x = 0;
        for (Bottle bottle : toxicBottles) {
            assertEquals(bottle, bottlesInDb.get(x * 3));
            x++;
        }

        List<Bottle> uncheckedBottles = bottleDAO.findByToxicity(Toxicity.UNCHECKED);
        x = 0;
        for (Bottle bottle : uncheckedBottles) {
            assertEquals(bottle, bottlesInDb.get((x * 3) + 1));
            x++;
        }

        List<Bottle> nonToxicBottles = bottleDAO.findByToxicity(Toxicity.NON_TOXIC);
        x = 0;
        for (Bottle bottle : nonToxicBottles) {
            assertEquals(bottle, bottlesInDb.get((x * 3) + 2));
            x++;
        }
    }

    /**
     * Test of findByBatchId method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByBatchId() {
        System.out.println("Testing findByBatchId");
        BottleDAO bottleDAO = new BottleDAOImpl(em);

        List<Bottle> b1 = bottleDAO.findByBatchId(bottlesInDb.get(0).getBatchNumber());
        assertEquals(b1, bottlesInDb);

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

        BottleDAO bottleDAO = new BottleDAOImpl(em);
        StoreDAO storeDAO = new StoreDAOImpl(em);
        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);
        ProducerDAO producerDAO = new ProducerDAOImpl(em);

        em.getTransaction().begin();
        storeDAO.insertStore(store);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        producerDAO.insertProducer(producer);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        bottleTypeDAO.insertBottleType(bottleType);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        bottleDAO.insertBottle(bottle);
        em.getTransaction().commit();
        
        bottlesInDb.add(bottle);

        assertEquals(bottleDAO.findById(bottle.getId()), bottle);

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
        BottleDAO bottleDAO = new BottleDAOImpl(em);

        Bottle bottle = bottlesInDb.get(0);
        bottle.setBatchNumber(-300);
        bottle.setStamp(-777);

        em.getTransaction().begin();
        bottleDAO.updateBottle(bottle);
        em.getTransaction().commit();
        
        assertEquals(-300, bottleDAO.findById(bottle.getId()).getBatchNumber());
        assertEquals(-777, bottleDAO.findById(bottle.getId()).getStamp());

        assertEquals(bottle, bottleDAO.findByBatchId(-300).get(0));
        assertEquals(bottle, bottleDAO.findByStamp(-777));

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

        BottleDAO bottleDAO = new BottleDAOImpl(em);

        for (int x = bottlesInDb.size(); x > 0; x--) {
            //chech bottle in DB
            assertEquals(bottleDAO.findById(bottlesInDb.get(x - 1).getId()),
                    bottlesInDb.get(x - 1));

            //check size of DB
            assertEquals(bottleDAO.findAll().size(), x);

            em.getTransaction().begin();
            bottleDAO.deleteBottle(bottlesInDb.get(x - 1));
            em.getTransaction().commit();

            //chech deleted bottle in DB
            assertEquals(bottleDAO.findById(bottlesInDb.get(x - 1).getId()),
                    null);
        }
        assertEquals(bottleDAO.findAll().size(), 0);
    }

}
