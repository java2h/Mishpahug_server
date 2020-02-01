package application.controllers;

import application.dtoes.LogoutResponse;
import application.entities.security.UserSession;
import application.models.user.IUserModel;
import application.repositories.UserSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/logout")
public class LogoutController {

    @Autowired
    IUserModel userModel;

    @Autowired
    UserSessionRepository userSessionRepository;

    @PostMapping(value = "/")
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
