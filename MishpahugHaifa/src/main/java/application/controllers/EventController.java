package application.controllers;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;

import application.dto.EventDTO;
import application.dto.UserDTO;
import application.models.event.EventEntity;
import application.models.event.IEventModel;
import application.models.relation.IRelationModel;
import application.models.user.IUserModel;
import application.models.user.UserEntity;
import application.utils.converter.IConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/event")
public class EventController implements IEventController {

	@Autowired
	IUserModel userModel;
	
	@Autowired
	IEventModel eventModel;

	@Autowired
	IRelationModel relationModel;

	@Autowired
	IConverter<EventEntity, EventDTO> converter;

	@Autowired
	IConverter<UserEntity, UserDTO> converterUser;

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public List<EventDTO> findAllByWebQuerydsl(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request, 
			@QuerydslPredicate(root = EventEntity.class) Predicate predicate) {
		return converter.DTOListFromEntities(eventModel.getAll(predicate));
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EventDTO findById(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(name = "id") Integer id) {
		EventEntity event = eventModel.getById(id);
		EventDTO eventDTO  = new EventDTO(event);
		eventDTO.setGuestIds(relationModel.getGuestIdsByEvent(event));
		return eventDTO;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/guests")
	@ResponseBody
	@Override
	/** inter-aggregate query**/
	public List<UserDTO> findGuestByEventId(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request, @PathVariable(name = "id") Integer id) {	/*inter-aggregate query*/
		List<UserEntity> userEntityList = relationModel.getSubscribedGuests(id);
		return converterUser.DTOListFromEntities(userEntityList);
	}

	@Override
	@PostMapping(value = "/")
	public EventDTO setDataFromForm(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody EventDTO data) {
		UserEntity owner = userModel.getById(data.getOwnerId());
		EventEntity eventEntity = new EventEntity(owner, data.getDate(), data.getTime());
		return new EventDTO(eventModel.add(eventEntity));
	}

	@Override
	@PutMapping(value = "/{id}")
	public EventDTO updateDataFromForm(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody HashMap<String, String> data, @PathVariable(value = "id") Integer id) {
		data.forEach((key, value) -> {
			log.warn("EventController -> updateDataFromForm -> data -> " + String.format("data '%s' = %s", key, value));
		});
		return new EventDTO(eventModel.update(id, data));
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(value = "id") Integer id) {
		eventModel.delete(id);
	}

	@Override
	@DeleteMapping(value = "/")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
		Predicate predicate = new Predicate() { 
			@Override
			public Predicate not() {
				return null;
			}

			@Nullable
			@Override
			public <R, C> R accept(Visitor<R, C> v, @Nullable C context) {
				return null;
			}

			@Override
			public Class<? extends Boolean> getType() {
				return null;
			}
		};
		eventModel.getAll(predicate).forEach(EventEntity::putIntoDeletionQueue);
		eventModel.deleteAll();
	}
}
