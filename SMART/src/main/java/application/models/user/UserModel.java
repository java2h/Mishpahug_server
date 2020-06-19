package application.models.user;

import application.dtoes.UserDTO;
import application.entities.UserEntity;
import application.repositories.UserRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserModel implements IUserModel {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity getByUsernameAndPassword(String username, String password) {
        return userRepository.findByUserNameAndAndEncrytedPassword(username, password);
    }

    @Override
    public Iterable<UserEntity> getAll(Predicate predicate) {
        return userRepository.findAll(predicate);
    }

    @Override
    public UserEntity getById(Integer userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public UserEntity getByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public UserEntity add(UserDTO data) {
         return userRepository.save(new UserEntity((data)));
    }

    @Override
    public UserEntity update(String username,
                             HashMap<String, String> data){
        UserEntity user = userRepository.findByUserName(username);
        //TODO едактирование данных пользователя
        // if (userRepository.existsByUserNameAndEMail(user.getUserName(), user.getEMail())) throw new EntityExistsException(""); 
        // we're trying to update existing user. throwing an exception if it exists will stop our job. 
        userRepository.save(user);
        return user;
    }

    /**
     * Deletes the user skipping checks
     */
    @Override
    public UserEntity deleteByID(Integer userId) {
        UserEntity usr = userRepository.getOne(userId);
        usr.putIntoDeletionQueue();
        userRepository.deleteById(userId);
        return usr;
    }

    /**
     * Deletes all users skipping checks
     */
    @Override
    public List<UserEntity> deleteAll() {
        List<UserEntity> all = userRepository.findAll();
        for (UserEntity user : all) {
            user.putIntoDeletionQueue();
        }
        userRepository.deleteAll();
        return all;
    }

    /**
     * Activates the user, activating all his "deactivated" events and subscriptions;
     */
    @Override
    public UserEntity activateByID(Integer userId) {
        UserEntity usr = userRepository.getOne(userId);
        usr.activate();
        return usr;
    }

    /**
     * Deactivates the user, all his events and subscriptions;
     */
    @Override
    public UserEntity deactivateByID(Integer userId) {
        UserEntity usr = userRepository.getOne(userId);
        usr.deactivate();
        return usr;
    }

    /**
     * Puts the user into deletion queue;
     */
    @Override
    public UserEntity prepareForDeletionByID(Integer userId) {
        UserEntity usr = userRepository.getOne(userId);
        usr.putIntoDeletionQueue();
        return null;
    }
}
