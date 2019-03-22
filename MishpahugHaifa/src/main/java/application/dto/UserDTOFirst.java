package application.dto;

import lombok.*;


@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
// This DTO should be use in constructor create UserEntity in DB
public class UserDTOFirst {

    private String email;
    private String encrytedPassword;
}
