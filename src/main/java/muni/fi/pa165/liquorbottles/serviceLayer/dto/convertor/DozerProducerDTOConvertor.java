package muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor;

import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.ProducerDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Convertor for Producer entity using Dozer framework.
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class DozerProducerDTOConvertor implements DTOConvertor<Producer, ProducerDTO> {

    @Override
    public ProducerDTO fromEntityToDTO(Producer entity) {
        Mapper mapper = new DozerBeanMapper();
        ProducerDTO producerDTO = mapper.map(entity, ProducerDTO.class);

        return producerDTO;
    }

    @Override
    public Producer fromDTOToEntity(ProducerDTO dto) {
        Mapper mapper = new DozerBeanMapper();
        Producer producer = mapper.map(dto, Producer.class);

        return producer;
    }

    @Override
    public List<ProducerDTO> fromEntityToDTO(List<Producer> entityList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producer> fromDTOToEntity(List<ProducerDTO> dtoList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
