package application.entities.data;

import application.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "manufactorer")
@ToString(exclude = {"name", "description"})
@EqualsAndHashCode(of = {"name"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Manufactorer {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Manufactorer_Product",
            joinColumns = { @JoinColumn(name = "manufactorer_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    Set<StoreProduct> projects = new HashSet<>();

    @OneToOne(mappedBy = "manufactorer")
    private UserEntity user;


    @OneToMany(mappedBy = "manufactorer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("manufactorerofproduct")
    private Set<StoreProduct> products;
}
