package muni.fi.pa165.liquorbottles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class Main {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "muni.fi.pa165_LiquorBottles_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Police police = new Police();
        police.setName("PoliceMan1");
        police.setAddress("Botanicka 68");
        
        Store store = new Store();
        store.setName("Alco shop");
        store.setAddress("Get down.");
        
        Bottle bottle = new Bottle();
        bottle.setBatchNumber(1234);
        bottle.setDateOfBirth("vcera");
        bottle.setIsToxic(0);
        
        em.persist(police);
        em.persist(store);
        em.persist(bottle);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        
    }
}
