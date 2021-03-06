package application.utils.converter;

import application.entities.EventEntity;
import application.entities.UserEntity;

import java.util.HashMap;

public interface IUpdates {

	public void updateEvent(EventEntity eventEntity, HashMap<String, String> data); 
	
	public void updateUser(UserEntity userEntity, HashMap<String, String> data);
}
