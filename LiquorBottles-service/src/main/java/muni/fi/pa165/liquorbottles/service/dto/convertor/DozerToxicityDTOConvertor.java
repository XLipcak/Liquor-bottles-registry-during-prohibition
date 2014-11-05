package muni.fi.pa165.liquorbottles.service.dto.convertor;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.service.dto.ToxicityDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author Michal Taraj
 */
public class DozerToxicityDTOConvertor implements DTOConvertor<Toxicity, ToxicityDTO> {
    
    @Override
    public ToxicityDTO fromEntityToDTO(Toxicity entity) {
        Mapper mapper = new DozerBeanMapper();
        ToxicityDTO toxicityDTO = mapper.map(entity, ToxicityDTO.class);
        return toxicityDTO;
    }

    @Override
    public Toxicity fromDTOToEntity(ToxicityDTO dto) {
        Mapper mapper = new DozerBeanMapper();
        Toxicity toxicity = mapper.map(dto, Toxicity.class);

        return toxicity;
    }

    @Override
    public List<ToxicityDTO> fromEntityToDTO(List<Toxicity> entityList) {
        List<ToxicityDTO> resultDTO = new ArrayList<>();
        for (Toxicity t : entityList) {
            resultDTO.add(fromEntityToDTO(t));
        }
        return resultDTO;
    }

    @Override
    public List<Toxicity> fromDTOToEntity(List<ToxicityDTO> dtoList) {
        List<Toxicity> result = new ArrayList<>();
        for (ToxicityDTO t : dtoList) {
            result.add(fromDTOToEntity(t));
        }
        return result;
    }
}
