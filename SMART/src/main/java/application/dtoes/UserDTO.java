package application.dtoes;

import application.entities.UserEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PreRemove;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ToString
@EqualsAndHashCode(of = {"userName"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class UserDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String eMail;
    private String userName;
    private String encrytedPassword;
    private LocalDate dateOfBirth;
    private UserStatus status = UserStatus.ACTIVE;
    public enum UserStatus implements StatusChanger {
        ACTIVE(u -> u.activate()), DEACTIVATED(u -> u.deactivate()), PENDINGFORDELETION(u -> u.putIntoDeletionQueue());

        private StatusChanger changer;

        private UserStatus(StatusChanger changer) {
            this.changer = changer;
        }

        @Override
        public void change(UserDTO user) {
            changer.change(user);
        }
    }

    public UserDTO(UserEntity userEntity){
        this.firstName = userEntity.getFirstName();
        this.eMail = userEntity.getEMail();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.lastName = userEntity.getLastName();
        this.dateOfBirth = userEntity.getDateOfBirth();
    }

    @FunctionalInterface
    private interface StatusChanger {
        void change(UserDTO user);
    }

    /**
     * UserEntity: required fields, userName is immutable;
     */
    public UserDTO(@NotNull String userName, @NotNull String email) {
        if (userName.length() > 36) {
            throw new IllegalArgumentException("userName too long");
        }
        this.userName = userName;
        this.eMail = email;
    }

    public String fieldByName(String fieldName) {
        String res = "n/a";
        switch (fieldName) {
            case "userName":
                res = this.userName;
                break;
            //TODO
        }
        return res;
    }

    /**
     * Changes this user's status, validating the parameter
     *
     * @param status must be equal to one of UserStatus values;
     */
    public void changeStatus(String status) {
        UserStatus newStatus;
        try {
            newStatus = UserStatus.valueOf(status);
        } catch (Exception e) {
            throw new IllegalArgumentException("Not found UserStatus with nameOption " + status);
        }
        newStatus.change(this);
    }

    /**
     * Checks that the user is OK to delete and then unsubscribes him/her from
     * everywhere, and his subscribers too;
     */
    @PreRemove
    private void nullifyForRemoval() {
        if (!isPendingForDeletion()) {
            throw new IllegalArgumentException("User must be first putIntoDeletionQueue");
        }
    }


    /**
     * @return true if this user is activated
     */
    public boolean isEnabled() {
        return status.equals(UserStatus.ACTIVE);
    }

    /**
     * @return true if this user is pending for deletion
     */
    public boolean isPendingForDeletion() {
        return status.equals(UserStatus.PENDINGFORDELETION);
    }

    /**
     * Activate this user
     */
    public void activate() {
        status = UserStatus.ACTIVE;
    }

    /**
     * Deactivate this user
     */
    public void deactivate() {
        status = UserStatus.DEACTIVATED;
    }

    /**
     * Puts this user in the deletion queue
     */
    public void putIntoDeletionQueue() {
        status = UserStatus.PENDINGFORDELETION;
    }
}

