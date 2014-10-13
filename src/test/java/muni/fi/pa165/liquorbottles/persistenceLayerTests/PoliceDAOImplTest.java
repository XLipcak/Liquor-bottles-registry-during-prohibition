package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import muni.fi.pa165.liquorbottles.classes.DaoContext;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.PoliceDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.PoliceDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Matus Novak, Masaryk University
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PoliceDAOImplTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private List<Police> expectedResultList;

    public PoliceDAOImplTest() {
        expectedResultList = new ArrayList<>();
    }

    @BeforeMethod
    public void beforeMethod() {
        PoliceDAO policeDAO = new PoliceDAOImpl(emf);
        Police police1 = new Police("1", "a1", "u1", "p1");
        Police police2 = new Police("2", "a2", "u2", "p2");

        policeDAO.insertPolice(police1);
        policeDAO.insertPolice(police2);

        expectedResultList.add(police1);
        expectedResultList.add(police2);
    }

    @AfterMethod
    public void afterMethod() {
        expectedResultList = new ArrayList<>();
    }

    @Test
    public void testFindAll() {
        System.out.println("Testing findAll.");

        PoliceDAO policeDAO = new PoliceDAOImpl(emf);

        List<Police> result = policeDAO.findAll();
        int x = 0;
        for (Police p1 : result) {
            for (Police p2 : expectedResultList) {
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

        PoliceDAO policeDAO = new PoliceDAOImpl(emf);

        for (int x = 0; x < expectedResultList.size(); x++) {
            assertEquals(policeDAO.findById(expectedResultList.get(x).getId()),
                    expectedResultList.get(x));
        }
    }

    @Test
    public void findByUsername() {
        System.out.println("Testing findByUsername");
        PoliceDAO policeDao = new PoliceDAOImpl(emf);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(policeDao.findByUsername(expectedResultList.get(i).getUsername()), expectedResultList.get(i));
        }
    }

    @Test
    public void findByfindByName() {
        System.out.println("Testing findByName");
        PoliceDAO policeDao = new PoliceDAOImpl(emf);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(policeDao.findByName(expectedResultList.get(i).getName()), expectedResultList.get(i));
        }
    }

    @Test
    public void findByAdress() {
        System.out.println("Testing findByAdress");
        PoliceDAO policeDao = new PoliceDAOImpl(emf);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(policeDao.findByAddress(expectedResultList.get(i).getAddress()), expectedResultList.get(i));
        }
    }

    @Test
    public void testInsertPolice() {
        System.out.println("Testing insertPolice");

        Police police = new Police("PPolice", "VDolnychZenskychKoncinach", "Kalinak", "0000");
        PoliceDAO policeDAO = new PoliceDAOImpl(emf);
        policeDAO.insertPolice(police);
        expectedResultList.add(police);

        assertEquals(expectedResultList.get(expectedResultList.size() - 1), police);

        try {
            policeDAO.insertPolice(police);
            fail("Same police cannot be inserted twice.");
        } catch (PersistenceException p) {

        }

        try {
            policeDAO.insertPolice(new Police(null, null, null, null));
            fail("Police with null references cannot be inserted.");
        } catch (PersistenceException p) {

        }

    }

    @Test
    public void testUpdatePolice() {
        System.out.println("Testing updatePolice");

        PoliceDAO policeDAO = new PoliceDAOImpl(emf);
        Police police = expectedResultList.get(0);
        police.setAddress("Matejkova");
        police.setName("Jozef");
        police.setUsername("MVSK");
        police.setPassword("NBU123");
        policeDAO.updatePolice(police);

        assertEquals("Matejkova", policeDAO.findById(police.getId()).getAddress());
        assertEquals("Jozef", policeDAO.findById(police.getId()).getName());
        assertEquals("MVSK", policeDAO.findById(police.getId()).getUsername());
        assertEquals("NBU123", policeDAO.findById(police.getId()).getPassword());

        try {
            Police police2 = new Police();
            policeDAO.updatePolice(police2);
            fail("Non persisted POLICE cannot be updated.");
        } catch (PersistenceException ex) {

        }
    }

    @Test
    public void testDeletePolice() {
        System.out.println("Testing deletePolice");

        PoliceDAO policeDAO = new PoliceDAOImpl(emf);

        for (int x = expectedResultList.size(); x > 0; x--) {
            assertEquals(policeDAO.findAll().size(), x);
            policeDAO.deletePolice(expectedResultList.get(x - 1));
        }
        assertEquals(policeDAO.findAll().size(), 0);
    }
}
