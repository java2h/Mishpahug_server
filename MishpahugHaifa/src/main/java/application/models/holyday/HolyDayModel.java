package application.models.holyday;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import application.dto.forholiday.HolidayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import application.entities.HoliDayEntity;
import application.repositories.HolyDayRepository;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class HolyDayModel implements IHolyDayModel {

    @Autowired
    HolyDayRepository holyDayRepository;

    @Override
    public HoliDayEntity getByReligion(Integer religionId) {
        return holyDayRepository.getOne(religionId);
    }

    @Override
    public List<HoliDayEntity> getByYear(Integer year) {
        return null;//TODO
    }

    @Override
    public List<HoliDayEntity> getAll(){
        return holyDayRepository.findAll();
    }

    @Override
    public HoliDayEntity getByName(String name){
        return holyDayRepository.getByFullName(name);
    }

    @Override
    public void updateFromServer(HolidayDTO dto) {
        System.out.println(dto);
        HoliDayEntity holiDayEntity = new HoliDayEntity();
        holiDayEntity.setName(dto.getName());
        holiDayEntity.setDescription(dto.getDescription());
        holiDayEntity.setDate(LocalDate.parse(dto.getDate()));
        holyDayRepository.save(holiDayEntity);
    }
}
