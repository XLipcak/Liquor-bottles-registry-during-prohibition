/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbootles.serviceTests;

import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.BottleTypeDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerBottleTypeDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.impl.BottleTypeServiceImpl;
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
public class BottleTypeServiceImplTest {
    
    @Mock
    private BottleTypeDAOImpl mockBottleTypeDaoImpl;
    
    @InjectMocks
    private BottleTypeServiceImpl bottleTypeServiceImpl;
    
    private BottleTypeDTO bottleTypeDto;
    private ProducerDTO producerDto;  
    
    private DozerBottleTypeDTOConvertor dozerBottleTypeDtoConvertor;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        dozerBottleTypeDtoConvertor = new DozerBottleTypeDTOConvertor();
        
        producerDto = new ProducerDTO();
        producerDto.setAddress("Bojnicka 68");
        producerDto.setName("Bozkov");
        producerDto.setPassword("pass");
        producerDto.setUsername("use");
        
        bottleTypeDto = new BottleTypeDTO();
        bottleTypeDto.setAlcType("vodka");
        bottleTypeDto.setName("prizracna");
        bottleTypeDto.setPower(40);
        bottleTypeDto.setVolume(700);
        bottleTypeDto.setProducer(producerDto);
        
    }
    
    @After
    public void tearDown() {
        this.bottleTypeDto = null;
        this.dozerBottleTypeDtoConvertor = null;
    }
    
    @Test
    public void insertBottleType(){
        System.out.println("Testing insert BottleType service");
        
        bottleTypeServiceImpl.insertBottleType(bottleTypeDto);
        BottleType bottleType = dozerBottleTypeDtoConvertor.fromDTOToEntity(bottleTypeDto);
        
        Mockito.verify(mockBottleTypeDaoImpl).insertBottleType(bottleType);
    }

    @Test
    public void updateBottleType(){
        System.out.println("Testing insert BottleType service");
        
        bottleTypeServiceImpl.updateBottleType(bottleTypeDto);
        BottleType bottleType = dozerBottleTypeDtoConvertor.fromDTOToEntity(bottleTypeDto);
        
        Mockito.verify(mockBottleTypeDaoImpl).updateBottleType(bottleType);
    }

    @Test
    public void deleteBottleType(){
        System.out.println("Testing insert BottleType service");
        
        bottleTypeServiceImpl.deleteBottleType(bottleTypeDto);
        BottleType bottleType = dozerBottleTypeDtoConvertor.fromDTOToEntity(bottleTypeDto);
        
        Mockito.verify(mockBottleTypeDaoImpl).deleteBottleType(bottleType);
    }
}
