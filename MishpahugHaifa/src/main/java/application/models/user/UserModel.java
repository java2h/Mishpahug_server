package application.models.user;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import application.entities.GenderEntity;
import application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;

@Service
@Transactional
public class UserModel implements IUserModel {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	KichenTypeRepository kichenTypeRepository;

	@Autowired
	GenderRepository genderRepository;

	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getById(Integer userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public List<UserEntity> getByFilter(HashMap<String, String> filter) {
		return userRepository.searchByFilter(filter);
	}

	@Override
	public List<UserEntity> getByGender(String gender){
		GenderEntity genderEntity = genderRepository.findByName(gender);
		return userRepository.findByGenderEntity(genderEntity);
	}

	@Override
	public UserEntity add(UserEntity data) {
		return userRepository.save(data);
	}

	/*
	 * keys for update nickname firstname lastname email address.build address.city
	 * address.apartment address.street telephone
	 */
	/*
	 * нужно протестировать не уверен в работе кода между !!!. проверен вариант
	 * зактнуть логику в сущноть... не подойдет, так как нужен поиск города по имени
	 */
	@Override
	public UserEntity update(Integer userId, HashMap<String, String> data) throws ExceptionMishpaha {
		try {
			UserEntity user = userRepository.getOne(userId);
			return userRepository.update(user, data);
		} catch (Exception e) {
			throw new ExceptionMishpaha("Error! Not found user with id " + userId, null);
		}
	}

	@Override
	public UserEntity remove(Integer userId) {
		UserEntity usr = userRepository.getOne(userId);
		userRepository.deleteById(userId);
		return usr;
	}

	@Override
	public UserEntity getByName(String name) {
		return userRepository.findByUserName(name);
	}

}