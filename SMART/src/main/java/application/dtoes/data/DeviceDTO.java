package application.dtoes.data;

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

    public DeviceDTO() {
    }
}
