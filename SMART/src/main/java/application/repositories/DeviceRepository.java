package application.repositories;

import application.entities.data.DeviceEntity;
import application.entities.data.QDeviceEntity;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface DeviceRepository  extends JpaRepository<DeviceEntity, Integer>,
        QuerydslPredicateExecutor<DeviceEntity>, QuerydslBinderCustomizer<QDeviceEntity> {
    public DeviceEntity getByNameDevice(String name);

    @Override
    default public void customize(QuerydslBindings bindings, QDeviceEntity root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

        bindings.bind(root.description).all((path, value) -> {
            List<? extends String> descriptionList = new ArrayList<>(value);
            return Optional.of(path.contains(descriptionList.get(0)));
        });

    }
}
