package application.controllers;

import application.controllers.interfaces.ICityController;
import application.entities.CityEntity;
import application.models.city.ICityModel;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController implements ICityController {
    @Autowired
    ICityModel cityModel;

    @Override
    @PostMapping(value = "/")
    public void post(
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request, @RequestBody CityEntity data){
        cityModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request, @RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name){
        cityModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        cityModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request, @PathVariable(name = "name") String name){
        cityModel.deleteByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request, @QuerydslPredicate(root = CityEntity.class) Predicate predicate){
        Iterable<CityEntity> cityEntityList = cityModel.getAll(predicate);
        List<String> res = new ArrayList<>();
        cityEntityList.forEach(item -> res.add(item.getName()));
        return res;
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(
                      @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request, @PathVariable(name = "id") Integer id){
        return cityModel.getById(id).getName();
    }
}
