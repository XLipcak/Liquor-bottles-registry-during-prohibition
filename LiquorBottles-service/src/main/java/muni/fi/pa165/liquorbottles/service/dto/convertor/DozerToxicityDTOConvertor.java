package muni.fi.pa165.liquorbottles.service.dto.convertor;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.api.dto.ToxicityDTO;

/**
 *
 * @author Michal Taraj
 */
public class DozerToxicityDTOConvertor implements DTOConvertor<Toxicity, ToxicityDTO> {

    @Override
    public ToxicityDTO fromEntityToDTO(Toxicity entity) {

        if (entity == Toxicity.TOXIC) {
            return ToxicityDTO.TOXIC;
        }
        if (entity == Toxicity.NON_TOXIC) {
            return ToxicityDTO.NON_TOXIC;
        }
        if (entity == Toxicity.UNCHECKED) {
            return ToxicityDTO.UNCHECKED;
        }

        return ToxicityDTO.TOXIC;
    }

    @Override
    public Toxicity fromDTOToEntity(ToxicityDTO dto) {
        if (dto == ToxicityDTO.TOXIC) {
            return Toxicity.TOXIC;
        }
        if (dto == ToxicityDTO.NON_TOXIC) {
            return Toxicity.NON_TOXIC;
        }
        if (dto == ToxicityDTO.UNCHECKED) {
            return Toxicity.UNCHECKED;
        }

        return Toxicity.TOXIC;
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
