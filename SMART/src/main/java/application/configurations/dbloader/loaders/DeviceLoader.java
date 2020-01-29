package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.data.DeviceEntity;
import application.utils.RandomString;
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
public class DeviceLoader implements ILoader {

	@Autowired
	LoaderDependencies data;

	private BufferedReader br;

	public DeviceLoader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void load() {
		try {
			this.data.deviceRepository.deleteAll();
			this.data.deviceRepository.flush();
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception

			Random random = new Random();

			for (int i = 0; i < 42; i++) {
				DeviceEntity entity = new DeviceEntity();
				entity.setDescription(RandomString.genText(97,122));
				entity.setNameDevice(RandomString.genText(97,122));
				entity.setPin(3);
				this.data.deviceRepository.save(entity);
			}
			log.debug("DBLoadTest -> DeviceLoader -> In repository " + this.data.deviceRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}