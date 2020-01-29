package application.models.data.sensor;

import application.dtoes.data.SensorDTO;
import application.entities.data.SensorEntity;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ISensorModel {
    public Iterable<SensorDTO> getAll(Predicate predicate);
    public SensorEntity getByName(String name);
}
