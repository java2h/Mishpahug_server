package application.models.user;

import application.entities.UserEntity;
import application.repositories.CityRepository;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import java.util.HashMap;
@Service
@Transactional
public class UserModel implements IUserModel {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(Integer userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public List<UserEntity> getByFilter(HashMap<String, String> filter) {
        return userRepository.searchByFilter(filter);
    }

    @Override
    public UserEntity add(UserEntity data) {
        return userRepository.save(data);
    }


    @Override
    public UserEntity update(Integer userId, HashMap<String, String> data)
    {
        return userRepository.save(userRepository.update(userId,data));
    }

    @Override
    public UserEntity remove(Integer userId) {
        UserEntity usr = userRepository.getOne(userId);
        userRepository.deleteById(userId);
        return usr;
    }

    @Override
    public UserEntity getByName(String name) {
        return userRepository.findByUserName(name);
    }

}