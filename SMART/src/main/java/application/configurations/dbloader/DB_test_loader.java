package application.configurations.dbloader;

import application.configurations.dbloader.loaders.ILoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Load the production DB with integration test data; TODO: make it a Spring
 * profile
 */
@Component
@Transactional
public class DB_test_loader implements CommandLineRunner {

	@Autowired
	@Qualifier("userLoader")
	private ILoader userLoader;

	@Autowired
	@Qualifier("deviceLoader")
	private ILoader deviceLoader;

	@Autowired
	@Qualifier("sensorLoader")
	private ILoader sensorLoader;

	@Autowired
	@Qualifier("optionLoader")
	private ILoader optionLoader;


	@Override
	public void run(String... args) throws Exception {
		loadTest(MPHEntity.USER);
		loadTest(MPHEntity.SENSOR);
		loadTest(MPHEntity.DEVICE);
		loadTest(MPHEntity.OPTIONS);
		loadTest(MPHEntity.LOGS);

	}

	public void loadTest(MPHEntity entity) {

		switch (entity) {
		case USER: {
			userLoader.load();
			break;
		}
			case DEVICE: {
			deviceLoader.load();
			break;
		}
			case SENSOR: {
			sensorLoader.load();
			break;
		}
			case OPTIONS: {
			optionLoader.load();
			break;
		}

		}

	}

	/*
	 * List<TemplateEntity> templateEntities = templateRepository.findAll(); for
	 * (TemplateEntity x:templateEntities ) { JPGDocumentFormat jpgDocumentFormat =
	 * new JPGDocumentFormat();
	 * jpgDocumentFormat.createInvitationFromTemplate("d://211.jpeg", "d://" +
	 * x.getNameOption() + ".jpg", "fontname", 8, x, 1 , 1);
	 * EMailSender.sender("mishpahug2019@gmail.com", "marina1986ANGEL", "test",
	 * "dfgsdfgsdfg", "mrcolombo1985remote@gmail.com", "d://" + x.getNameOption() +
	 * ".jpg"); }
	 */


}
