package application.entities.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "option")
@ToString(exclude = {"name", "port", "description"})
@EqualsAndHashCode(of = {"name"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OptionEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="device_id", nullable=false)
    private DeviceEntity device;

    @ManyToOne
    @JoinColumn(name="sensor_id", nullable=false)
    private Sensor sensor;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "port")
    private Integer port;

    @Column(name = "type")
    private Integer type;

    @Column(name = "iftype")
    private Integer ifType;
    // 0= 1< 2>

}