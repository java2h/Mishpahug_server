package application.models.event;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;

import java.util.List;
import java.util.HashMap;
import java.util.Set;

public interface IEventModel {
    public List<EventEntity> getAll();
    public Set<EventEntity> getAllByUser(Integer userId);
    public List<EventEntity> getByFilter(HashMap<String, String> filter);
    public EventEntity add(EventEntity data); // Should not allow duplicated events; 
    public EventEntity update(Integer eventId, HashMap<String, String> data);
    public Set<UserEntity> getAllSubscribed(Integer eventId);
    public EventEntity remove(Integer eventId) throws ExceptionMishpaha;
    public EventEntity getById(Integer id) throws ExceptionMishpaha;
    public EventEntity getByFullName(String name);
    public EventEntity subscribe(Integer eventId, Integer userId) throws ExceptionMishpaha;
    public EventEntity unsubscribe(Integer eventId, Integer userId) throws ExceptionMishpaha;
}
