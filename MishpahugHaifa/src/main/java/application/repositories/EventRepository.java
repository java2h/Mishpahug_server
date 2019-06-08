package application.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;

import application.entities.EventEntity;
import application.entities.QEventEntity;
import application.entities.QSubscriptionEntity;

//TODO: disallow/hide DELETE;
public interface EventRepository extends JpaRepository<EventEntity, Integer>,
        QuerydslPredicateExecutor<EventEntity>, QuerydslBinderCustomizer<QEventEntity>
        {


    Logger log = LoggerFactory.getLogger(EventRepository.class);

    public EventEntity getByNameOfEvent(String name);
    public List<EventEntity> getByUserEntityOwner_UserName(String userName);
    public Boolean existsByDateAndTimeAndUserEntityOwner(LocalDate date, LocalTime time, UserEntity owner);
    
    @Override
    default public void customize(QuerydslBindings bindings, QEventEntity root) {
        log.debug("EventRepository -> customize-> bindings = " + root.toString());
        bindings.bind(String.class).first((StringPath path, String value) -> {
            log.debug("EventRepository -> customize-> value = " + value);
            log.debug("EventRepository -> customize-> path = " + path);
            return path.containsIgnoreCase(value);
        });

        bindings.bind(root.nameOfEvent).all((path, value) -> {
            log.debug("EventRepository -> customize-> value = " + value);
            log.debug("EventRepository -> customize-> path = " + path);
            List<? extends String> NamesOfEvents = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfEvents.get(0)));
        });

        bindings.bind(root.holiDay.name).all((path, value) -> {
            log.debug("EventRepository -> customize-> value = " + value);
            log.debug("EventRepository -> customize-> path = " + path);
            List<? extends String> NamesOfHolidays = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfHolidays.get(0)));
        });

        bindings.bind(root.addressEntity.cityEntity.name).all((path, value) -> {
            log.debug("EventRepository -> customize-> value = " + value);
            log.debug("EventRepository -> customize-> path = " + path);
            List<? extends String> NamesOfCities = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfCities.get(0)));
        });

        bindings.bind(root.userEntityOwner.userName).all((path, value) -> {
            log.debug("EventRepository -> customize-> value = " + value);
            log.debug("EventRepository -> customize-> path = " + path);
            List<? extends String> OwnerUserNames= new ArrayList<>(value);
            return Optional.of(path.contains(OwnerUserNames.get(0))); //Why get(0)?
        });
        
        bindings.bind(root.subscriptions.any().guest.userName).all((path, value) -> {
            log.debug("EventRepository -> customize-> value = " + value);
            log.debug("EventRepository -> customize-> path = " + path);
            List<? extends String> GuestUserNames= new ArrayList<>(value);
            return Optional.of(path.contains(GuestUserNames.get(0))); //Why get(0)?
        });


        bindings.bind(root.date).all((path, value) -> {
            log.debug("EventRepository -> customize-> value = " + value);
            log.debug("EventRepository -> customize-> path = " + path);
            List<? extends LocalDate> dates = new ArrayList<>(value);
            if (dates.size() == 1) {
                return Optional.of(path.eq(dates.get(0)));
            } else {
                LocalDate from = dates.get(0);
                LocalDate to = dates.get(1);
                return Optional.of(path.between(from, to));
            }
        });
    }

}
