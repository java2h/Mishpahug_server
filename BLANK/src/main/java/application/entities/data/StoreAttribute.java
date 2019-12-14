package application.entities.data;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "storeattribute")
@ToString(exclude = {"name", "description"})
@EqualsAndHashCode(of = {"name"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class StoreAttribute {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_on_front")
    private Integer displayOnFront;

    @Column(name = "use_in_filter")
    private Integer useInFilter;

    @Column(name = "use_in_compare")
    private Integer useInCompare;


}
