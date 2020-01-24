package application.entities.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "arduino")
@ToString(exclude = {"ipaddress", "description", "description"})
@EqualsAndHashCode(of = {"ipaddress", "description"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArduinoEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ipaddress")
    private String ipaddress;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private Integer type; // 0-relay 1-dimmer 2-sensor

    @OneToMany(mappedBy="arduinoEntity")
    private Set<DeviceEntity> deviceEntitySet;

    @OneToMany(mappedBy="arduinoEntity")
    private Set<SensorEntity> sensorEntitySet;
}
