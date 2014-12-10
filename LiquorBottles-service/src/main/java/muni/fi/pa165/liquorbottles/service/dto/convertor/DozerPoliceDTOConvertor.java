package muni.fi.pa165.liquorbottles.service.dto.convertor;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import muni.fi.pa165.liquorbottles.api.dto.PoliceDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Convertor for Police entity using Dozer framework.
 *
 * @author Matus Novak, Masaryk University
 */
public class DozerPoliceDTOConvertor implements DTOConvertor<Police, PoliceDTO> {

    @Override
    public PoliceDTO fromEntityToDTO(Police entity) {
        Mapper mapper = new DozerBeanMapper();
        PoliceDTO policeDTO = mapper.map(entity, PoliceDTO.class);

        return policeDTO;
    }

    @Override
    public Police fromDTOToEntity(PoliceDTO dto) {
        Mapper mapper = new DozerBeanMapper();
        Police police = mapper.map(dto, Police.class);

        return police;
    }

    @Override
    public List<PoliceDTO> fromEntityToDTO(List<Police> entityList) {
        List<PoliceDTO> resultList = new ArrayList<>();
        for (Police p : entityList) {
            resultList.add(fromEntityToDTO(p));
        }
        return resultList;
    }

    @Override
    public List<Police> fromDTOToEntity(List<PoliceDTO> dtoList) {
        List<Police> resultList = new ArrayList<>();
        for (PoliceDTO p : dtoList) {
            resultList.add(fromDTOToEntity(p));
        }
        return resultList;
    }

}
