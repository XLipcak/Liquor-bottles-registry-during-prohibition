/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbootles.serviceTests;

import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.BottleTypeDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.ProducerDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerProducerDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.impl.ProducerServiceImpl;
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
public class ProducerServiceImplTest {
    
    @Mock 
    private ProducerDAOImpl mockProducerDaoImpl;
    @Mock 
    private BottleTypeDAOImpl bottleTypeDAOImpl;
    
    @InjectMocks
    private ProducerServiceImpl producerSeviceImpl;
    
    private DozerProducerDTOConvertor dozerProducerConvertor;
    
    private ProducerDTO producerDto;
    
    public ProducerServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        dozerProducerConvertor = new DozerProducerDTOConvertor();
        
        producerDto = new ProducerDTO();
        producerDto.setAddress("Vyrobna 6");
        producerDto.setName("Absolut");
        producerDto.setUsername("Destilere");
        producerDto.setPassword("alko");
    }
    
    @After
    public void tearDown() {
        this.dozerProducerConvertor = null;
        this.producerDto = null;
    }

   
    @Test
    public void insertProducer(){
        System.out.println("Testing producer service insert");
        
        producerSeviceImpl.insertProducer(producerDto);
        Producer producer = dozerProducerConvertor.fromDTOToEntity(producerDto);
        Mockito.verify(mockProducerDaoImpl).insertProducer(producer);
    }

    @Test
    public void updateProducer(){
        System.out.println("Testing producer service insert");
        
        producerSeviceImpl.updateProducer(producerDto);
        Producer producer = dozerProducerConvertor.fromDTOToEntity(producerDto);
        Mockito.verify(mockProducerDaoImpl).updateProducer(producer);
    }

    @Test
    public void deleteProducer(){
        System.out.println("Testing producer service insert");
        
        producerSeviceImpl.deleteProducer(producerDto);
        Producer producer = dozerProducerConvertor.fromDTOToEntity(producerDto);
        Mockito.verify(mockProducerDaoImpl).deleteProducer(producer);
    }
}
