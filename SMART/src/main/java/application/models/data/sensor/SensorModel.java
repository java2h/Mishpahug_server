package application.models.data.sensor;

import application.dtoes.data.SensorDTO;
import application.entities.data.SensorEntity;
import application.repositories.SensorRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class SensorModel implements ISensorModel {
    @Autowired
    SensorRepository sensorRepository;


    @Override
    public Iterable<SensorDTO> getAll(Predicate predicate) {
        List<SensorDTO> sensorDTOS = new ArrayList<>();
        sensorRepository.findAll(predicate).forEach(x -> sensorDTOS.add(new SensorDTO(x)));
        return sensorDTOS;
    }

    @Override
    public SensorEntity getByName(String name) {
        return sensorRepository.getByNameSensor(name);
    }

    @Override
    public SensorEntity getByMAC(String mac) {
        return sensorRepository.getByMacAddress(mac);
    }

    @Override
    public void save(SensorDTO data) {
        try {
            sensorRepository.save(new SensorEntity(data));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public void delete() {
        sensorRepository.deleteAll();
    }

    @Override
    public void deleteData() {
        sensorRepository.findAll().forEach(x -> {
            x.clearData();
        });
    }

    @Override
    public void deleteData(String mac) {
        sensorRepository.getByMacAddress(mac).clearData();
    }

    @Override
    public void deleteData(String mac, LocalDate dateBegin, LocalDate dateEnd) {
        sensorRepository.getByMacAddress(mac).clearData(dateBegin, dateEnd);
    }

    @Override
    public void deleteData(LocalDate dateBegin, LocalDate dateEnd) {
        sensorRepository.findAll().forEach(x -> {
            x.clearData(dateBegin, dateEnd);
        });
    }

}
