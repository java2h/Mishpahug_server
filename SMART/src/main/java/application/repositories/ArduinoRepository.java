package application.repositories;

import application.entities.data.Arduino;
import application.entities.data.QArduino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

public interface ArduinoRepository  extends JpaRepository<Arduino, Integer>,
        QuerydslPredicateExecutor<Arduino>, QuerydslBinderCustomizer<QArduino> {
}
