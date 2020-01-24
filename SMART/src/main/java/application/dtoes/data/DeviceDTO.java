package application.dtoes.data;

import application.entities.data.Device;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class DeviceDTO {
    private String name;
    private String description;
    private Integer pin;
    private String ipAddress;

    public DeviceDTO(Device data) {
        this.name = data.getName();
        this.description = data.getDescription();
        this.ipAddress = data.getArduino().getIpaddress();
        this.pin = data.getPin();
    }
}
