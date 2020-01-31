package application.configurations;

import application.entities.data.OptionEntity;
import application.repositories.DeviceRepository;
import application.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class CheckingData {
    private Integer delta = 10;
    private String sendIP = "";
    private Integer sendPIN = -1;
    private  Integer sendCOM = 0;
    public static final RestTemplate restTemplate = new RestTemplate();


    @Autowired
    OptionRepository optionRepository;

    @Autowired
    DeviceRepository deviceRepository;

    private void scheduleFixedDelayTaskMethod(Integer num){
        if (deviceRepository.findAll().size()>num)
        {
            List<OptionEntity> optionEntityList = optionRepository.getByDevice_Id(num);
            //System.out.println("scheduleFixedDelayTask -> Number = " + num + " -> Size -> " + optionEntityList.size());
            optionEntityList.forEach(z -> {
                //System.out.println("scheduleFixedDelayTask - " + num + " -> data = " + z);
                //TODO написать прохождение по значениям
                switch (z.getType()){
                    case  0:{
                            //TODO Sensor
                        switch (z.getIfType()){
                            case 0:{
                                if (z.getSensor().getValue() > z.getData())
                                {
                                    sendIP = z.getDevice().getIpaddress();
                                    sendPIN = z.getDevice().getPin();
                                    sendCOM = z.getCommand();
                                }
                                break;
                            }
                            case 1:{
                                if ((z.getSensor().getValue() > z.getData() - delta) && (z.getSensor().getValue() < z.getData() + delta))
                                {
                                    sendIP = z.getDevice().getIpaddress();
                                    sendPIN = z.getDevice().getPin();
                                    sendCOM = z.getCommand();
                                }
                                break;
                            }
                            case 2:{
                                if (z.getSensor().getValue() < z.getData())
                                {
                                    sendIP = z.getDevice().getIpaddress();
                                    sendPIN = z.getDevice().getPin();
                                    sendCOM = z.getCommand();
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case  1:{
                            //TODO Time
                        LocalDateTime dateTime = LocalDateTime.of(z.getDateS().getYear(), z.getDateS().getMonth(), z.getDateS().getDayOfMonth(), z.getTimeS().getHour(), z.getTimeS().getMinute());
                        Date now = new Date();
                        Instant curr = now.toInstant();
                        LocalDateTime currentDT = LocalDateTime.ofInstant(curr, ZoneId.systemDefault());
                        if (currentDT.isAfter(dateTime))
                        {
                            sendIP = z.getDevice().getIpaddress();
                            sendPIN = z.getDevice().getPin();
                            sendCOM = z.getCommand();
                        }
                        break;
                    }
                }
                //TODO sending
                String url = "http://" + sendIP;
                String urn = "/in?pin=" + sendPIN.toString() + "&value=" + sendCOM;
                RequestEntity<?> request = null;
                try {
                    request = new RequestEntity(null, HttpMethod.POST, new URI(url+urn));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                ResponseEntity<String> responseS = restTemplate.exchange(request, String.class);
            });
        }

    }

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask0() {
        Integer deviceNumber = 0;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask1() {
        Integer deviceNumber = 1;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask2() {
        Integer deviceNumber = 2;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask3() {
        Integer deviceNumber = 3;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask4() {
        Integer deviceNumber = 4;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask5() {
        Integer deviceNumber = 5;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask6() {
        Integer deviceNumber = 6;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask7() {
        Integer deviceNumber = 7;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask8() {
        Integer deviceNumber = 8;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask9() {
        Integer deviceNumber = 9;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask10() {
        Integer deviceNumber = 10;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask11() {
        Integer deviceNumber = 11;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask12() {
        Integer deviceNumber = 12;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask13() {
        Integer deviceNumber = 13;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask14() {
        Integer deviceNumber = 14;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask15() {
        Integer deviceNumber = 15;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask16() {
        Integer deviceNumber = 16;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask17() {
        Integer deviceNumber = 17;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask18() {
        Integer deviceNumber = 18;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask19() {
        Integer deviceNumber = 19;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask20() {
        Integer deviceNumber = 20;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask21() {
        Integer deviceNumber = 21;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask22() {
        Integer deviceNumber = 22;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask23() {
        Integer deviceNumber = 23;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask24() {
        Integer deviceNumber = 24;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask25() {
        Integer deviceNumber = 25;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask26() {
        Integer deviceNumber = 26;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask27() {
        Integer deviceNumber = 27;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask28() {
        Integer deviceNumber = 28;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask29() {
        Integer deviceNumber = 29;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask30() {
        Integer deviceNumber = 30;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask31() {
        Integer deviceNumber = 31;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask32() {
        Integer deviceNumber = 32;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask33() {
        Integer deviceNumber = 33;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask34() {
        Integer deviceNumber = 34;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask35() {
        Integer deviceNumber = 35;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask36() {
        Integer deviceNumber = 36;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask37() {
        Integer deviceNumber = 37;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask38() {
        Integer deviceNumber = 38;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask39() {
        Integer deviceNumber = 39;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask40() {
        Integer deviceNumber = 40;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask41() {
        Integer deviceNumber = 41;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask42() {
        Integer deviceNumber = 42;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask43() {
        Integer deviceNumber = 43;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask44() {
        Integer deviceNumber = 44;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask45() {
        Integer deviceNumber = 45;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask46() {
        Integer deviceNumber = 46;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask47() {
        Integer deviceNumber = 47;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask48() {
        Integer deviceNumber = 48;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask49() {
        Integer deviceNumber = 49;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask50() {
        Integer deviceNumber = 50;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask51() {
        Integer deviceNumber = 51;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask52() {
        Integer deviceNumber = 52;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask53() {
        Integer deviceNumber = 53;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask54() {
        Integer deviceNumber = 54;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask55() {
        Integer deviceNumber = 55;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask56() {
        Integer deviceNumber = 56;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask57() {
        Integer deviceNumber = 57;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask58() {
        Integer deviceNumber = 58;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask59() {
        Integer deviceNumber = 59;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask60() {
        Integer deviceNumber = 60;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask61() {
        Integer deviceNumber = 61;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask62() {
        Integer deviceNumber = 62;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask63() {
        Integer deviceNumber = 63;
        scheduleFixedDelayTaskMethod(deviceNumber);
    }

}
