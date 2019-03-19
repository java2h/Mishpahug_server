package Application.models.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import Application.entities.UserEntity;
import Application.repo.CityRepository;
import Application.repo.UserRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserModelTest {
	
	@TestConfiguration
	static class MPHServiceConfig{
		@Bean
		public IUserModel userModel() {
			return new UserModel();
		}
	}
	
	@MockBean
	UserRepository userRepo;
	
	@MockBean
	CityRepository cityRepo;
	
	@Autowired
	private IUserModel userModel;
	
	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity ALYSSADUPLICATE = new UserEntity();
	private final String ANICKNAME = "P. Hacker";
	
	@Before
	public void buildEntities() {
		ALYSSA.setNickname(ANICKNAME);
		ALYSSADUPLICATE.setNickname(ANICKNAME);
	}
	
	
	@Test
	public void add() { //TODO: proper named methods please

		Mockito.when(userRepo.existsById(ALYSSA.getId())).thenReturn(false).thenReturn(true);	
		Mockito.when(userRepo.save(ALYSSA)).thenReturn(ALYSSA);
		Mockito.when(userRepo.getOne(ALYSSA.getId())).thenReturn(ALYSSA);
		
		assertEquals(userModel.add(ALYSSA), ALYSSA);
		assertEquals(userModel.getById(ALYSSA.getId()), ALYSSA);
		assertEquals(userModel.add(ALYSSA), ALYSSA);
	}
	
}
