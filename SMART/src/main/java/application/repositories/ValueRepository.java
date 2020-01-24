package application.repositories;

import application.entities.data.QValue;
import application.entities.data.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

public interface ValueRepository  extends JpaRepository<Value, Integer>,
        QuerydslPredicateExecutor<Value>, QuerydslBinderCustomizer<QValue> {
}
