package application.controllers;

import application.dtoes.data.DeviceDTO;
import application.entities.data.DeviceEntity;
import application.models.data.device.IDeviceModel;
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
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    IDeviceModel deviceModel;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<DeviceDTO> findAllByWebQuerydsl(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                                                    @QuerydslPredicate(root = DeviceEntity.class) Predicate predicate) {
        return deviceModel.getAll(predicate);
    }
}
