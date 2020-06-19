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

	@Bean(name = "salLoader")
	public ILoader salLoader() {
		createBufferedReader(MPHEntity.SAL);
		return new SalLoader(bufferedReader);
	}

	@Bean(name = "sampleLoader")
	public ILoader sampleLoader() {
		createBufferedReader(MPHEntity.SAMPLE);
		return new SampleLoader(bufferedReader);
	}

	@Bean(name = "itemLoader")
	public ILoader itemLoader() {
		createBufferedReader(MPHEntity.ITEM);
		return new ItemLoader(bufferedReader);
	}

	@Bean(name = "ishurLoader")
	public ILoader ishurLoader() {
		createBufferedReader(MPHEntity.ISHUR);
		return new IshurLoader(bufferedReader);
	}

}