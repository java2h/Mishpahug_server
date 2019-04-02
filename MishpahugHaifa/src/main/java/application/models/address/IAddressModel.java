package application.models.address;

import application.entities.AddressEntity;
import application.exceptions.ExceptionMishpaha;
import com.querydsl.core.types.Predicate;
import java.util.HashMap;
import java.util.List;

public interface IAddressModel {
    public AddressEntity getById(Integer id);
    public AddressEntity update(HashMap<String, String> data, Integer id);

    AddressEntity deleteByID(Integer id)  throws ExceptionMishpaha;

    void deleteAll();

    public AddressEntity add(AddressEntity data);
    public List<AddressEntity> getAll();
    public Iterable<AddressEntity> getAll(Predicate predicate);
    public AddressEntity remove(Integer id);
}
