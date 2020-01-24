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
    @JoinColumn(name="deviceEntity_id", nullable=false)
    private DeviceEntity deviceEntity;

    @ManyToOne
    @JoinColumn(name="sensorEntity_id", nullable=false)
    private SensorEntity sensorEntity;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "port")
    private Integer port;

    @Column(name = "type")
    private Integer type;


}