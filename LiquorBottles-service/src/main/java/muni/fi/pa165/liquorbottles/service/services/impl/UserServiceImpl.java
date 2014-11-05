package muni.fi.pa165.liquorbottles.service.services.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.UserDAO;
import muni.fi.pa165.liquorbottles.persistenceLayer.dao.impl.UserDAOImpl;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.User;
import muni.fi.pa165.liquorbottles.service.dto.UserDTO;
import muni.fi.pa165.liquorbottles.service.dto.convertor.DozerUserDTOConvertor;
import muni.fi.pa165.liquorbottles.service.services.UserService;
import org.springframework.dao.NonTransientDataAccessResourceException;

/**
 *
 * @author Michal Štora, Masaryk University
 */
public class UserServiceImpl implements UserService {

    //replace by Spring injection later
    EntityManagerFactory emf;
    private UserDAO userDAOimpl;
    private DozerUserDTOConvertor userDTOConvertor = new DozerUserDTOConvertor();
    
    public UserServiceImpl(){
        emf = Persistence.createEntityManagerFactory(
                "localDB");
        userDAOimpl = new UserDAOImpl(emf);
    }
    
    public void closeEMF(){
        this.emf.close();
    }
    
    @Override
    public List<UserDTO> findAll() {
        try{
            List<User> user = userDAOimpl.findAll();
            return userDTOConvertor.fromEntityToDTO(user);
        } catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public UserDTO findById(long id) {
        try{
            User user = userDAOimpl.findById(id);
            return userDTOConvertor.fromEntityToDTO(user);
        }catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public UserDTO findByUsername(String userName) {
        try{
            User user = userDAOimpl.findByUsername(userName);
            return userDTOConvertor.fromEntityToDTO(user);
        }catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public String findPassByUsername(String userName) {
        try{
            return userDAOimpl.findPassByUsername(userName);
        }catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public void insertUser(UserDTO userDTO) {
        try{
            User user = userDTOConvertor.fromDTOToEntity(userDTO);
            userDAOimpl.insertUser(user);
            userDTO.setId(user.getId());
        }catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public void deleteUser(UserDTO userDTO) {
        try{
            User user = userDTOConvertor.fromDTOToEntity(userDTO);
            userDAOimpl.deleteUser(user);
        }catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        try{
            User user = userDTOConvertor.fromDTOToEntity(userDTO);
            userDAOimpl.updateUser(user);
        }catch (PersistenceException ex) {
            throw new NonTransientDataAccessResourceException("Operation failed!");     
        }
    }

}
