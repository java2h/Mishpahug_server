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
import java.util.HashMap;
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
    public Iterable<UserEntity> findAllByWebQuerydsl(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                                                 @QuerydslPredicate(root = UserEntity.class) Predicate predicate) {
        return userModel.getAll(predicate);
    }

    @GetMapping(value = "/{id}")
    public UserDTO get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                       @PathVariable(value = "id") Integer id) {
        return new UserDTO(userModel.getById(id));
    }

    @PostMapping(value = "/login")
    public LoginResponse login(@RequestHeader HttpHeaders httpHeaders,
                               HttpServletRequest request, @RequestBody LoginDTO loginDTO) throws FailedLoginException {
        UserEntity userEntity = userModel.getByUsernameAndPassword(loginDTO.getUsername(), DigestUtils.md5Hex(loginDTO.getPassword()));
        if (userEntity == null) {
            throw new FailedLoginException();
        }
        UserSession userSessionOld = userSessionRepository.findByUserNameAndIpAndUserAgentAndIsValidTrue(loginDTO.getUsername(),
                request.getRemoteAddr(),
                httpHeaders.get("user-agent").get(0));
        if (userSessionOld != null) {
            userSessionOld.setToken(UUID.randomUUID().toString());
            log.info("User Controller -> Update token");
            userSessionRepository.save(userSessionOld);
            return new LoginResponse(userSessionOld.getToken());
        }
        UserSession userSessionNew = UserSession.builder()
                .userName(userEntity.getUserName())
                .token(UUID.randomUUID().toString())
                .ip(request.getRemoteAddr())
                .userAgent(httpHeaders.get("user-agent").get(0))
                .localDateBegin(DateTime.now().toLocalDate())
                .localTimeBegin(DateTime.now().toLocalTime())
                .isValid(true)
                .build();
        userSessionRepository.save(userSessionNew);
        return new LoginResponse(userSessionNew.getToken());
    }

    @PostMapping(value = "/logout")
    public LogoutResponse logout(@RequestHeader(name = "Authorization", required = false) String token) {
        if (token == null) throw new RuntimeException("Token is NULL");
        UserSession userSession = userSessionRepository.findByTokenAndIsValidTrue(token);
        if (userSession == null)  throw new RuntimeException("Token is incorrect");
        userSession.setIsValid(false);
        userSession.setLocalDateEnd(DateTime.now().toLocalDate());
        userSession.setLocalTimeEnd(DateTime.now().toLocalTime());
        userSessionRepository.save(userSession);
        return new LogoutResponse("OK");
    }

}
