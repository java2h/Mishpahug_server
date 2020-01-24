package application.dtoes.data;

import application.entities.data.Sensor;
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

    public SensorDTO(Sensor data) {
        this.name = data.getName();
        this.description = data.getDescription();
        this.address = data.getMacAddress();
        this.ipAddress = data.getArduino().getIpaddress();
    }
}
