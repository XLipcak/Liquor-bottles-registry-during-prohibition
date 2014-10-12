package muni.fi.pa165.liquorbottles.persistenceLayerTests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.UserDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class UserDAOImplTest {
   
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    private List<User> expectedResultList;
    
    public UserDAOImplTest(){
        expectedResultList = new ArrayList<>();
    }
    
    @BeforeMethod
    public void BeforeMethod(){
        UserDAO userDao = new UserDAOImpl(emf);
        
        User user1 = new User();
        user1.setUsername("prvy");
        user1.setPassword("prveheslo");
        
        User user2 = new User();
        user2.setPassword("druheheslo");
        user2.setUsername("druhy");
                
        User user3 = new User();
        user3.setUsername("tri");
        user3.setPassword("pass");
        
        User user4 = new User();
        user4.setUsername("4");
        user4.setPassword("word");
        
        expectedResultList.add(user1);
        expectedResultList.add(user2);
        expectedResultList.add(user3);
        expectedResultList.add(user4);
        
        userDao.insertUser(user1);
        userDao.insertUser(user2);
        userDao.insertUser(user3);
        userDao.insertUser(user4);
        
    }
    
    @AfterMethod
    public void afterMethod() {
        expectedResultList = new ArrayList<>();
    }
    
    /**
     * Test of findAll method, of class USerDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindAll(){
        System.out.println("Testing findAll.");
         
        UserDAO storeDao = new UserDAOImpl(emf);
        List<User> result = storeDao.findAll();
        
        int count =0;
        for (User userRe : result){
            for (User userLis : expectedResultList){
                if (userRe.equals(userLis)){
                    count++;
                }
            }
        } 
        assertEquals(count, expectedResultList.size());
    }
    
    /**
     * Test of findById method, of class UserDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindById(){
        System.out.println("Testing findById");
        UserDAO userDao = new UserDAOImpl(emf);
        for (User expectedResultList1 : expectedResultList) {
            assertEquals(userDao.findById(expectedResultList1.getId()), expectedResultList1);
        }
    }
    
    /**
     * Test of findByUsername method, of class UserDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindByUsername(){
        System.out.println("Testing findByUserName");
        UserDAO userDao = new UserDAOImpl(emf);
        for (User expectedResultList1 : expectedResultList) {
            assertEquals(userDao.findByUsername(expectedResultList1.getUsername()), expectedResultList1);
        }
    }
    
    /**
     * Test of findPassByUsername method, of class UserDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testFindPassByUsername(){
        System.out.println("Testing findPassByUsername");
        UserDAO userDao = new UserDAOImpl(emf);
        for (User expectedResultList1 : expectedResultList) {
            assertEquals(userDao.findPassByUsername(expectedResultList1.getUsername()), expectedResultList1.getPassword());
        }
    }
    
    /**
     * Test of insertUser method, of class UserDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testInsert(){
        System.out.println("Testing insertUser");
        UserDAO userDao = new UserDAOImpl(emf);
        User toAdd = new User();
        toAdd.setUsername("last");
        toAdd.setPassword("lastpass");
        expectedResultList.add(toAdd);
        userDao.insertUser(toAdd);
        for (User expectedResultList1 : expectedResultList) {
            assertEquals(userDao.findByUsername(expectedResultList1.getUsername()), expectedResultList1);
        }
    }
    
    /**
     * Test of updatetUser method, of class UserDAOImpl.
     */
    @Test(groups = "executeBeforeDeleteTest")
    public void testUpdate(){
    System.out.println("Testing updateUser");
        UserDAO userDao = new UserDAOImpl(emf);
        User user;
        expectedResultList.get(expectedResultList.size()-1).setUsername("Changed by update");
        user = expectedResultList.get(expectedResultList.size()-1);
        userDao.updateUser(user);
        for (User expectedResultList1 : expectedResultList) {
            assertEquals(userDao.findByUsername(expectedResultList1.getUsername()), expectedResultList1);
        }
    }
    
    /**
     * Test of deleteUser method, of class UserDAOImpl.
     */
    @Test(dependsOnGroups = "executeBeforeDeleteTest")
    public void testDelete(){
        System.out.println("Testing deleteUser");
        UserDAO userDao = new UserDAOImpl(emf);
        userDao.deleteUser(expectedResultList.get(expectedResultList.size()-1));
        expectedResultList.remove(expectedResultList.size()-1);
        for (User expectedResultList1 : expectedResultList) {
            assertEquals(userDao.findByUsername(expectedResultList1.getUsername()), expectedResultList1);
        }
    }
}
