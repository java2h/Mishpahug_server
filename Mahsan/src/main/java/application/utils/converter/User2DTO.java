package application.utils.converter;

import application.dtoes.UserDTO;
import application.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class User2DTO {
    public static List<UserDTO> userDTOList(Iterable<UserEntity> data){
        List<UserDTO> res = new ArrayList<>();
        data.forEach(x ->{
            res.add(new UserDTO(x));
        });
        return res;
    }
}
