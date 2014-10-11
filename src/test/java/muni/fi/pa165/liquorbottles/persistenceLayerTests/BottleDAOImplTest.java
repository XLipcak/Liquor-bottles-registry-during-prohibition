package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class BottleDAOImplTest {

    private EntityManagerFactory emf;
    private List<Bottle> expectedResultList;

    public BottleDAOImplTest() {
        emf = Persistence.createEntityManagerFactory("muni.fi.pa165_LiquorBottles_jar_1.0-SNAPSHOTPU");
        expectedResultList = new ArrayList<>();
    }

    @BeforeClass
    public void setup() {
        BottleDAO bottleDAO = new BottleDAOImpl(emf);
        StoreDAO storeDAO = new StoreDAOImpl(emf);
        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(emf);
        ProducerDAO producerDAO = new ProducerDAOImpl(emf);

        Store store = new Store("TestShop", "Alco1", "user123", "test");
        Producer producer = new Producer("TestProducer", "Vizovice", "user", "test");
        BottleType bottleType = new BottleType("TestBottleType", "Kalashnikov",
                55, 700, producer);

        Bottle bottle1 = new Bottle(store, bottleType, 123456, new Date(new Date().getTime()),
                Toxicity.TOXIC);
        Bottle bottle2 = new Bottle(store, bottleType, 123456, new Date(new Date().getTime()),
                Toxicity.UNCHECKED);
        Bottle bottle3 = new Bottle(store, bottleType, 0000, new Date(new Date().getTime()),
                Toxicity.NON_TOXIC);

        storeDAO.insertStore(store);
        producerDAO.insertProducer(producer);
        bottleTypeDAO.insertBottleType(bottleType);

        bottleDAO.insertBottle(bottle1);
        bottleDAO.insertBottle(bottle2);
        bottleDAO.insertBottle(bottle3);

        expectedResultList.add(bottle1);
        expectedResultList.add(bottle2);
        expectedResultList.add(bottle3);
    }

    /**
     * Test of findAll method, of class BottleDAOImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("Testing findAll.");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        List<Bottle> result = bottleDAO.findAll();
        assertEquals(result, expectedResultList);
    }

    /**
     * Test of findById method, of class BottleDAOImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("Testing findById");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        for (int x = 0; x < expectedResultList.size(); x++) {
            assertEquals(bottleDAO.findById(expectedResultList.get(x).getId()),
                    expectedResultList.get(x));
        }
    }

    /**
     * Test of findByStamp method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByStamp() {
        System.out.println("Testing findByStamp");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        for (int x = 0; x < expectedResultList.size(); x++) {
            assertEquals(bottleDAO.findByStamp(expectedResultList.get(x).getStamp()),
                    expectedResultList.get(x));
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
                expectedResultList);
    }

    /**
     * Test of findByToxicity method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByToxicity() {
        System.out.println("Testing findByToxicity");

        BottleDAO bottleDAO = new BottleDAOImpl(emf);

        assertEquals(bottleDAO.findByToxicity(Toxicity.TOXIC).get(0),
                expectedResultList.get(0));
        assertEquals(bottleDAO.findByToxicity(Toxicity.UNCHECKED).get(0),
                expectedResultList.get(1));
        assertEquals(bottleDAO.findByToxicity(Toxicity.NON_TOXIC).get(0),
                expectedResultList.get(2));
    }

    /**
     * Test of insertBottle method, of class BottleDAOImpl.
     */
    @Test
    public void testInsertBottle() {
        System.out.println("Testing insertBottle");
        //TODO
    }

    /**
     * Test of updateBottle method, of class BottleDAOImpl.
     */
    @Test
    public void testUpdateBottle() {
        System.out.println("Testing updateBottle");
        //TODO
    }

    /**
     * Test of deleteBottle method, of class BottleDAOImpl.
     */
    @Test
    public void testDeleteBottle() {
        System.out.println("Testing deleteBottle");
        //TODO
    }

    /**
     * Test of findByBatchId method, of class BottleDAOImpl.
     */
    @Test
    public void testFindByBatchId() {
        System.out.println("Testing findByBatchId");
        //TODO
    }

}
