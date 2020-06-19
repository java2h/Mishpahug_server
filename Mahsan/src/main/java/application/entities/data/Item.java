package application.entities.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item")
//@ToString
//@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial")
    private String serial;

    @ManyToOne
    @JoinColumn(name="sal_id", nullable=false)
    private Sal sal;

    @Column(name = "positioninsal")
    private Integer positionInSal;

    @ManyToOne
    @JoinColumn(name="sample_id", nullable=false)
    private Sample sample;


}
