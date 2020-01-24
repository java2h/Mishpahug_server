package application.entities.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "arduino")
@ToString(exclude = {"ipaddress", "description", "type"})
@EqualsAndHashCode(of = {"ipaddress", "type"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Arduino {

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

    @OneToMany(mappedBy="arduino")
    private Set<Device> deviceSet;

    @OneToMany(mappedBy="arduino")
    private Set<Sensor> sensorSet;
}
