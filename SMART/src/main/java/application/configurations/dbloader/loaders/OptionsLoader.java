package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.data.DeviceEntity;
import application.entities.data.OptionEntity;
import application.entities.data.SensorEntity;
import application.utils.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Loads users
 */
@Slf4j
@Transactional
public class OptionsLoader implements ILoader {

	@Autowired
	LoaderDependencies data;

	private BufferedReader br;

	public OptionsLoader(BufferedReader br) {
		this.br = br;
	}
	//TODO создать файл для генерации постоянных данных
	@Override
	public void load() {
		try {
			Random rr = new Random();
			this.data.optionRepository.deleteAll();
			this.data.optionRepository.flush();
			List<SensorEntity> sensorEntityList = this.data.sensorRepository.findAll();
			Integer sensorMax = sensorEntityList.size() - 1;
			List<DeviceEntity> deviceEntityList = this.data.deviceRepository.findAll();
			Integer deviceMax = deviceEntityList.size() - 1;
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
			String detail;
			for (int i = 0; i < 512; i++) {
				OptionEntity entity = new OptionEntity();
				entity.setDevice(deviceEntityList.get(rr.nextInt(deviceMax)));
				entity.setSensor(sensorEntityList.get(rr.nextInt(sensorMax)));
				entity.setTimeS(RandomGenerator.genTime());
				entity.setDateS(RandomGenerator.genDate());
				entity.setIfType(rr.nextInt(1024)%3);
				entity.setType(rr.nextInt(1024)%3);
				entity.setNameOption(RandomGenerator.genText(97, 122));
				entity.setDescription(RandomGenerator.genText(97, 122));
				this.data.optionRepository.save(entity);
			}
			log.debug("DBLoadTest -> OptionLoader -> In repository " + this.data.optionRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}