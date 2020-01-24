package application.repositories;

import application.entities.data.Device;
import application.entities.data.QDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

public interface DeviceRepository  extends JpaRepository<Device, Integer>,
        QuerydslPredicateExecutor<Device>, QuerydslBinderCustomizer<QDevice> {
}
