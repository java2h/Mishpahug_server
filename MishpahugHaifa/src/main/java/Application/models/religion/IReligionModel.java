package Application.models.religion;

import Application.entities.ReligionEntity;

import java.util.List;

public interface IReligionModel {
    public ReligionEntity getById(Integer id);
    public List<ReligionEntity> getAll();
    public ReligionEntity add(ReligionEntity data);
    public ReligionEntity update(String oldName, String newName);
    public ReligionEntity getByFullName(String name);
    public List<ReligionEntity> getByName(String name);
    public ReligionEntity remove(Integer id);
    public ReligionEntity update(Integer religionId, String newName);
}
