package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class ProducerDAOImplTest {

    private final EntityManagerFactory emf;
    private final List<Producer> expectedResultList;

    public ProducerDAOImplTest() {
        emf = Persistence.createEntityManagerFactory("muni.fi.pa165_LiquorBottles_jar_1.0-SNAPSHOTPU");
        expectedResultList = new ArrayList<>();
    }

    @BeforeClass
    public void setup() {

    }

    @Test
    public void testFindAll() {
        System.out.println("Testing findAll.");

        ProducerDAO producerDAO = new ProducerDAOImpl(emf);

        List<Producer> result = producerDAO.findAll();
        assertEquals(result, expectedResultList);
    }

    @Test
    public void testFindById() {
        System.out.println("Testing findById");

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
    public void testInsertPolice() {
        System.out.println("Testing insertProducer");
        //TODO
    }

    @Test
    public void testUpdatePolice() {
        System.out.println("Testing updateProducer");
        //TODO
    }

    @Test
    public void testDeletePolice() {
        System.out.println("Testing deleteProducer");
        //TODO
    }
}
