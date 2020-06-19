package application.entities.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "optionz")
@ToString(exclude = {"device", "sensor"})
@EqualsAndHashCode(of = {"nameOption"})
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
    private SensorEntity sensor;

    @Column(name = "description")
    private String description;

    @Column(name = "nameOption")
    private String nameOption;

    @Column(name = "data")
    private Integer data;

    @Column(name = "command")
    private Integer command;

    @Column(name = "types")
    private Integer type;

    @Column(name = "iftype")
    private Integer ifType;
    // 0= 1< 2>
    @Column(name = "dates")
    private LocalDate dateS;

    @Column(name = "times")
    private LocalTime timeS;
}