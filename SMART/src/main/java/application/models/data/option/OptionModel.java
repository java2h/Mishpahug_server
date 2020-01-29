package application.models.data.option;

import application.dtoes.data.OptionDTO;
import application.repositories.OptionRepository;
import application.utils.OptionConverter;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OptionModel implements IOptionModel {
    @Autowired
    OptionRepository optionRepository;

    @Override
    public Iterable<OptionDTO> getAll(Predicate predicate) {
        List<OptionDTO> optionDTOS = new ArrayList<>();
        optionRepository.findAll(predicate).forEach(x -> optionDTOS.add(new OptionDTO(x)));
        return optionDTOS;
    }

    @Override
    public void save(OptionDTO data) {
        OptionConverter converter = new OptionConverter();
        optionRepository.save(converter.converter(data));
    }
}
