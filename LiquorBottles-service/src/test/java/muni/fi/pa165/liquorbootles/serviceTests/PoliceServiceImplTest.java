/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbootles.serviceTests;

import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.PoliceDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import muni.fi.pa165.liquorbottles.api.dto.PoliceDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerPoliceDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.impl.PoliceServiceImpl;
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
public class PoliceServiceImplTest {
    
    @Mock 
    private PoliceDAOImpl mockPoliceDaoImpl;
    
    @InjectMocks
    private PoliceServiceImpl policeServiceImpl;
    
    private PoliceDTO policeDto;
    
    private DozerPoliceDTOConvertor dozerPoliceDtoConvertor;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        dozerPoliceDtoConvertor = new DozerPoliceDTOConvertor();
        
        policeDto = new PoliceDTO();
        policeDto.setAddress("Dokl;iadkova 14");
        policeDto.setName("Stanica");
        policeDto.setUsername("Polis");
        policeDto.setPassword("pass");
    }
    
    @After
    public void tearDown() {
        this.dozerPoliceDtoConvertor = null;
    }
    
    @Test
    public void insertPolice(){
        System.out.println("Testing police service insert");
        
        policeServiceImpl.insertPolice(policeDto);
        Police police = dozerPoliceDtoConvertor.fromDTOToEntity(policeDto);
        
        Mockito.verify(mockPoliceDaoImpl).insertPolice(police);
    }

    @Test
    public void updatePolice(){
        System.out.println("Testing police service insert");
        
        policeServiceImpl.updatePolice(policeDto);
        Police police = dozerPoliceDtoConvertor.fromDTOToEntity(policeDto);
        
        Mockito.verify(mockPoliceDaoImpl).updatePolice(police);
    }

    @Test
    public void deletePolice(){
        System.out.println("Testing police service insert");
        
        policeServiceImpl.deletePolice(policeDto);
        Police police = dozerPoliceDtoConvertor.fromDTOToEntity(policeDto);
        
        Mockito.verify(mockPoliceDaoImpl).deletePolice(police);}
}
