package muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor;

import java.util.List;

/**
 * DTO Convertor
 *
 * @author Jakub Lipcak, Masaryk University
 */
public interface DTOConvertor<Entity, DTO> {

    /**
     * Converts entity into Data Transfer object.
     *
     * @param entity entity to convert
     * @return Data Transfer object
     */
    DTO fromEntityToDTO(Entity entity);

    /**
     * Converts Data Transfer object into entity.
     *
     * @param dto Data Transfer object to convert
     * @return entity
     */
    Entity fromDTOToEntity(DTO dto);

    /**
     * Converts List of Entity objects into DTOList.
     *
     * @param entityList List of entities objects to convert
     * @return dtoList
     */
    List<DTO> fromEntityToDTO(List<Entity> entityList);

    /**
     * Converts List of Data Transfer object into entityList.
     *
     * @param dtoList List of Data Transfer objects to convert
     * @return entityList
     */
    List<Entity> fromDTOToEntity(List<DTO> dtoList);
}
