package application.models.gender;

import application.entities.GenderEntity;

import java.util.List;

public interface IGenderModel {
    public GenderEntity getByName(String name);

    GenderEntity getById(Integer id);

    void deleteByName(String name);

    void deleteAll();

    GenderEntity add(GenderEntity data);

    GenderEntity updateName(Integer id, String name);

    public List<GenderEntity> getAll();
    //TODO
}
