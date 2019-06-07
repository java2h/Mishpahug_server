package application.models.address;

import java.util.HashMap;
import java.util.List;

import application.exceptions.NotFoundGenderWithIDException;
import com.querydsl.core.types.Predicate;

import application.entities.AddressEntity;

public interface IAddressModel {
    public AddressEntity getById(Integer id);

    public AddressEntity update(HashMap<String, String> data, Integer id);

    AddressEntity deleteByID(Integer id);

    void deleteAll();

    public AddressEntity add(AddressEntity data);

    public List<AddressEntity> getAll();

    public Iterable<AddressEntity> getAll(Predicate predicate);

    public AddressEntity remove(Integer id);
}
