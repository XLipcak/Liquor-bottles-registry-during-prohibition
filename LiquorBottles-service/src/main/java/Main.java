import java.util.Date;
import muni.fi.pa165.liquorbottles.service.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.service.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.service.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.service.services.BottleService;
import muni.fi.pa165.liquorbottles.service.services.BottleTypeService;
import muni.fi.pa165.liquorbottles.service.services.ProducerService;
import muni.fi.pa165.liquorbottles.service.services.StoreService;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        ProducerDTO producer = new ProducerDTO();
        producer.setAddress("Botanicka");
        producer.setName("Alko");
        producer.setPassword("xxx");
        producer.setUsername("Hello");

        ProducerService producerService = (ProducerService) applicationContext.getBean(ProducerService.class);
        producerService.insertProducer(producer);

        BottleTypeDTO type = new BottleTypeDTO();
        type.setAlcType("123456");
        type.setName("Bororo");
        type.setPower(9);
        type.setProducer(producer);
        type.setVolume(123);

        BottleTypeService bottleTypeService = (BottleTypeService) applicationContext.
                getBean(BottleTypeService.class);
        bottleTypeService.insertBottleType(type);

        StoreDTO store = new StoreDTO();
        store.setAddress("adresa");
        store.setName("obchod");
        store.setUsername("Tesco");
        store.setPassword("12");

        StoreService storeService = (StoreService) applicationContext.
                getBean(StoreService.class);
        storeService.insertStore(store);

        BottleDTO bottle = new BottleDTO();
        bottle.setBatchNumber(123456);
        bottle.setDateOfBirth(new Date(new Date().getTime()));
        bottle.setStamp(123456);
        bottle.setToxicity(ToxicityDTO.TOXIC);
        bottle.setBottleType(type);
        bottle.setStore(store);

        BottleService bottleService = (BottleService) applicationContext.
                getBean(BottleService.class);
        bottleService.insertBottle(bottle);

        System.out.println("Kubek");
    }
}
