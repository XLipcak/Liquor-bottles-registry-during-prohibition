/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.serviceLayer.dto.convertor;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;
import muni.fi.pa165.liquorbottles.serviceLayer.dto.UserDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Convertor for User entity using Dozer framework.
 *
 * @author Matus Novak, Masaryk University
 */
public class DozerUserDTOConvertor implements DTOConvertor<User, UserDTO> {

    @Override
    public UserDTO fromEntityToDTO(User entity) {
        Mapper mapper = new DozerBeanMapper();
        UserDTO userDTO = mapper.map(entity, UserDTO.class);

        return userDTO;
    }

    @Override
    public User fromDTOToEntity(UserDTO dto) {
        Mapper mapper = new DozerBeanMapper();
        User user = mapper.map(dto, User.class);

        return user;
    }

    @Override
    public List<UserDTO> fromEntityToDTO(List<User> entityList) {
        List<UserDTO> resultList = new ArrayList<>();

        for (User user : entityList) {
            resultList.add(fromEntityToDTO(user));
        }
        return resultList;
    }

    @Override
    public List<User> fromDTOToEntity(List<UserDTO> dtoList) {
        List<User> resultList = new ArrayList<>();

        for (UserDTO userDto : dtoList) {
            resultList.add(fromDTOToEntity(userDto));
        }
        return resultList;
    }

}
