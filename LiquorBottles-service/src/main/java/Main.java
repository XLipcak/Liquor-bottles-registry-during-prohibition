import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.ProducerDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.service.dto.PoliceDTO;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.services.PoliceService;
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

        PoliceDTO policeDTO = new PoliceDTO();
        policeDTO.setAddress("policajt");
        policeDTO.setName("Debil");
        policeDTO.setUsername("Imrich");
        policeDTO.setPassword("NBU123");

        PoliceService service2 = (PoliceService) applicationContext.getBean(PoliceService.class);
        service2.insertPolice(policeDTO);

        System.out.println("Hello world!");
    }
}
