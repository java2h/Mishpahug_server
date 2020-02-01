package application.controllers;

import application.dtoes.data.SensorDTO;
import application.entities.data.SensorEntity;
import application.models.data.sensor.ISensorModel;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/sensor")
public class SensorController {

    @Autowired
    ISensorModel sensorModel;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<SensorDTO> findAllByWebQuerydsl(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                                                    @QuerydslPredicate(root = SensorEntity.class) Predicate predicate) {
        return sensorModel.getAll(predicate);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ResponseBody
    public void post(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                                                    @RequestBody SensorDTO data) {
        sensorModel.save(data);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/{id}", "/"})
    @ResponseBody
    public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                     @PathVariable(required = false) Integer id) {
        if (id != null)
        {
            sensorModel.delete(id);
        }
        else {
            sensorModel.delete();
        }
    }
}
