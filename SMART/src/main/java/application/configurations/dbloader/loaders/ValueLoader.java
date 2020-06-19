package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.data.SensorEntity;
import application.entities.data.ValueEntity;
import application.utils.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Loads users
 */
@Slf4j
@Transactional
public class ValueLoader implements ILoader {

	@Autowired
	LoaderDependencies data;

	Random rr = new Random();

	private BufferedReader br;

	public ValueLoader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void load() {
		try {
			List<SensorEntity> sensorEntityList = this.data.sensorRepository.findAll();
			Integer sensorMax = sensorEntityList.size() - 1;
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
			//TODO создать файл для генерации постоянных данных
			for (int i = 0; i < 4096; i++) {
				ValueEntity entity = new ValueEntity();
				entity.setValue(50 - rr.nextDouble()*100);
				entity.setTimeUpdate(RandomGenerator.genTime());
				entity.setDateUpdate(RandomGenerator.genDate());
				SensorEntity sensorEntity = sensorEntityList.get(rr.nextInt(sensorMax));
				sensorEntity.addValue(entity);
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}