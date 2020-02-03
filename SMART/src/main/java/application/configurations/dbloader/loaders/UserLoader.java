package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.UserEntity;
import application.utils.RandomGenerator;
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
public class UserLoader implements ILoader {

	@Autowired 
	LoaderDependencies data;

	private BufferedReader br;

	public UserLoader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void load() {
		try {
			this.data.userRepository.findAll().forEach(UserEntity::putIntoDeletionQueue);
			this.data.userRepository.deleteAll();
			this.data.userRepository.flush();
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
			String detail;
			while ((detail = br.readLine()) != null) {
				String[] data = detail.split("!");
				UserEntity user = new UserEntity();
				user.setEncrytedPassword(DigestUtils.md5Hex((data[0].split("@")[0])));
				user.setUserName(data[0].split("@")[0]);
				user.setPhoneNumber(RandomGenerator.genText(48,57));
				user.setDateOfBirth(RandomGenerator.genDate());
				user.setFirstName(RandomGenerator.genText(97,122));
				user.setLastName(RandomGenerator.genText(97,122));
				user.activate();
				user.setEMail(data[0]);
				user.loadAvatar("av.png");
				log.debug("DBLoadTest -> UserLoader -> userentity = " + user);
				this.data.userRepository.save(user);
			}
			log.debug("DBLoadTest -> UserLoader -> In repository " + this.data.userRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}