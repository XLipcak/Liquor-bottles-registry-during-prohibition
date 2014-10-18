package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class ProducerDAOImplTest {

    //TODO: inject this values from XML
    private final int NUMBER_OF_RECORDS = 50;
    private final String NAME_OF_DB = "testDB";

    private EntityManagerFactory emf;
    private List<Producer> expectedResultList;

    public ProducerDAOImplTest() {
        expectedResultList = new ArrayList<>();
    }

    @BeforeMethod
    public void beforeMethod() {
        emf = Persistence.createEntityManagerFactory(NAME_OF_DB);
        ProducerDAO producerDAO = new ProducerDAOImpl(emf);
        Producer producer1 = new Producer("1", "a1", "uu1", "p1");
        Producer producer2 = new Producer("2", "a2", "uu2", "p2");

        producerDAO.insertProducer(producer1);
        producerDAO.insertProducer(producer2);

        expectedResultList.add(producer1);
        expectedResultList.add(producer2);
    }

    @AfterMethod
    public void afterMethod() {
        expectedResultList = new ArrayList<>();
    }

    @Test
    public void testFindAll() {
        System.out.println("Testing findAll.");

        ProducerDAO producerDAO = new ProducerDAOImpl(emf);

        List<Producer> result = producerDAO.findAll();
        int x = 0;
        for (Producer p1 : result) {
            for (Producer p2 : expectedResultList) {
                if (p1.equals(p2)) {
                    x++;
                }
            }
        }
        assertEquals(x, expectedResultList.size());
    }

    @Test
    public void testFindById() {
        System.out.println("Testing findById");

        ProducerDAO producerDAO = new ProducerDAOImpl(emf);

        for (int x = 0; x < expectedResultList.size(); x++) {
            assertEquals(producerDAO.findById(expectedResultList.get(x).getId()),
                    expectedResultList.get(x));
        }

    }

    @Test
    public void findByUsername() {
        System.out.println("Testing findByUsername");
        ProducerDAO producerDao = new ProducerDAOImpl(emf);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(producerDao.findByUsername(expectedResultList.get(i).getUsername()), expectedResultList.get(i));
        }
    }

    @Test
    public void findByfindByName() {
        System.out.println("Testing findByName");
        ProducerDAO producerDao = new ProducerDAOImpl(emf);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(producerDao.findByName(expectedResultList.get(i).getName()), expectedResultList.get(i));
        }
    }

    @Test
    public void findByAdress() {
        System.out.println("Testing findByAdress");
        ProducerDAO producerDao = new ProducerDAOImpl(emf);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(producerDao.findByAddress(expectedResultList.get(i).getAddress()), expectedResultList.get(i));
        }
    }

    @Test
    public void testInsertProducer() {
        System.out.println("Testing insertProducer");

        Producer producer = new Producer("PProducer", "Alberquerque", "Fetak", "9999");
        ProducerDAO producerDAO = new ProducerDAOImpl(emf);
        producerDAO.insertProducer(producer);
        expectedResultList.add(producer);

        assertEquals(expectedResultList.get(expectedResultList.size() - 1), producer);
        try {
            producerDAO.insertProducer(producer);
            fail("Same producer cannot be inserted twice.");
        } catch (PersistenceException p) {

        }

        try {
            producerDAO.insertProducer(new Producer(null, null, null, null));
            fail("Producer with null references cannot be inserted.");
        } catch (PersistenceException p) {

        }
    }

    @Test
    public void testUpdateProducer() {
        System.out.println("Testing updateProducer");

        ProducerDAO producerDAO = new ProducerDAOImpl(emf);
        Producer producer = expectedResultList.get(0);

        producer.setAddress("JT");
        producer.setName("Heinsenberg");
        producer.setUsername("Kladivo");
        producer.setPassword("1234");
        producerDAO.updateProducer(producer);

        assertEquals("JT", producerDAO.findById(producer.getId()).getAddress());
        assertEquals("Heinsenberg", producerDAO.findById(producer.getId()).getName());
        assertEquals("Kladivo", producerDAO.findById(producer.getId()).getUsername());
        assertEquals("1234", producerDAO.findById(producer.getId()).getPassword());

        try {
            Producer producer2 = new Producer();
            producerDAO.updateProducer(producer2);
            fail("Non persisted POLICE cannot be updated.");
        } catch (PersistenceException ex) {

        }
    }

    @Test
    public void testDeleteProducer() {
        System.out.println("Testing deleteProducer");

        ProducerDAO producerDAO = new ProducerDAOImpl(emf);

        int countOfProducers = producerDAO.findAll().size();
        for (int x = expectedResultList.size(); x > 0; x--) {
            assertEquals(producerDAO.findAll().size(), countOfProducers);
            producerDAO.deleteProducer(expectedResultList.get(x - 1));
            countOfProducers--;
        }
    }
}
