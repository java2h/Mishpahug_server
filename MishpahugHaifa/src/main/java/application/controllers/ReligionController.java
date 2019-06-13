package application.controllers;

import application.controllers.interfaces.IReligionController;
import application.entities.properties.ReligionEntity;
import application.models.properties.religion.IReligionModel;
import application.utils.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/religion")
public class ReligionController implements IReligionController {

    @Autowired
    IReligionModel religionModel;
    
    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request){
        return IConverter.PropertyToStringList(religionModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public ReligionEntity get(@RequestHeader HttpHeaders httpHeaders,
                              HttpServletRequest request, @PathVariable(name = "id") Integer id){
        return religionModel.getById(id);
    }

    @Override
    @PostMapping(value = "/")
    public void post(@RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request, @RequestBody ReligionEntity data){
        religionModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request, @RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name){
        religionModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        religionModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request, @PathVariable(name = "name") String name){
        religionModel.deleteByName(name);
    }

}
