package application.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import application.dto.UserDTO;
import application.entities.UserEntity;

@Service
public class UserConverter extends ConverterBase implements IConverter<UserEntity, UserDTO> {

	public List<UserDTO> DTOListFromEntities(Iterable<UserEntity> data) {
		List<UserDTO> res = new ArrayList<>();
		for (UserEntity x : data) {
			res.add(new UserDTO(x));
		}
		return res;
	}

	public UserEntity entityFromDTO(UserDTO data) {
		UserEntity res = new UserEntity(data.getUserName(), data.getEMail());
		res.setFirstName(data.getFirstName());
		res.setLastName(data.getLastName());
		res.setEncrytedPassword(data.getEncryptedPassword());
		res.setPhoneNumber(data.getPhoneNumber());
		res.setDateOfBirth(data.getDayOfBirth());
		res.setGender(genderRepository.getByName(data.getGender()));
		res.setKitchenType(kichenTypeRepository.getByName(data.getKichenType()));
		res.setReligion(religionRepository.getByName(data.getReligion()));
		res.setMaritalStatus(maritalStatusRepository.getByName(data.getMaritalStatus()));
		return res;
	}
}
