package application.controllers;

import application.entities.UserEntity;
import application.entities.security.UserSession;
import application.models.user.IUserModel;
import application.repositories.UserSessionRepository;
import com.querydsl.core.types.Predicate;
import application.dtoes.LoginDTO;
import application.dtoes.LoginResponse;
import application.dtoes.LogoutResponse;
import application.dtoes.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserModel userModel;

    @Autowired
    UserSessionRepository userSessionRepository;


    @PutMapping(value = "/{id}")
    public UserEntity update(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                          @RequestBody HashMap<String, String> data, @PathVariable(value = "id") Integer id) {
        return userModel.update(id, data);
    }


    @DeleteMapping(value = "/{id}")
    public UserEntity delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                          @PathVariable(value = "id") Integer id) {
        return userModel.deleteByID(id);
    }


    @DeleteMapping(value = "/")
    public void deleteAll(@RequestHeader HttpHeaders httpHeaders,
                          HttpServletRequest request){
        userModel.deleteAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<UserDTO> findAllByWebQuerydsl(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                                                 @QuerydslPredicate(root = UserEntity.class) Predicate predicate) {
        List<UserDTO> userDTOS = new ArrayList<>();
        userModel.getAll(predicate).forEach(x -> userDTOS.add(new UserDTO(x)));
        return userDTOS;
    }

    @PostMapping(value = "/")
    public void registration(@RequestHeader HttpHeaders httpHeaders,
                             HttpServletRequest request, @RequestBody UserDTO userDTO) {
        userModel.add(userDTO);
    }

}
