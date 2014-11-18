import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.UserDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;
import muni.fi.pa165.liquorbottles.service.dto.PoliceDTO;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.dto.UserDTO;
import muni.fi.pa165.liquorbottles.service.services.PoliceService;
import muni.fi.pa165.liquorbottles.service.services.ProducerService;
import muni.fi.pa165.liquorbottles.service.services.UserService;
import muni.fi.pa165.liquorbottles.service.services.impl.ProducerServiceImpl;
import muni.fi.pa165.liquorbottles.service.services.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDTO user = new UserDTO();
        user.setUsername("Fero");
        user.setPassword("1234567");
        
        UserService userService = (UserService) applicationContext.getBean(UserService.class);

        userService.insertUser(user);
        
        System.out.println("Hello world!");
    }
}
