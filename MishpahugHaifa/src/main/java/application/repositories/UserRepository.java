package application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.GenderEntity;
import application.entities.KitchenTypeEntity;
import application.entities.MaritalStatusEntity;
import application.entities.ReligionEntity;
import application.entities.UserEntity;
import application.repositories.custom.UserRepositoryCustom;


public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom {
   
	public List<UserEntity> findByReligion(ReligionEntity religionEntity);
	
	public List<UserEntity> findByKitchenType(KitchenTypeEntity kitchenEntity);
	 
	public List<UserEntity> findByGender(GenderEntity genderEntity);
	 
	public List<UserEntity> findByMaritalStatus(MaritalStatusEntity maritalStatusEntity);
	
    public UserEntity findByUserName(String userName);
}
