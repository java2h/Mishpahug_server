package application.controllers;

import application.entities.data.ValueEntity;
import application.models.data.sensor.ISensorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/data")
public class DataFromDeviceController {
    @Autowired
    ISensorModel sensorModel;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void post(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                     @RequestBody String data){
        //TODO обработка данных с arduinp
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{mac}")
    public List<ValueEntity> get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request, @PathVariable String mac){
        //TODO вывод значений для датчика по его идентификатору
        return sensorModel.getByMAC(mac).getValues();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{mac}/{datebegin}/{dateend}")
    //TODO проверка коррктности дат до их использования
    public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
                       @PathVariable(required = false) String mac,
                       @PathVariable(required = false) String datebegin,
                       @PathVariable(required = false) String dateend){

        if (mac != null){
            if ((datebegin != null) && (dateend != null)){
                sensorModel.deleteData(mac, LocalDate.parse(datebegin), LocalDate.parse(dateend));
            }
            else {
                sensorModel.deleteData(mac);
            }
        }
        else if ((datebegin != null) && (dateend != null)){
            sensorModel.deleteData(LocalDate.parse(datebegin), LocalDate.parse(dateend));
            }
            else {
            sensorModel.deleteData();
            }
    }

}
