package application.dtoes.data;

import application.entities.data.Option;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class OptionDTO {
    private String name;
    private Integer type;
    private Integer ifType; // 0= 1< 2>
    private String sensorName;
    private String deviceName;

    public OptionDTO(Option data) {
        this.name = data.getName();
        this.deviceName = data.getDevice().getName();
        this.type = data.getType();
        this.ifType = data.getIfType();
        this.sensorName = data.getSensor().getName();
    }
}
