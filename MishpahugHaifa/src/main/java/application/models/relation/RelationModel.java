package application.models.relation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import application.models.event.EventChanged;
import application.models.event.EventDeleted;
import application.models.event.EventEntity;
import application.models.event.EventEntity.EventStatus;
import application.models.relation.SubscriptionEntity.EventGuestId;
import application.models.user.UserChanged;
import application.models.user.UserDeleted;
import application.models.user.UserEntity;
import application.models.user.UserEntity.UserStatus;
import application.models.user.values.FeedBackValue;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RelationModel implements IRelationModel {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;
    
    
    @EventListener
    public void updateSubscriptionsOnEventDelete(UserDeleted userDeleted) {
    		log.warn("RelationModel -> userDeleted event deleted detected");
    		List<SubscriptionEntity> visits = subscriptionRepository.findByGuest_Id(userDeleted.getUserId()); 
    		log.warn("Subscriptions To Delete by User : " + visits );
    		visits.forEach(SubscriptionEntity::putIntoDeletionQueue);
    		subscriptionRepository.deleteAll(visits);
    		log.warn("Check subModelUserDelete " + subscriptionRepository.findAll());
    }
    
    @EventListener
    public void updateSubscriptionsOnEventDelete(EventDeleted eventDeleted) {
    		log.warn("RelationModel -> eventDeleted  event deleted detected");
			List<SubscriptionEntity> subs = subscriptionRepository.findByEvent_Id(eventDeleted.getEventId());
			log.warn("Subscriptions To Delete by Event : " + subs );
			subs.forEach(SubscriptionEntity::putIntoDeletionQueue);
			subscriptionRepository.deleteAll(subs);
			log.warn("Check subModelEventDelete " + subscriptionRepository.findAll());
    }
  
    
    /**
	 * Handles subscription logic;
	 */
	// TODO: integer arguments design issue; test;
	private class SubscriptionHandler {
		final private Integer eventId;
		final private Integer userId;
		private EventEntity eventEntity;
		private UserEntity userEntity;
		private SubscriptionEntity subscription;

		private SubscriptionHandler(Integer eventId, Integer userId){
			this.eventId = eventId;
			this.userId = userId;
			load();
		}

		private void load(){
			eventEntity = eventRepository.getOne(eventId);
			userEntity = userRepository.getOne(userId);
			EventGuestId subscriptionKey = new EventGuestId(userEntity.getId(), eventEntity.getId());
			if (!subscriptionRepository.existsById(subscriptionKey)) {
				subscription = new SubscriptionEntity();
			} else {
				subscription = subscriptionRepository.getOne(subscriptionKey);
			}
		}

		EventEntity subscribe() {
			return eventEntity;
		}

		EventEntity cancel() {
			subscription.cancel();
			return eventEntity;
		}

		EventEntity deactivate() {
			subscription.deactivate();
			return eventEntity;
		}

		EventEntity unsubscribe() {
			subscription.putIntoDeletionQueue();
			subscription.nullifyForRemoval(); // cascaded, no need to explicitly delete;
			return eventEntity;
		}
	}
	
	@Override
	public EventEntity subscribe(Integer eventId, Integer userId){
		SubscriptionHandler handler = new SubscriptionHandler(eventId, userId);
		return handler.subscribe();
	}

	@Override
	public EventEntity deactivateSubscription(Integer eventId, Integer userId){
		SubscriptionHandler handler = new SubscriptionHandler(eventId, userId);
		return handler.deactivate();
	}
    
    @Override
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId) {
        Map<Integer, FeedBackValue> res = new HashMap<>();
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findByEvent_Id(eventId);
        log.info("" + subscriptionEntityList);
        Integer z = 0;
        for (SubscriptionEntity x:subscriptionEntityList){
            FeedBackValue value = x.getFeedback();
            res.put(z,value);
            z++;
        }
        log.info("" + subscriptionEntityList);
        return res; 
    }

    @Override
    public Map<Integer, FeedBackValue> getAllByUser(Integer userId) {
        Map<Integer, FeedBackValue> res = new HashMap<>();
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findByGuest_Id(userId);
        log.info("" + subscriptionEntityList);
        Integer z = 0;
        for (SubscriptionEntity x:subscriptionEntityList){
            FeedBackValue value = x.getFeedback();
            res.put(z,value);
            z++;
        }
        return res;
    }

    @Override
    public void removeAllByUser(Integer userId) {
        subscriptionRepository.removeById_UserGuestId(userId);
    }

    @Override
    public void removeAllByEvent(Integer eventId) {
        subscriptionRepository.removeById_EventId(eventId);
    }

    @Override
    public FeedBackValue removeById(Integer feedBackId) {
        return null;
    }
}