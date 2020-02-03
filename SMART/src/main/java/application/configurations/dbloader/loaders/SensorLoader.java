package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.data.SensorEntity;
import application.utils.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

/**
 * Loads users
 */
@Slf4j
@Transactional
public class SensorLoader implements ILoader {

	@Autowired
	LoaderDependencies data;
	private Integer selector = 1;
	private BufferedReader br;

	public SensorLoader(BufferedReader br) {
		this.br = br;
	}
	//TODO создать файл для генерации постоянных данных
	@Override
	public void load() {
		try {
			this.data.sensorRepository.deleteAll();
			this.data.sensorRepository.flush();

			Random random = new Random();
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
			if (selector ==1){
				String detail;
				while ((detail = br.readLine()) != null) {
					String[] data = detail.split("!");
					SensorEntity entity = new SensorEntity();
					entity.setNameSensor(data[0]);
					entity.setIpaddress(InetAddress.getByName(data[1]));
					entity.setMacAddress(data[2]);
					entity.setDescription(data[3]);
					this.data.sensorRepository.save(entity);
				}
			}
			if (selector ==2){
				for (int i = 0; i < 32; i++) {
					SensorEntity entity = new SensorEntity();
					entity.setNameSensor(RandomGenerator.genText(97,122));
					entity.setIpaddress(InetAddress.getByName(RandomGenerator.genIP()));
					entity.setMacAddress(RandomGenerator.genMAC());
					entity.setDescription(RandomGenerator.genText(97,122));
					this.data.sensorRepository.save(entity);
				}
			}
			log.debug("DBLoadTest -> SensorLoader -> In repository " + this.data.sensorRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}