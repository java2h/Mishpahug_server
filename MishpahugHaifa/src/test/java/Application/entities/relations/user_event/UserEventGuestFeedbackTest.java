package Application.entities.relations.user_event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;
import Application.entities.values.FeedBackValue;
import Application.repo.EventGuestRepository;
import Application.repo.EventRepository;
import Application.repo.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventGuestFeedbackTest {

	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final EventEntity GUESTING = new EventEntity();
	private final EventGuestRelation AGUESTING = new EventGuestRelation();
	private final FeedBackValue ABFEEDBACK = new FeedBackValue();
	
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Autowired
	EventGuestRepository eventGuestRepo;
	
	@Before
	public void buildEntities() {
		ALYSSA.setNickname("Alyssa");
		BEN.setNickname("Ben");
		ABFEEDBACK.setComment("Nice event");
		ABFEEDBACK.setDateTime(LocalDateTime.now());
		ABFEEDBACK.setRating(5);
	}

	@Test
	public void onSubsctiptionSaveReadFeedback() {
		
		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA); 
		AGUESTING.subscribe(ALYSSA, GUESTING);
		AGUESTING.setFeedback(ABFEEDBACK);
		eventGuestRepo.save(AGUESTING);
	
		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
		EventGuestRelation savedSubcsrEvent = savedE.getSubscriptions().iterator().next();
		FeedBackValue savedFB = savedSubcsrEvent.getFeedback();
		assertEquals(savedFB, ABFEEDBACK);

	}
	
//
//	@Test
//	public void addFeedbackWithoutSubscription() {
//		
//		
//
//	}
	


}