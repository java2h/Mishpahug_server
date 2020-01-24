package application.dtoes.data;

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
}
