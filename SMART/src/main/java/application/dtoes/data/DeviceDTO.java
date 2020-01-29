package application.dtoes.data;

import application.entities.data.DeviceEntity;
import application.entities.data.DeviceEntity;
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

    public DeviceDTO(DeviceEntity data) {
        this.name = data.getNameDevice();
        this.description = data.getDescription();
        this.pin = data.getPin();
    }
}
