package muni.fi.pa165.liquorbottles.service.dto.convertor;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Bottle;
import muni.fi.pa165.liquorbottles.service.dto.BottleDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Convertor for Bottle entity using Dozer framework.
 *
 * @author Michal Taraj, Masaryk University
 */
public class DozerBottleDTOConvertor implements DTOConvertor<Bottle, BottleDTO> {

    @Override
    public BottleDTO fromEntityToDTO(Bottle entity) {
        Mapper mapper = new DozerBeanMapper();
        BottleDTO bottleDTO = mapper.map(entity, BottleDTO.class);
        return bottleDTO;
    }

    @Override
    public Bottle fromDTOToEntity(BottleDTO dto) {
        Mapper mapper = new DozerBeanMapper();
        Bottle bottle = mapper.map(dto, Bottle.class);

        return bottle;
    }

    @Override
    public List<BottleDTO> fromEntityToDTO(List<Bottle> entityList) {
        List<BottleDTO> resultDTO = new ArrayList<>();
        for (Bottle b : entityList) {
            resultDTO.add(fromEntityToDTO(b));
        }
        return resultDTO;
    }

    @Override
    public List<Bottle> fromDTOToEntity(List<BottleDTO> dtoList) {
        List<Bottle> result = new ArrayList<>();
        for (BottleDTO b : dtoList) {
            result.add(fromDTOToEntity(b));
        }
        return result;
    }

}
