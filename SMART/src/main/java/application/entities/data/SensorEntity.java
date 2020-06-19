package application.entities.data;

import application.dtoes.data.SensorDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sensor")
@ToString(exclude = {"ipaddress", "macAddress", "description"})
@EqualsAndHashCode(of = {"ipaddress", "macAddress", "description"})
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
    private InetAddress ipaddress;

    @Column(name = "pin")
    private Integer pin;

    @ElementCollection
    private List<ValueEntity> values = new ArrayList<>();

    @OneToMany(mappedBy="sensor")
    private Set<OptionEntity> optionEntities = new HashSet<>();

    public SensorEntity(SensorDTO data) throws UnknownHostException {
        this.nameSensor = data.getName();
        this.description = data.getDescription();
        this.macAddress = data.getAddress();
        this.ipaddress = InetAddress.getByName(data.getIpAddress());
    }

    public Double getLastValue(){
        return (Double) values.toArray()[values.size()-1];
    }

    public void addValue(ValueEntity data){
        values.add(data);
    }

    public void clearData(){
        values.clear();
    }

    public void clearData(LocalDate dateBegin, LocalDate dateEnd){
        values.forEach(x->{
            if ((x.getDateUpdate().isAfter(dateBegin)) && (x.getDateUpdate().isBefore(dateEnd))){
                values.remove(x);
            }
        });
    }
}
