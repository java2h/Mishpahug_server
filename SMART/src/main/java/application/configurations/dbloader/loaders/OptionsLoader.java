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
public class OptionsLoader implements ILoader {

	@Autowired
	LoaderDependencies data;

	private BufferedReader br;

	public OptionsLoader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void load() {
		try {
			this.data.optionRepository.deleteAll();
			this.data.optionRepository.flush();
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
			String detail;
			while ((detail = br.readLine()) != null) {

			}
			log.debug("DBLoadTest -> OptionLoader -> In repository " + this.data.optionRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}