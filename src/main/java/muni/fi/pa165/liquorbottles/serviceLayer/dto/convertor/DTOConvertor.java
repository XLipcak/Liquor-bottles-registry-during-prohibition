package muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor;

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
}
