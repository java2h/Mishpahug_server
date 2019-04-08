package application.repositories;

import application.entities.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {
    public UserSession findByTokenaAndAndValidTrue(String token);
}