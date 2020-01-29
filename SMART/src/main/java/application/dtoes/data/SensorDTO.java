package application.dtoes.data;

import application.entities.data.SensorEntity;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class SensorDTO {
    private String name;
    private String description;
    private String address;
    private String ipAddress;

    public SensorDTO(SensorEntity data) {
        this.name = data.getNameSensor();
        this.description = data.getDescription();
        this.address = data.getMacAddress();
    }
}
