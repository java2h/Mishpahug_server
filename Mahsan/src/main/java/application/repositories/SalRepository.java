package application.repositories;

import application.entities.data.Sal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalRepository extends JpaRepository<Sal, Integer> {
}
