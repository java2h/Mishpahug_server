package application.repositories;

import application.entities.data.Option;
import application.entities.data.QOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

public interface OptionRepository  extends JpaRepository<Option, Integer>,
        QuerydslPredicateExecutor<Option>, QuerydslBinderCustomizer<QOption> {
}
