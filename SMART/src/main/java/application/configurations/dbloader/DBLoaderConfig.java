package application.configurations.dbloader;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import application.configurations.dbloader.loaders.*;

@Configuration
public class DBLoaderConfig {
	
	@Value("${database-test-folder}") //TODO: test context from Eclipse doesn't load this;
	String testFolder = "short-datafiles";
	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	InputStream inputStream;
	InputStreamReader inputStreamReader;
	BufferedReader bufferedReader; 
	
	private void createBufferedReader(MPHEntity entity) {
		inputStream = classloader.getResourceAsStream(testFolder + "/" + entity.dataFile());
		if (inputStream != null) {
			inputStreamReader= new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
		}
		if (bufferedReader == null) throw new IllegalArgumentException();
	}
	

	@Bean(name = "userLoader")
	public ILoader userLoader() {
		createBufferedReader(MPHEntity.USER);
		return new UserLoader(bufferedReader);	
	}
	@Bean(name = "arduinoLoader")
	public ILoader arduinoLoader() {
		createBufferedReader(MPHEntity.ARDUINO);
		return new UserLoader(bufferedReader);
	}
	@Bean(name = "deviceLoader")
	public ILoader deviceLoader() {
		createBufferedReader(MPHEntity.DEVICE);
		return new UserLoader(bufferedReader);
	}
	@Bean(name = "sensorLoader")
	public ILoader sensorLoader() {
		createBufferedReader(MPHEntity.SENSOR);
		return new UserLoader(bufferedReader);
	}
	@Bean(name = "optionLoader")
	public ILoader optionLoader() {
		createBufferedReader(MPHEntity.OPTIONS);
		return new UserLoader(bufferedReader);
	}

}