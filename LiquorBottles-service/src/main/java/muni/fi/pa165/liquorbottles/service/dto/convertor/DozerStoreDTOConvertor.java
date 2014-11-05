package muni.fi.pa165.liquorbottles.service.dto.convertor;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;
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
        List<StoreDTO> resultList = new ArrayList<>();
        for (Store s : entityList) {
            resultList.add(fromEntityToDTO(s));
        }
        return resultList;
    }

    @Override
    public List<Store> fromDTOToEntity(List<StoreDTO> dtoList) {
        List<Store> resultList = new ArrayList<>();
        for (StoreDTO s : dtoList) {
            resultList.add(fromDTOToEntity(s));
        }
        return resultList;
    }

}
