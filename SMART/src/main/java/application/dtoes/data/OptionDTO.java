package application.dtoes.data;

import application.entities.data.OptionEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class OptionDTO {
    private String name;
    private Integer type; // 1Time 2Sensor
    private Integer ifType; // 0= 1< 2>
    private String sensorName;
    private String deviceName;
    private LocalDate date;
    private LocalTime time;
    private Float data;

    public OptionDTO(OptionEntity data) {
        this.name = data.getNameOption();
        this.deviceName = data.getDevice().getNameDevice();
        this.type = data.getType();
        this.ifType = data.getIfType();
        this.sensorName = data.getSensor().getNameSensor();
        this.date = data.getDateS();
        this.time = data.getTimeS();
    }
}
