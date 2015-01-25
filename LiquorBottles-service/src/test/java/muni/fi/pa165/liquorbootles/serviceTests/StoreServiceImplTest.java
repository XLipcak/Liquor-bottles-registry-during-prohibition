package muni.fi.pa165.liquorbootles.serviceTests;

import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.StoreDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import muni.fi.pa165.liquorbottles.api.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerStoreDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.impl.StoreServiceImpl;
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
public class StoreServiceImplTest {
    
    @Mock
    private StoreDAOImpl mockStoreDaoImpl;
    
    @InjectMocks
    private StoreServiceImpl storeServiceImpl;
    
    private DozerStoreDTOConvertor dozerStoreDtoConvertor;
    
    private StoreDTO storeDto;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        dozerStoreDtoConvertor = new DozerStoreDTOConvertor();
        
        storeDto = new StoreDTO();
        storeDto.setAddress("Bojnicka 69");
        storeDto.setName("Billa");
        storeDto.setPassword("pas");
        storeDto.setUsername("user");
        
    }
    
    @After
    public void tearDown() {
        dozerStoreDtoConvertor = null;
        storeDto = null;
    }
    
    @Test
    public void insertStore(){
        System.out.println("Testing store service insert method");
        storeServiceImpl.insertStore(storeDto);
        
        Store store = dozerStoreDtoConvertor.fromDTOToEntity(storeDto);
        Mockito.verify(mockStoreDaoImpl).insertStore(store);
    }

    @Test
    public void updateStore(){
        System.out.println("Testing store service update method");
        storeServiceImpl.updateStore(storeDto);
        
        Store store = dozerStoreDtoConvertor.fromDTOToEntity(storeDto);
        Mockito.verify(mockStoreDaoImpl).updateStore(store);
    }

    @Test
    public void delteStore(){
        System.out.println("Testing store service delete method");
        storeServiceImpl.deleteStore(storeDto);
        
        Store store = dozerStoreDtoConvertor.fromDTOToEntity(storeDto);
        Mockito.verify(mockStoreDaoImpl).deleteStore(store);
    }

}
