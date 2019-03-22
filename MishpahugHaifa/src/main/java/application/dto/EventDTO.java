package application.dto;

import application.entities.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class EventDTO {

	private String nameOfEvent;
	private UserEntity userOwnerEntity;

	private HoliDayEntity holiDay;
	private ReligionEntity religion;
	private KichenTypeEntity kichenType;

	private AddressEntity addressEntity;
	private String description;

	private LocalDate date;
	private LocalTime time;






}
