package application.relations.user_event;

import application.models.event.EventEntity;
import application.models.event.EventRepository;
import application.models.relation.SubscriptionEntity;
import application.models.relation.SubscriptionRepository;
import application.models.user.UserEntity;
import application.models.user.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventGuestTest {

	private final UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final UserEntity BEN = new UserEntity("Ben", "bitdiddle@sicp.edu");
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private EventEntity GUESTING;
	private EventEntity TESTING;
	private SubscriptionEntity AGUESTING;
	private SubscriptionEntity BTESTING;

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Autowired
	SubscriptionRepository subscriptionRepo;

	@Before
	public void buildEntities() {
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		
		GUESTING = new EventEntity(BEN.getId(), TDATE, TTIME);
		eventRepo.save(GUESTING); 
		AGUESTING = new SubscriptionEntity(GUESTING.getId(), ALYSSA.getId()); 
		subscriptionRepo.save(AGUESTING);
		
		TESTING= new EventEntity(ALYSSA.getId(), TDATE, TTIME);
		eventRepo.save(TESTING); 
		BTESTING = new SubscriptionEntity(TESTING.getId(), BEN.getId()); 
		subscriptionRepo.save(BTESTING);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void onUnexistentSubscriptionUnsubscriptionThrow() {

		AGUESTING.nullifyForRemoval();

	}
	
	@Test
	public void onSubscriptionSaveReadUserAndEvent() {
		
		assertTrue(subscriptionRepo.existsById(AGUESTING.getId()));

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();

		Integer savedAIdfromRelation = AGUESTING.getId().getGuestId();
		Integer savedEIdfromRelation = AGUESTING.getId().getEventId();
		assertTrue(savedA.equals(ALYSSA));
		assertTrue(savedAIdfromRelation.equals(ALYSSA.getId()));
		assertTrue(savedA.getId().equals(savedAIdfromRelation));
		assertTrue(savedE.equals(GUESTING));
		assertTrue(savedEIdfromRelation.equals(GUESTING.getId()));
		assertTrue(savedE.getId().equals(savedEIdfromRelation));

	}
	
	@Test
	public void findEventBySubs() {

		List<Integer> eventIds = subscriptionRepo.getEventIdsForGuestId(ALYSSA.getId());
		assertEquals(eventIds.size(), 1);
		assertTrue(eventIds.contains(GUESTING.getId()));

	}

	@Test
	public void findUserBySubs() {

		List<Integer> guestIds = subscriptionRepo.getEventIdsForGuestId(GUESTING.getId());
		assertEquals(guestIds.size(), 1);
		assertTrue(guestIds.contains(ALYSSA.getId()));
	}
	
	
	/**
	 * Deleting user and testing that its subscriptions are unsubscribed automatically;
	 */
	@Test
	public void onGuestDeleteEventRemains() {

		ALYSSA.putIntoDeletionQueue();
		AGUESTING.putIntoDeletionQueue();
		TESTING.putIntoDeletionQueue();
		BTESTING.putIntoDeletionQueue();
		subscriptionRepo.delete(AGUESTING); 
		subscriptionRepo.delete(BTESTING); 
		eventRepo.delete(TESTING);
		userRepo.delete(ALYSSA);

		assertTrue(eventRepo.existsById(GUESTING.getId()));
		assertFalse(userRepo.existsById(ALYSSA.getId()));
		assertFalse(subscriptionRepo.existsById(AGUESTING.getId()));
		
	}
	
	/**
	 * Deleting event and testing that its subscriptions are unsubscribed automatically;
	 * 
	 */
	@Test
	public void onEventDeleteGuestRemains() {
	
		GUESTING.putIntoDeletionQueue();
		AGUESTING.putIntoDeletionQueue();
		subscriptionRepo.delete(AGUESTING); 
		eventRepo.delete(GUESTING);
		
		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertTrue(userRepo.existsById(BEN.getId()));

		assertEquals(eventRepo.count(), 1);
		assertEquals(subscriptionRepo.count(), 1);
	}
}