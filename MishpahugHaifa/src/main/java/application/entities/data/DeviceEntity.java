package application.entities.data;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import lombok.*;
        import lombok.extern.slf4j.Slf4j;

        import javax.persistence.*;

@Entity
@Table(name = "device")
@ToString(exclude = {"iparduino", "port", "description"})
@EqualsAndHashCode(of = {"name"})
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

    @Column(name = "name")
    private String name;

    @Column(name = "iparduino")
    private String ipArduino;

    @Column(name = "port")
    private Integer port;


}