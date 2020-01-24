package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.UserEntity;
import application.utils.RandomDate;
import application.utils.RandomString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Loads users
 */
@Slf4j
@Transactional
public class ArduinoLoader implements ILoader {

	@Autowired
	LoaderDependencies data;

	private BufferedReader br;

	public ArduinoLoader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void load() {
		try {
			/*this.data.userRepository.findAll().forEach(UserEntity::putIntoDeletionQueue);
			this.data.userRepository.deleteAll();
			this.data.userRepository.flush();*/
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
			String detail;
			while ((detail = br.readLine()) != null) {

			}
			log.debug("DBLoadTest -> ArduinoLoader -> In repository " + this.data.arduinoRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}