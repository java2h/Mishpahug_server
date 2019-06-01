package application.controllers;

import application.controllers.interfaces.ICityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.city.ICityModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/city")
public class CityController implements ICityController {
    @Autowired
    ICityModel cityModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody CityEntity data,
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request) throws ExceptionMishpaha {
        httpHeaders.forEach((key, value) -> {
            log.info("CityController -> post -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CityController -> post -> Remote IP -> " + request.getRemoteAddr());
        cityModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name,
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request) throws ExceptionMishpaha {
        httpHeaders.forEach((key, value) -> {
            log.info("CityController -> put -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CityController -> put -> Remote IP -> " + request.getRemoteAddr());
        cityModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("CityController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CityController -> delete -> Remote IP -> " + request.getRemoteAddr());
        cityModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) throws ExceptionMishpaha {
        httpHeaders.forEach((key, value) -> {
            log.info("CityController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CityController -> delete -> Remote IP -> " + request.getRemoteAddr());
        cityModel.deleteByID(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<CityEntity> findAllByWebQuerydsl(@QuerydslPredicate(root = CityEntity.class) Predicate predicate,
                                                     @RequestHeader HttpHeaders httpHeaders,
                                                     HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("CityController -> findAllByWebQuerydsl -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CityController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return cityModel.getAll(predicate);
    }

    @Override
    @GetMapping(value = "/{id}")
    public CityEntity get(@PathVariable(name = "id") Integer id,
                          @RequestHeader HttpHeaders httpHeaders,
                          HttpServletRequest request) throws ExceptionMishpaha {
        httpHeaders.forEach((key, value) -> {
            log.info("CityController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CityController -> get -> Remote IP -> " + request.getRemoteAddr());
        return cityModel.getById(id);
    }

}
