package application.entities.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "storeproduct")
@ToString(exclude = {"name", "created"})
@EqualsAndHashCode(of = {"name"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class StoreProduct {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "manufactorer_of_product")
    @JsonBackReference("manufactorerofproduct")
    private Manufactorer manufactorer;

    @ManyToMany(mappedBy = "projects")
    private Set<Manufactorer> employees = new HashSet<>();

    @Column(name = "price")
    private Float price;

    @Column(name = "created")
    private DateTime created;

    @Column(name = "updated")
    private DateTime updated;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("productofattribute")
    private Set<StoreProductAttributeRef> attributeRefSet;
}
