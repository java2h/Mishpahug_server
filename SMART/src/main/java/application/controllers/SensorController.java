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
}
