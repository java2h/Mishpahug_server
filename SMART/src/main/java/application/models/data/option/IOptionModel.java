package application.models.data.option;

import application.dtoes.data.OptionDTO;
import com.querydsl.core.types.Predicate;

public interface IOptionModel {
    public Iterable<OptionDTO> getAll(Predicate predicate);
    public void save(OptionDTO data);
}
