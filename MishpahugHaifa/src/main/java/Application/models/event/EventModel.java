package Application.models.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.EventGuestRelation.EventGuestId;
import Application.entities.UserEntity;
import Application.exceptions.ExceptionMishpaha;
import Application.repo.EventGuestRepository;
import Application.repo.EventRepository;
import Application.repo.UserRepository;

@Service
@Transactional
public class EventModel implements IEventModel {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventGuestRepository subscriptionsRepository;

	@Override
	public List<EventEntity> getAll() {
		return eventRepository.findAll();
	}

	@Override
	/**
	 * Guest
	 */
	public Set<EventEntity> getAllByUser(Integer userId) {
		UserEntity userEntity = userRepository.getOne(userId);
		Set<EventGuestRelation> subscriptions = userEntity.getSubscriptions();
		return subscriptions.stream().map(s -> s.getEvent()).collect(Collectors.toSet());
	}

	@Override
	public List<EventEntity> getByFilter(HashMap<String, String> filter) {
		return eventRepository.searchByFilter(filter);
	}

	@Override
	public EventEntity add(EventEntity data) {
		return eventRepository.save(data);
	}

	@Override
	public EventEntity update(Integer eventId, HashMap<String, String> data) {
		return null;
	}

	@Override
	public Set<UserEntity> getAllSubscribed(Integer eventId) {
		EventEntity eventEntity = eventRepository.getOne(eventId);
		Set<EventGuestRelation> subscriptions = eventEntity.getSubscriptions();
		return subscriptions.stream().map(s -> s.getUserGuest()).collect(Collectors.toSet());
	}

	@Override
	public EventEntity remove(Integer eventId) throws ExceptionMishpaha {
		try {
			EventEntity eventEntity = eventRepository.getOne(eventId);
			UserEntity userOwner = eventEntity.getUserEntityOwner();
			userOwner.removeOwnedEvent(eventEntity);
			userRepository.save(userOwner); 
			//TODO: check cascade; 
			return eventEntity;
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + eventId, null);
		}
	}

	@Override
	public EventEntity getById(Integer id) throws ExceptionMishpaha {
		try {
			return eventRepository.getOne(id);
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + id, null);
		}
	}

	@Override
	public EventEntity getByFullName(String name) {
		return eventRepository.byFullName(name);
	}

	@Override
	public EventEntity subscribe(Integer eventId, Integer userId) throws ExceptionMishpaha {
		EventEntity eventEntity;
		UserEntity userEntity;
		try {
			eventEntity = eventRepository.getOne(eventId);
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + eventId, null);
		}
		try {
			userEntity = userRepository.getOne(userId);
		} catch (Exception e) {
			throw new ExceptionMishpaha("Error! Not found user with id " + userId, null);
		}
		EventGuestRelation subscription = new EventGuestRelation();
		subscription.subscribe(userEntity, eventEntity);
		return eventEntity;
	}

	@Override
	public EventEntity unsubscribe(Integer eventId, Integer userId) throws ExceptionMishpaha {
		EventEntity eventEntity;
		UserEntity userEntity;
		EventGuestRelation subscription;
		try {
			eventEntity = eventRepository.getOne(eventId);
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + eventId, null);
		}
		try {
			userEntity = userRepository.getOne(userId);
		} catch (Exception e) {
			throw new ExceptionMishpaha("Error! Not found user with id " + userId, null);
		}
		EventGuestId subscriptionKey = new EventGuestId(userEntity.getId(), eventEntity.getId());
		if(!subscriptionsRepository.existsById(subscriptionKey)) {
			throw new ExceptionMishpaha("User with id " + userId + " is not subscribed to event with id " + eventId , null);
		}			
		subscription = subscriptionsRepository.findById(subscriptionKey).get();
		if (!userEntity.getSubscriptions().contains(subscription) || !eventEntity.getSubscriptions().contains(subscription)){
			throw new IllegalStateException("User is guest of event, but his set of subscriptions does not contain this event");
		}
		subscription.unsubscribe(userEntity, eventEntity); //cascaded, no need to explicitly delete; 
		return eventEntity;
	}
}
