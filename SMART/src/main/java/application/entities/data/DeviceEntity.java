package application.entities.data;

        import application.dtoes.data.DeviceDTO;
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import lombok.*;
        import lombok.extern.slf4j.Slf4j;

        import javax.persistence.*;
        import java.net.InetAddress;
        import java.net.UnknownHostException;
        import java.util.Set;

@Entity
@Table(name = "device")
@ToString(exclude = {"optionEntities"})
@EqualsAndHashCode(of = {"nameDevice"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DeviceEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "nameDevice")
    private String nameDevice;

    @Column(name = "ipaddress")
    private InetAddress ipaddress;

    @Column(name = "pin")
    private Integer pin;

    @OneToMany(mappedBy="device")
    private Set<OptionEntity> optionEntities;

    public DeviceEntity(DeviceDTO data) throws UnknownHostException {
        this.nameDevice = data.getName();
        this.description = data.getDescription();
        this.pin = data.getPin();
        this.ipaddress = InetAddress.getByName(data.getIpAddress());
    }
}