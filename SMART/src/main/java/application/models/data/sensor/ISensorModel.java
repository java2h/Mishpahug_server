package application.models.data.sensor;

import application.dtoes.data.SensorDTO;
import application.entities.data.SensorEntity;
import com.querydsl.core.types.Predicate;

import java.time.LocalDate;

public interface ISensorModel {
    public Iterable<SensorDTO> getAll(Predicate predicate);
    public SensorEntity getByName(String name);
    public SensorEntity getByMAC(String mac);
    public void save(SensorDTO data);
    public void delete(Integer id);
    public void delete();
    public void deleteData();
    public void deleteData(String mac);
    public void deleteData(String mac, LocalDate dateBegin, LocalDate dateEnd);
    public void deleteData(LocalDate dateBegin, LocalDate dateEnd);
}
