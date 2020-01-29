package application.entities.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sensor")
@ToString(exclude = {"ipAddress", "macaddress", "description"})
@EqualsAndHashCode(of = {"ipAddress", "macaddress", "description"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SensorEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nameSensor")
    private String nameSensor;

    @Column(name = "description")
    private String description;

    @Column(name = "macaddress")
    private String macAddress;

    @Column(name = "ipaddress")
    private String ipaddress;

    @Column(name = "value")
    private Float value;

    @Column(name = "pin")
    private Integer pin;

    @OneToMany(mappedBy="sensor")
    private Set<OptionEntity> optionEntities;

    }
