package application.models.marriagestatus;

import application.entities.MaritalStatusEntity;

import java.util.List;

public interface IMaritalStatusModel {
    public MaritalStatusEntity getByName(String name);

    //TODO
    public List<MaritalStatusEntity> getAll();

    MaritalStatusEntity getById(Integer id);

    void deleteByName(String name);

    void deleteAll();

    MaritalStatusEntity updateName(Integer id, String name);

    MaritalStatusEntity add(MaritalStatusEntity data);
}
