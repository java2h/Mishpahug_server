package application.configurations.dbloader;

import application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LoaderDependencies { //TODO: stability risk with public access modifier on all repositories;
	@Autowired
	public Environment env;
	
	@Autowired
	public UserRepository userRepository;

	@Autowired
	public SampleRepository sampleRepository;

	@Autowired
	public SalRepository salRepository;

	@Autowired
	public IshurRepository ishurRepository;

	@Autowired
	public ItemRepository itemRepository;

	public LoaderDependencies() {
	}
}