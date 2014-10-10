package muni.fi.pa165.liquorbottles;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.PoliceDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.PoliceDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.UserDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "muni.fi.pa165_LiquorBottles_jar_1.0-SNAPSHOTPU");

        Police police = new Police();
        police.setName("PoliceMan1");
        police.setAddress("Botanicka 68");
        police.setUsername("user");
        police.setPassword("123");

        Store store = new Store();
        store.setName("Alco shop");
        store.setAddress("Get down.");

        Producer producer = new Producer();
        producer.setName("Alco producer");
        producer.setAddress("Bayerova 9");

        UserDAO userDao = new UserDAOImpl(emf);
        
        userDao.insertUser(police);
        userDao.insertUser(store);
        userDao.insertUser(producer);

        emf.close();

    }
}
