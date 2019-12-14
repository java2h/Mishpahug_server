package application.entities.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
@Entity
@Table(name = "storeproductattributeref")
@ToString(exclude = {"name", "created"})
@EqualsAndHashCode(of = {"name"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class StoreProductAttributeRef {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product")
    @JsonBackReference("productofattribute")
    private StoreProduct product;

     @Column(name = "value")
    private String value;


    @ManyToOne(optional = false)
    @JoinColumn(name = "attribute")
    @JsonBackReference("attributeofproduct")
    private StoreAttribute attribute;


}
