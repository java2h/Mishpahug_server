package application.models.data.sensor;

import application.dtoes.data.SensorDTO;
import application.entities.data.SensorEntity;
import application.repositories.SensorRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

}
