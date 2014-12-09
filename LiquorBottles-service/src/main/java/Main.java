import java.util.Date;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
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
        bottle.setBatchNumber(1);
        bottle.setDateOfBirth(new Date(new Date().getTime()));
        bottle.setStamp(2);
        bottle.setToxicity(ToxicityDTO.TOXIC);
        bottle.setBottleType(type);
        bottle.setStore(store);

        BottleDTO bottle2 = new BottleDTO();
        bottle2.setBatchNumber(10);
        bottle2.setDateOfBirth(new Date(new Date().getTime()));
        bottle2.setStamp(2);
        bottle2.setToxicity(ToxicityDTO.TOXIC);
        bottle2.setBottleType(type);
        bottle2.setStore(store);

        BottleDTO bottle3 = new BottleDTO();
        bottle3.setBatchNumber(1);
        bottle3.setDateOfBirth(new Date(new Date().getTime()));
        bottle3.setStamp(20);
        bottle3.setToxicity(ToxicityDTO.TOXIC);
        bottle3.setBottleType(type);
        bottle3.setStore(store);

        BottleService bottleService = (BottleService) applicationContext.
                getBean(BottleService.class);
        bottleService.insertBottle(bottle);
     //  bottleService.insertBottle(bottle2);

        //toxicity convert problem TODO
        List<BottleDTO> resultList = bottleService.findByFilter(
                1, 
                1, 
                2,
                ToxicityDTO.TOXIC, 
                new Date(2014, 11, 28, 20, 55, 0),
                new Date(2014, 11, 28, 21, 55, 0),
                1, 
                2
        );
        
        List<StoreDTO> resultStoreList = storeService.findByFilter("obchod", "adresa");

        System.out.println("vypis");
        //System.out.println(resultList.toString());
        System.out.print(resultStoreList.toString());

    }
}
