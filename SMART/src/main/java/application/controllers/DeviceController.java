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

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void post(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                                                    @RequestBody DeviceDTO data) {
        deviceModel.save(data);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/{id}", "/"})
    @ResponseBody
    public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                       @PathVariable(required = false) Integer id) {
        if (id != null)
        {
            deviceModel.delete(id);
        }
        else {
            deviceModel.delete();
        }
    }
}
