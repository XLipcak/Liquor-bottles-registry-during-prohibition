/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.BottleTypeDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.BottleTypeDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Taraj, Masaryk University
 */
public class BottleTypeDAOImplTest {

    //TODO: inject this values from XML
    private final int NUMBER_OF_RECORDS = 50;
    private final String NAME_OF_DB = "testDB";

    private EntityManagerFactory emf;
    private EntityManager em;

    private List<BottleType> bottleTypesInDb;

    public BottleTypeDAOImplTest() {
        bottleTypesInDb = new ArrayList<>();
    }

    @BeforeMethod
    public void beforeMethod() {
        emf = Persistence.createEntityManagerFactory(NAME_OF_DB);
        em = emf.createEntityManager();
        em.getTransaction().begin();

        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);
        ProducerDAO producerDAO = new ProducerDAOImpl(em);

        Producer producer = new Producer("TestProducer", "Prod1", "user123", "test");
        BottleType bottleType1 = new BottleType("Bozkov RUM, 0,5l", "rum", 35, 500, producer);
        BottleType bottleType2 = new BottleType("Bozkov VODKA, 0,7l", "vodka", 40, 700, producer);
        BottleType bottleType3 = new BottleType("Bozkov WHISKEY, 0,7l", "whiskey", 40, 700, producer);

        producerDAO.insertProducer(producer);
        bottleTypeDAO.insertBottleType(bottleType1);
        bottleTypeDAO.insertBottleType(bottleType2);
        bottleTypeDAO.insertBottleType(bottleType3);

        bottleTypesInDb.add(bottleType1);
        bottleTypesInDb.add(bottleType2);
        bottleTypesInDb.add(bottleType3);

        em.getTransaction().commit();
    }

    @AfterMethod
    public void afterMethod() {
        bottleTypesInDb = new ArrayList<>();
    }

    /**
     * Test of findAll method, of class BottleTypeDAOImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("Testing findAll.");

        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);

        List<BottleType> result = bottleTypeDAO.findAll();

        int x = 0;
        for (BottleType b1 : result) {
            for (BottleType b2 : bottleTypesInDb) {
                if (b1.equals(b2)) {
                    x++;
                }
            }
        }
        assertEquals(x, bottleTypesInDb.size());
    }

    /**
     * Test of findById method, of class BottleTypeDAOImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("Testing findById");

        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);

        for (int x = 0; x < bottleTypesInDb.size(); x++) {
            assertEquals(bottleTypeDAO.findById(bottleTypesInDb.get(x).getId()),
                    bottleTypesInDb.get(x));
        }
    }

    /**
     * Test of findByAlcType method, of class BottleTypeDAOImpl.
     */
    @Test
    public void findByAlcType() {
        System.out.println("Testing findByAlcType");

        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);

        for (int x = 0; x < bottleTypesInDb.size(); x++) {
            BottleType b1 = bottleTypeDAO.findByAlcType(bottleTypesInDb.get(x).getAlcType()).get(0);
            BottleType b2 = bottleTypesInDb.get(x);
            assertEquals(bottleTypeDAO.findByAlcType(bottleTypesInDb.get(x).getAlcType()).get(0),
                    bottleTypesInDb.get(x));
        }
    }

    /**
     * Test of findByPower method, of class BottleTypeDAOImpl.
     */
    @Test
    public void findByPower() {
        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);

        for (int x = 0; x < bottleTypesInDb.size(); x++) {
            List<BottleType> b1 = bottleTypeDAO.findByPower(bottleTypesInDb.get(x).getPower());
            int count = 0;
            for (int i = 0; i < bottleTypesInDb.size(); i++) {
                if (bottleTypesInDb.get(x).getPower() == bottleTypesInDb.get(i).getPower()) {
                    count++;
                }
            }
            assertEquals(count, b1.size());
        }
    }

    /**
     * Test of findByVolume method, of class BottleTypeDAOImpl.
     */
    @Test
    public void findByVolume() {
        System.out.println("Testing findByVolume");

        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);

        for (int x = 0; x < bottleTypesInDb.size(); x++) {
            List<BottleType> b1 = bottleTypeDAO.findByVolume(bottleTypesInDb.get(x).getVolume());
            int count = 0;
            for (int i = 0; i < bottleTypesInDb.size(); i++) {
                if (bottleTypesInDb.get(x).getVolume() == bottleTypesInDb.get(i).getVolume()) {
                    count++;
                }
            }
            assertEquals(count, b1.size());
        }
    }

    /**
     * Test of insertBottleType method, of class BottleTypeDAOImpl.
     */
    @Test
    public void testInsertTypeBottle() {
        System.out.println("Testing insertBottleType");

        Producer producer = new Producer("TestProducerInsertBT", "Prod1234", "userInsertType", "test");
        BottleType bottleType = new BottleType("Bozkov RUM, 0,5l", "rum", 35, 500, producer);

        ProducerDAO producerDAO = new ProducerDAOImpl(em);
        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);

        em.getTransaction().begin();
        producerDAO.insertProducer(producer);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        bottleTypeDAO.insertBottleType(bottleType);
        em.getTransaction().commit();
        
        bottleTypesInDb.add(bottleType);

        try {
            bottleTypeDAO.insertBottleType(new BottleType(null, null, 0, 0, null));
            fail("Bottle Type with null references cannot be inserted.");
        } catch (PersistenceException p) {
            //ok
        }
    }

    /**
     * Test of updateBottleType method, of class BottleTypeDAOImpl.
     *
     */
    @Test
    public void testUpdateTypeBottle() {
        System.out.println("Testing updateBottleType");

        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);

        BottleType bottleType = bottleTypesInDb.get(0);
        bottleType.setName("Bozkov zmeneny, 0,1l");
        bottleType.setAlcType("Updatecohol");
        bottleType.setPower(100);
        bottleType.setVolume(100);

        bottleTypeDAO.updateBottleType(bottleType);
        assertEquals("Bozkov zmeneny, 0,1l", bottleTypeDAO.findById(bottleType.getId()).getName());
        assertEquals("Updatecohol", bottleTypeDAO.findById(bottleType.getId()).getAlcType());
        assertEquals(100, bottleTypeDAO.findById(bottleType.getId()).getPower());
        assertEquals(100, bottleTypeDAO.findById(bottleType.getId()).getVolume());

        try {
            BottleType bottleType2 = new BottleType(null, null, 0, 0, null);
            bottleTypeDAO.updateBottleType(bottleType2);
            fail("Non persisted bottle type cannot be updated.");
        } catch (PersistenceException ex) {
            //ok
        }
    }

    /**
     * Test of deleteBottleType method, of class BottleTypeDAOImpl.
     */
    @Test
    public void testDeleteBottleType() {
        System.out.println("Testing deleteBottleType");

        BottleTypeDAO bottleTypeDAO = new BottleTypeDAOImpl(em);

        for (int x = bottleTypesInDb.size(); x > 0; x--) {
            assertEquals(bottleTypeDAO.findAll().size(), x);
            
            em.getTransaction().begin();
            bottleTypeDAO.deleteBottleType(bottleTypesInDb.get(x - 1));
            em.getTransaction().commit();
        }
        assertEquals(bottleTypeDAO.findAll().size(), 0);
    }
}
