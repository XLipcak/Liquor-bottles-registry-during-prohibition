package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;
import muni.fi.pa165.liquorbottles.api.dto.UserDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerUserDTOConvertor;
import muni.fi.pa165.liquorbottles.api.services.UserService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private DozerUserDTOConvertor userDTOConvertor = new DozerUserDTOConvertor();

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<UserDTO> findAll() {
        try {
            List<User> user = userDAO.findAll();
            return userDTOConvertor.fromEntityToDTO(user);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_STORE", "ROLE_PRODUCER"})
    public UserDTO findById(long id) {
        try {
            User user = userDAO.findById(id);
            return userDTOConvertor.fromEntityToDTO(user);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public UserDTO findByUsername(String userName) {
        try {
            User user = userDAO.findByUsername(userName);
            return userDTOConvertor.fromEntityToDTO(user);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public String findPassByUsername(String userName) {
        try {
            return userDAO.findPassByUsername(userName);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void insertUser(UserDTO userDTO) {
        try {
            User user = userDTOConvertor.fromDTOToEntity(userDTO);
            userDAO.insertUser(user);
            userDTO.setId(user.getId());
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void deleteUser(UserDTO userDTO) {
        try {
            User user = userDTOConvertor.fromDTOToEntity(userDTO);
            userDAO.deleteUser(user);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void updateUser(UserDTO userDTO) {
        try {
            User user = userDTOConvertor.fromDTOToEntity(userDTO);
            userDAO.updateUser(user);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!", ex);
        }
    }

    @Required
    @Secured({"ROLE_ADMIN"})
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
