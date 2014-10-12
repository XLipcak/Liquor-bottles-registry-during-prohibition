package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.PoliceDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.PoliceDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class PoliceDAOImplTest {

    private final EntityManagerFactory emf;
    private final List<Police> expectedResultList;

    public PoliceDAOImplTest() {
        emf = Persistence.createEntityManagerFactory("muni.fi.pa165_LiquorBottles_jar_1.0-SNAPSHOTPU");
        expectedResultList = new ArrayList<>();
    }

    @BeforeClass
    public void setup() {

    }

    @Test
    public void testFindAll() {
        System.out.println("Testing findAll.");

        PoliceDAO policeDAO = new PoliceDAOImpl(emf);

        List<Police> result = policeDAO.findAll();
        assertEquals(result, expectedResultList);
    }

    @Test
    public void testFindById() {
        System.out.println("Testing findById");

        /*PoliceDAO policeDAO = new PoliceDAOImpl(emf);

         for (int x = 0; x < expectedResultList.size(); x++) {
         assertEquals(PoliceDAO.findById(expectedResultList.get(x).getId()),
         expectedResultList.get(x));
         }*/
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
        //TODO
    }

    @Test
    public void testUpdatePolice() {
        System.out.println("Testing updatePolice");
        //TODO
    }

    @Test
    public void testDeletePolice() {
        System.out.println("Testing deletePolice");
        //TODO
    }
}
