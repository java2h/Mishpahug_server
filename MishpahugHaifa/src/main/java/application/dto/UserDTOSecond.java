package application.dto;


import application.entities.Gender;
import application.entities.KichenTypeEntity;
import application.entities.MarriageStatus;
import application.entities.ReligionEntity;
import lombok.*;

import java.time.LocalDate;


@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// This DTO should be use for the controller to add all of needed forms
public class UserDTOSecond {

	private String firstName;
	private String lastName;

	private String phoneNumber;
	private LocalDate birthday;

	private ReligionEntity religion;
	private KichenTypeEntity kichenType;
	private Gender gender;
	private MarriageStatus marriageStatus;

}
