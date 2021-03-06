package application.repositories;

import application.entities.properties.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity, Integer> {
    public GenderEntity getByName(String name);
    public void deleteByName(String name);
    public Boolean existsByName(String name);
}
