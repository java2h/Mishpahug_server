package application.models.data.device;

import application.dtoes.data.DeviceDTO;
import application.entities.data.DeviceEntity;
import com.querydsl.core.types.Predicate;

public interface IDeviceModel {
    public Iterable<DeviceDTO> getAll(Predicate predicate);
    public DeviceEntity getByName(String name);
    public void save(DeviceDTO data);
    public void delete(Integer id);
    public void delete();
}
