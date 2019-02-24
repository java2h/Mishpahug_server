package Application.controllers;

import Application.entities.UserEntity;
import Application.models.user.IUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/administrator/user")
public class AdminCPUser {
    @Autowired
    IUserModel userModel;
    @GetMapping(value="/getall")
    public List<UserEntity> getAll(){
        return userModel.getAll();
    }
    @PostMapping(value = "/add")
    public UserEntity add(@RequestBody UserEntity data){
        return userModel.add(data);
    }
    @PostMapping(value = "/update")
    public UserEntity update(@RequestBody HashMap<String, String> data,@RequestParam(value = "id") Integer id){
        return userModel.updateUser(id, data);
    }
    @DeleteMapping(value = "/remove")
    public UserEntity remove(@RequestParam(value = "id") Integer id){
        return userModel.remomeUser(id);
    }
    @GetMapping(value="/get")
    public UserEntity get(@RequestParam(value = "id") Integer id){
        return userModel.getById(id);
    }
    @GetMapping(value="/getbyfilter")
    public List<UserEntity> getByFilter(@RequestBody HashMap<String, String> filter){
        return userModel.getByFilter(filter);
    }
}
