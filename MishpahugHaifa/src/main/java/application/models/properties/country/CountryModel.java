package application.models.properties.country;

import application.entities.properties.CountryEntity;
import application.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CountryModel implements ICountryModel {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public CountryEntity getById(Integer id) {
        return countryRepository.getOne(id);
    }

    @Override
    public CountryEntity addCountry(CountryEntity data) {
        return countryRepository.save(data);
    }

    @Override
    public void deleteByName(String name) {
        countryRepository.deleteByName(name);
    }

    @Override
    public void deleteAll() {
        countryRepository.deleteAll();
    }

    @Override
    public CountryEntity updateName(Integer id, String name) {
        CountryEntity countryEntity = countryRepository.getOne(id);
        countryEntity.setName(name);
        return countryRepository.save(countryEntity);
    }

    @Override
    public CountryEntity getByName(String name) {
        return countryRepository.getByName(name);
    }

    @Override
    public List<CountryEntity> getAll() {
        return countryRepository.findAll();
    }
}
