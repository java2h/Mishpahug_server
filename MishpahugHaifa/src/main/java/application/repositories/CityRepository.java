package application.repositories;

import application.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
        @Query("SELECT c from CityEntity c WHERE c.name = :name")
        public CityEntity getByName(String name);

}
