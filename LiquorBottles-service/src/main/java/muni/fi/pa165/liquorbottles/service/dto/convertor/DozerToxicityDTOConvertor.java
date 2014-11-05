/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Toxicity;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.ToxicityDTO;
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
