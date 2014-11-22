package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.StoreDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.StoreDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class StoreDAOImplTest {

    //TODO: inject this values from XML
    private final int NUMBER_OF_RECORDS = 50;
    private final String NAME_OF_DB = "testDB";

    private EntityManagerFactory emf;
    private EntityManager em;
    
    private List<Store> expectedResultList;

    public StoreDAOImplTest() {
        expectedResultList = new ArrayList<>();
    }

    @BeforeMethod
    public void BeforeMethod() {
        emf = Persistence.createEntityManagerFactory(NAME_OF_DB);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        StoreDAO storeDao = new StoreDAOImpl(em);

        Store store1 = new Store("Test1", "Botanicka 68", "Muni", "pokus123");
        Store store2 = new Store("Test2", "Polna 48", "Zalesak", "90028");
        Store store3 = new Store("Test", "Lidicka 18", "something", "pass");
        Store last = new Store("Last", "to get changed", "userName", "key");

        storeDao.insertStore(store1);
        storeDao.insertStore(store2);
        storeDao.insertStore(store3);
        storeDao.insertStore(last);

        expectedResultList.add(store1);
        expectedResultList.add(store2);
        expectedResultList.add(store3);
        expectedResultList.add(last);
        
        em.getTransaction().commit();
    }

    @AfterMethod
    public void afterMethod() {
        expectedResultList = new ArrayList<>();
    }

    /**
     * Test of findAll method, of class StoreDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindAll() {
        System.out.println("Testing findAll.");

        StoreDAO storeDao = new StoreDAOImpl(em);
        List<Store> result = storeDao.findAll();

        int count = 0;
        for (Store storeRe : result) {
            for (Store storeLis : expectedResultList) {
                if (storeRe.equals(storeLis)) {
                    count++;
                }
            }
        }
        assertEquals(count, expectedResultList.size());
    }

    /**
     * Test of findById method, of class StoreDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindById() {
        System.out.println("Testing findById");
        StoreDAO storeDao = new StoreDAOImpl(em);
        for (Store expectedResultList1 : expectedResultList) {
            assertEquals(storeDao.findById(expectedResultList1.getId()), expectedResultList1);
        }

    }

    /**
     * Test of findByAdress method, of class StoreDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindByAdress() {
        System.out.println("Testing findByAdress");
        StoreDAO storeDao = new StoreDAOImpl(em);
        for (Store expectedResultList1 : expectedResultList) {
            assertEquals(storeDao.findByAddress(expectedResultList1.getAddress()), expectedResultList1);
        }
    }

    /**
     * Test of updateStore method, of class StoreDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testUpdate() {
        System.out.println("Testing updateStore");
        StoreDAO storeDao = new StoreDAOImpl(em);
        Store store;
        expectedResultList.get(expectedResultList.size() - 1).setUsername("Changed by update");
        store = expectedResultList.get(expectedResultList.size() - 1);
        
        em.getTransaction().begin();
        storeDao.updateStore(store);
        em.getTransaction().commit();
        
        for (Store expectedResultList1 : expectedResultList) {
            assertEquals(storeDao.findByAddress(expectedResultList1.getAddress()), expectedResultList1);
        }
    }

    /**
     * Test of insertStore method, of class StoreDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testInsert() {
        System.out.println("Testing insertStore");
        StoreDAO storeDao = new StoreDAOImpl(em);
        Store toAdd = new Store("new", "somwhere", "lastshop", "passw");
        expectedResultList.add(toAdd);
        
        em.getTransaction().begin();
        storeDao.insertStore(toAdd);
        em.getTransaction().commit();
        
        for (Store expectedResultList1 : expectedResultList) {
            assertEquals(storeDao.findByAddress(expectedResultList1.getAddress()), expectedResultList1);
        }
    }

    /**
     * Test of deleteStore method, of class StoreDAOImpl.
     */
    @Test(dependsOnGroups = "executeBeforeDeleteTest")
    public void testDelte() {
        System.out.println("Testing deleteStore");
        StoreDAO storeDao = new StoreDAOImpl(em);
        
        em.getTransaction().begin();
        storeDao.deleteStore(expectedResultList.get(expectedResultList.size() - 1));
        em.getTransaction().commit();
        
        expectedResultList.remove(expectedResultList.size() - 1);
        for (Store expectedResultList1 : expectedResultList) {
            assertEquals(storeDao.findByAddress(expectedResultList1.getAddress()), expectedResultList1);
        }
    }
}
