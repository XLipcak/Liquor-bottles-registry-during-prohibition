package muni.fi.pa165.liquorbootles.serviceTests;

import java.util.Date;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.BottleDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;
import muni.fi.pa165.liquorbottles.service.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.service.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.service.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerBottleDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.BottleService;
import muni.fi.pa165.liquorbottles.service.services.impl.BottleServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class BottleServiceImplTest {

    private DozerBottleDTOConvertor dozerBottleDtoConvertor = null;

    @Mock
    private BottleDAOImpl mockBottleDaoImpl;

    private ProducerDTO producerDto;
    private StoreDTO storeDto;
    private BottleTypeDTO bottleTypeDto;
    private BottleDTO bottleDto;

    private BottleService bottleService;

    @InjectMocks
    private BottleServiceImpl bottleServiceImpl;

    public BottleServiceImplTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        dozerBottleDtoConvertor = new DozerBottleDTOConvertor();

        producerDto = new ProducerDTO();
        producerDto.setAddress("Bojnicka 68");
        producerDto.setName("Bozkov");
        producerDto.setPassword("pass");
        producerDto.setUsername("use");

        storeDto = new StoreDTO();
        storeDto.setAddress("Bojnicka 69");
        storeDto.setName("Billa");
        storeDto.setPassword("pas");
        storeDto.setUsername("user");

        bottleTypeDto = new BottleTypeDTO();
        bottleTypeDto.setAlcType("vodka");
        bottleTypeDto.setName("prizracna");
        bottleTypeDto.setPower(40);
        bottleTypeDto.setVolume(700);
        bottleTypeDto.setProducer(producerDto);

        bottleDto = new BottleDTO();
        bottleDto.setBottleType(bottleTypeDto);
        bottleDto.setStore(storeDto);
        bottleDto.setBatchNumber(24);
        bottleDto.setDateOfBirth(new Date());
        bottleDto.setToxicity(ToxicityDTO.TOXIC);

    }

    @After
    public void tearDown() {
        this.producerDto = null;
        this.storeDto = null;
        this.bottleTypeDto = null;
        this.bottleDto = null;
        this.dozerBottleDtoConvertor = null;

    }

    @Test
    public void insertBottle() {
        System.out.println("Testing insert bottle on BottleService");
        bottleServiceImpl.insertBottle(bottleDto);
        Bottle bottle = dozerBottleDtoConvertor.fromDTOToEntity(bottleDto);

        Mockito.verify(mockBottleDaoImpl).insertBottle(bottle);
    }

    @Test
    public void updateBottle() {
        System.out.println("Testing update bottle on BottleService");
        bottleServiceImpl.updateBottle(bottleDto);
        Bottle bottle = dozerBottleDtoConvertor.fromDTOToEntity(bottleDto);

        Mockito.verify(mockBottleDaoImpl).updateBottle(bottle);
    }

    @Test
    public void deleteBottle() {

        System.out.println("Testing delete bottle on BottleService");
        bottleServiceImpl.deleteBottle(bottleDto);
        Bottle bottle = dozerBottleDtoConvertor.fromDTOToEntity(bottleDto);

        Mockito.verify(mockBottleDaoImpl).deleteBottle(bottle);
    }
}
