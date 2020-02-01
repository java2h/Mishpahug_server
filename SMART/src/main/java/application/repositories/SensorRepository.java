package application.repositories;

import application.entities.data.QSensorEntity;
import application.entities.data.SensorEntity;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SensorRepository  extends JpaRepository<SensorEntity, Integer>,
        QuerydslPredicateExecutor<SensorEntity>, QuerydslBinderCustomizer<QSensorEntity> {
        public SensorEntity getByNameSensor(String name);
        public SensorEntity getByMacAddress(String macAddress);
    @Override
    default public void customize(QuerydslBindings bindings, QSensorEntity root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

        bindings.bind(root.description).all((path, value) -> {
            List<? extends String> descriptionList = new ArrayList<>(value);
            return Optional.of(path.contains(descriptionList.get(0)));
        });
        bindings.bind(root.macAddress).all((path, value) -> {
            List<? extends String> macAddressList = new ArrayList<>(value);
            return Optional.of(path.contains(macAddressList.get(0)));
        });

        bindings.bind(root.nameSensor).all((path, value) -> {
            List<? extends String> nameList = new ArrayList<>(value);
            return Optional.of(path.contains(nameList.get(0)));
        });
    }
}
