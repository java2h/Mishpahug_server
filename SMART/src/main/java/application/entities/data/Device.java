package application.entities.data;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import lombok.*;
        import lombok.extern.slf4j.Slf4j;

        import javax.persistence.*;
        import java.util.Set;

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
public class Device {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="arduino_id", nullable=false)
    private Arduino arduino;

    @Column(name = "pin")
    private Integer pin;

    @OneToMany(mappedBy="device")
    private Set<Option> optionSet;


}