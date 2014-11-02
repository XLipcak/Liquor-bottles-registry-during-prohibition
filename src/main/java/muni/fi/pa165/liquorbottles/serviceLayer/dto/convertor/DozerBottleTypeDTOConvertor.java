/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.BottleType;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.BottleTypeDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Convertor for BottleType entity using Dozer framework.
 *
 * @author Michal Taraj, Masaryk University
 */

public class DozerBottleTypeDTOConvertor implements DTOConvertor<BottleType, BottleTypeDTO> {
    
    @Override
    public BottleTypeDTO fromEntityToDTO(BottleType entity) {
        Mapper mapper = new DozerBeanMapper();
        BottleTypeDTO bottleTypeDTO = mapper.map(entity, BottleTypeDTO.class);

        return bottleTypeDTO;
    }

    @Override
    public BottleType fromDTOToEntity(BottleTypeDTO dto) {
        Mapper mapper = new DozerBeanMapper();
        BottleType bottleType = mapper.map(dto, BottleType.class);

        return bottleType;
    }

    @Override
    public List<BottleTypeDTO> fromEntityToDTO(List<BottleType> entityList) {
        List<BottleTypeDTO> resultDTO = new ArrayList<>();
        for (BottleType b : entityList) {
            resultDTO.add(fromEntityToDTO(b));
        }
        return resultDTO;
    }

    @Override
    public List<BottleType> fromDTOToEntity(List<BottleTypeDTO> dtoList) {
        List<BottleType> result = new ArrayList<>();
        for (BottleTypeDTO b : dtoList) {
            result.add(fromDTOToEntity(b));
        }
        return result;
    }
}
