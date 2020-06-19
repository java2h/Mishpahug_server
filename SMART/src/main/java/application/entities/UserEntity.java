package application.entities;

import application.dtoes.UserDTO;
import application.utils.ImageUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}), @UniqueConstraint(columnNames = {"username"})})
@ToString()
@EqualsAndHashCode(of = {"userName"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
    @Id
    @Column(name = "User_Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @NotNull
    @lombok.NonNull
    @Column(name = "email", nullable = false)
    private String eMail;

    @NotNull
    @lombok.NonNull
    @Column(name = "username", length = 36, nullable = false)
    private String userName;

    @Column(name = "Encryted_Password", length = 128)
    /*
     * @JsonInclude(Include.NON_NULL) on class or @JsonInclude(Include.NON_NULL)
     * here to prevent this from being serialized as null
     */
    private String encrytedPassword;

    @Column(name = "dateofbirth")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dateOfBirth;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    @Setter(AccessLevel.NONE)
    private Blob picture;

    public UserEntity(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.dateOfBirth = userDTO.getDateOfBirth();
        this.eMail = userDTO.getEMail();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.userName = userDTO.getUserName();
    }

    public void loadAvatar(String filename){
        try {
            picture = BlobProxy.generateProxy(ImageUtils.getImage(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum UserStatus implements StatusChanger {
        ACTIVE(u -> u.activate()), DEACTIVATED(u -> u.deactivate()), PENDINGFORDELETION(u -> u.putIntoDeletionQueue());

        private StatusChanger changer;

        private UserStatus(StatusChanger changer) {
            this.changer = changer;
        }

        @Override
        public void change(UserEntity user) {
            changer.change(user);
        }
    }

    @FunctionalInterface
    private interface StatusChanger {
        void change(UserEntity user);
    }

/*    *//**
     * UserEntity: required fields, userName is immutable;
     *//*
    public UserEntity(@NotNull String userName, @NotNull String email) {
*//*        if (userName.length() > 36) {
            throw new IllegalArgumentException("userName too long");
        }*//*
        this.userName = userName;
        this.eMail = email;
    }*/

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
