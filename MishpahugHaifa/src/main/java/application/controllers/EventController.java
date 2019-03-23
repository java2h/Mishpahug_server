package application.controllers;

import application.dto.EventDTO;
import application.dto.EventDTODetail;
import application.dto.EventDTOLists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/event")
public class EventController {
    @GetMapping(value="/getall")
    public EventDTOLists getDataForAddForm(){
        return null;

    }
    @PostMapping(value="/addPage2")
    public EventDTODetail setDataFromFormDetail(){
        return null;

    }

    @PostMapping(value="/addPage1")
    public EventDTO setDataFromForm(){
        return null;

    }
}