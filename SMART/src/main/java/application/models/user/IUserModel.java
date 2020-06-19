package application.models.user;

import application.dtoes.UserDTO;
import application.entities.UserEntity;
import com.querydsl.core.types.Predicate;

import java.util.HashMap;
import java.util.List;

public interface IUserModel {
    UserEntity getByUsernameAndPassword(String username, String password);

    public Iterable<UserEntity> getAll(Predicate predicate);

    public UserEntity getById(Integer userId);

    public UserEntity getByUserName(String name);

    public UserEntity add(UserDTO data);

    public UserEntity update(String username,
                             HashMap<String, String> data);

    public UserEntity deleteByID(Integer userId);

    public List<UserEntity> deleteAll();

    public UserEntity activateByID(Integer userId);

    public UserEntity deactivateByID(Integer userId);

    public UserEntity prepareForDeletionByID(Integer userId);


}
