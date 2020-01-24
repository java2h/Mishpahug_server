package application.dtoes.data;

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
}
