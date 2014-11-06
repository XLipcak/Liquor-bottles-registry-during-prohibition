import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.services.ProducerService;
import muni.fi.pa165.liquorbottles.service.services.impl.ProducerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        ProducerDTO producerDTO = new ProducerDTO();
        producerDTO.setAddress("Botanicka");
        producerDTO.setName("Java guru");
        producerDTO.setUsername("Prohibition");
        producerDTO.setPassword("12345");

        ProducerService service = (ProducerService) applicationContext.getBean(ProducerService.class); 
        service.insertProducer(producerDTO);
        
        System.out.println("Hello world!");
    }
}
