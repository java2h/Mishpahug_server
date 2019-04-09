
package application.relations.user_event;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.repositories.SubscriptionRepository;
import application.repositories.EventRepository;
import application.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * Relation: 
 * OneToMany; User is the primary entity, that has a Set of Events.  
 * Event has only one User as its owner. Updates are casceded from User. 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventOwnerTest {

	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final EventEntity ABTEST = new EventEntity(TDATE, TTIME);
	private final EventEntity BATEST = new EventEntity(TDATE, TTIME);
	private final SubscriptionEntity ABSUB= new SubscriptionEntity();
	private final SubscriptionEntity BASUB = new SubscriptionEntity();


	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;	
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;
	
	@Autowired
	SubscriptionRepository eventGuestRepo;


	@Before
	public void buildEntities() {
		ALYSSA.setEMail("p_hacker@sicp.edu");
		BEN.setEMail("bitdiddle@sicp.edu");
	}

	
	@Test
	public void addEventOfAnotherOwner() { 
		
		BATEST.setUserEntityOwner(ALYSSA);
		BATEST.setUserEntityOwner(BEN);
		
		assertEquals(ALYSSA.getEventEntityOwner().size(), 0);
		assertEquals(BEN.getEventEntityOwner().size(), 1);
		assertFalse(ALYSSA.getEventEntityOwner().contains(BATEST));
		assertTrue(BEN.getEventEntityOwner().contains(BATEST));
		
	}
	
	@Test
	/**
	 * Can't add event to user more than 1 time; checks hashcode of Event; 
	 */
	public void saveDuplicateEvent() {

		BATEST.setUserEntityOwner(ALYSSA);
		BATEST.setUserEntityOwner(ALYSSA);
		BATEST.setUserEntityOwner(ALYSSA);
		BATEST.setUserEntityOwner(ALYSSA);

		userRepo.save(ALYSSA);
		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		assertTrue(savedA.getEventEntityOwner().size() == 1);

	}
	
	/**
	 * Checking that Event.setUserEntityOwner creates bidirectional link; 
	 * Checking cascade save; 
	 * Checking equals in User and Event; 
	 */
	@Test
	public void onUserSaveReadEvent() {

		BATEST.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);
		
		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertTrue(eventRepo.existsById(BATEST.getId()));
		
		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(BATEST.getId()).get();
		EventEntity savedEfromUser = savedA.getEventEntityOwner().iterator().next();
		UserEntity savedAfromEvent = savedE.getUserEntityOwner();
		
		assertTrue(savedA.equals(ALYSSA));
		assertTrue(savedAfromEvent.equals(ALYSSA));
		assertTrue(savedA.equals(savedAfromEvent));

		assertTrue(savedE.equals(BATEST));
		assertTrue(savedEfromUser.equals(BATEST));
		assertTrue(savedE.equals(savedEfromUser));

	}

	/**
	 * Checking that toString works in the bidirectional relation;
	 */
	@Test
	public void toStringBiDir() {

		BATEST.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(BATEST.getId()).get();
		
		System.out.println(savedA); //TODO: toString w/o println pls
		System.out.println(savedE);

	}
	
//	/**
//	 * This tests User.transferEvent;
//	 * Transfer Event not in the API, but useful as a tool for testing how relation works;
//	 */
//	@Test
//	public void onTransferEvent() { //TODO: fix me please, remove method wants to delete the event but it's not pending for deletion
//
//		BATEST.setUserEntityOwner(ALYSSA);
//		userRepo.save(ALYSSA);
//
//		ALYSSA.transferOwnedEvent(BATEST, BEN);
//		userRepo.save(ALYSSA);
//		userRepo.save(BEN);
//
//		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
//		UserEntity savedB = userRepo.findById(BEN.getId()).get();
//
//		assertTrue(savedA.getEventEntityOwner().size() == 0);
//		assertTrue(savedB.getEventEntityOwner().size() == 1);
//		assertFalse(savedA.getEventEntityOwner().contains(BATEST));
//		assertTrue(savedB.getEventEntityOwner().contains(BATEST));
//		assertTrue(eventRepo.existsById(BATEST.getId()));
//		assertEquals(eventRepo.findById(BATEST.getId()).get().getUserEntityOwner(), BEN);
//
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void removeEventOfAnotherOwner() {
//		
//		BATEST.setUserEntityOwner(ALYSSA);
//		BEN.transferOwnedEvent(BATEST, ALYSSA);
//		
//	}

	/**
	 * Deleting user and testing its unsubscribeAll() to ensure that he's unsubcribed from everywhere and 
	 * all his/her owned events clear themselves of tuests;
	 */
	@Test
	public void onUserDeleteEventNotRemains() {

		BATEST.setUserEntityOwner(ALYSSA);
		ABTEST.setUserEntityOwner(BEN);
		userRepo.save(ALYSSA);
		userRepo.save(BEN);
		ABSUB.subscribe(ALYSSA, ABTEST);
		BASUB.subscribe(BEN, BATEST);
		
		assertTrue(eventGuestRepo.existsById(ABSUB.getId()));
		assertTrue(eventGuestRepo.existsById(BASUB.getId()));
		assertTrue(ALYSSA.getSubscriptions().contains(ABSUB));
		assertTrue(BEN.getSubscriptions().contains(BASUB));

		ALYSSA.putIntoDeletionQueue();
		userRepo.delete(ALYSSA);
		
		assertEquals(userRepo.count(), 1);
		assertEquals(eventRepo.count(), 1);
		
		assertFalse(userRepo.existsById(ALYSSA.getId()));
		assertFalse(eventRepo.existsById(BATEST.getId()));
		assertFalse(eventGuestRepo.existsById(BASUB.getId()));
		assertFalse(BEN.getSubscriptions().contains(BASUB));
	
		assertTrue(userRepo.existsById(BEN.getId()));
		assertTrue(eventRepo.existsById(ABTEST.getId()));
		assertFalse(eventGuestRepo.existsById(ABSUB.getId()));
		//No Alyssa in the system...
		

	}
	



}