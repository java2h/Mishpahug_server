package Application.models.user;

import Application.entities.UserEntity;
import Application.repo.CityRepository;
import Application.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
@Service
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
    /*          keys for update
    nickname
    firstname
    lastname
    email
    address.build
    address.city
    address.apartment
    address.street
    telephone
     */
    /*нужно протестировать не уверен в работе кода между !!!.
    * проверен вариант зактнуть логику в сущноть... не подойдет,
     * так как нужен поиск города по имени*/
    @Override
    public UserEntity update(Integer userId, HashMap<String, String> data) {
        UserEntity userEntity = userRepository.getOne(userId);
        if (data.containsKey("nickname")) userEntity.setNickname(data.get("nickname"));
        if (data.containsKey("lastname")) userEntity.setLastName(data.get("lastname"));
        if (data.containsKey("firstname")) userEntity.setFirstName(data.get("firstname"));
        if (data.containsKey("email")) userEntity.setEMail(data.get("email"));
        if (data.containsKey("telephone")) userEntity.setPhoneNumber(data.get("telephone"));
        /*!!!*/
        if (data.containsKey("address.build"))
            userEntity.getAddressEntity().setBuild(Integer.getInteger(data.get("address.build")));
        if (data.containsKey("address.apartment"))
            userEntity.getAddressEntity().setApartment(Integer.getInteger(data.get("address.apartment")));
        if ((data.containsKey("address.city"))
                && (cityRepository.getByFullName(data.get("address.city")) != null))
            userEntity.getAddressEntity().setCityEntity(cityRepository.getByFullName(data.get("address.city")));
        if (data.containsKey("address.street"))
            userEntity.getAddressEntity().setStreet(data.get("address.street"));
        /*!!!*/
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity remove(Integer userId) {
        UserEntity usr = userRepository.getOne(userId);
        userRepository.deleteById(userId);
        return usr;
    }

}
