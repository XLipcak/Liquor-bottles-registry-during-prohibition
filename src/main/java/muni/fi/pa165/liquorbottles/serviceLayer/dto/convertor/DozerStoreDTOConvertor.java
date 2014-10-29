package muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.StoreDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Convertor for Store entity using Dozer framework.
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class DozerStoreDTOConvertor implements DTOConvertor<Store, StoreDTO> {

    @Override
    public StoreDTO fromEntityToDTO(Store entity) {
        Mapper mapper = new DozerBeanMapper();
        StoreDTO storeDTO = mapper.map(entity, StoreDTO.class);

        return storeDTO;
    }

    @Override
    public Store fromDTOToEntity(StoreDTO dto) {
        Mapper mapper = new DozerBeanMapper();
        Store store = mapper.map(dto, Store.class);

        return store;
    }

    @Override
    public List<StoreDTO> fromEntityToDTO(List<Store> entityList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Store> fromDTOToEntity(List<StoreDTO> dtoList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
