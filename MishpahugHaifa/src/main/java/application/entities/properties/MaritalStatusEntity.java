package application.entities.properties;

import application.entities.interfaces.NamedProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "marriage_status", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MaritalStatusEntity implements NamedProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

	public MaritalStatusEntity(String name) {
		super();
		this.name = name;
	}

}
