/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor;

import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Police;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.PoliceDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


/**
 * Convertor for Police entity using Dozer framework.
 *
 * @author Matus Novak, Masaryk University
 */
public class DozerPoliceDTOConvertor implements DTOConvertor<Police, PoliceDTO>{

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
    
}
