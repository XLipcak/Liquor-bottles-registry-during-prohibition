
package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.StoreDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.StoreDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Štora
 */
public class StoreDAOImplTest {
    
    private final EntityManagerFactory emf;
    private final List<Store> expectedResultList;
    
    public StoreDAOImplTest(){
        emf = Persistence.createEntityManagerFactory("muni.fi.pa165_LiquorBottles_jar_1.0-SNAPSHOTPU");
        expectedResultList = new ArrayList<>();
    }
    
    @BeforeClass
    public void setup(){
        StoreDAO storeDao = new StoreDAOImpl(emf);
        
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
    }
    
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindAll(){
        System.out.println("Testing findAll.");
         
        StoreDAO storeDao = new StoreDAOImpl(emf);
        List<Store> result = storeDao.findAll();
        
        int count =0;
        for (Store storeRe : result){
            for (Store storeLis : expectedResultList){
                if (storeRe.equals(storeLis)){
                    count++;
                }
            }
        } 
        assertEquals(count, expectedResultList.size());
    }
    
    @Test(groups = "executeBeforeDeleteTest") 
    public void testFindById(){
        System.out.println("Testing findById");
        StoreDAO storeDao = new StoreDAOImpl(emf);
        for(int i=0;i<expectedResultList.size();i++ ){
            assertEquals(storeDao.findById(expectedResultList.get(i).getId()), expectedResultList.get(i));
        }
        
    }
    
    
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindByAdress(){
        System.out.println("Testing findByAdress");
        StoreDAO storeDao = new StoreDAOImpl(emf);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(storeDao.findByAddress(expectedResultList.get(i).getAddress()), expectedResultList.get(i));
        }
    }
    
    @Test(groups = "executeBeforeDeleteTest")
    public void testUpdate(){
        System.out.println("Testing updateStore");
        StoreDAO storeDao = new StoreDAOImpl(emf);
        Store store;
        expectedResultList.get(expectedResultList.size()-1).setUsername("Changed by update");
        store = expectedResultList.get(expectedResultList.size()-1);
        storeDao.updateStore(store);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(storeDao.findByAddress(expectedResultList.get(i).getAddress()), expectedResultList.get(i));
        }
    }
    
    @Test(groups = "executeBeforeDeleteTest")
    public void testInsert(){
        System.out.println("Testing insert");
        StoreDAO storeDao = new StoreDAOImpl(emf);
        Store toAdd = new Store("new", "somwhere", "lastshop", "passw");
        expectedResultList.add(toAdd);
        storeDao.insertStore(toAdd);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(storeDao.findByAddress(expectedResultList.get(i).getAddress()), expectedResultList.get(i));
        }
    }
    
    @Test(groups = "executeBeforeDeleteTest")
    public void testDelte(){
        System.out.println("Testing delete");
        StoreDAO storeDao = new StoreDAOImpl(emf);
        storeDao.deleteStore(expectedResultList.get(expectedResultList.size()-1));
        expectedResultList.remove(expectedResultList.size()-1);
        for (int i = 0; i < expectedResultList.size(); i++) {
            assertEquals(storeDao.findByAddress(expectedResultList.get(i).getAddress()), expectedResultList.get(i));
        }
    }
}
