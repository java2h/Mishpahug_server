package application.models.data.device;

import application.dtoes.data.DeviceDTO;
import application.entities.data.DeviceEntity;
import application.repositories.DeviceRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class DeviceModel implements IDeviceModel {
    @Autowired
    DeviceRepository deviceRepository;


    @Override
    public Iterable<DeviceDTO> getAll(Predicate predicate) {
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        deviceRepository.findAll(predicate).forEach(x -> deviceDTOS.add(new DeviceDTO(x)));
        return deviceDTOS;
    }

    @Override
    public DeviceEntity getByName(String name) {
        return deviceRepository.getByNameDevice(name);
    }

    @Override
    public void save(DeviceDTO data) {
        try {
            deviceRepository.save(new DeviceEntity(data));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public void delete() {
        deviceRepository.deleteAll();
    }
}
