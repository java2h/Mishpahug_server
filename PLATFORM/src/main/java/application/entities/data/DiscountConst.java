package application.entities.data;

import application.entities.embeded.Discount;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "DiscountConst")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DiscountConst {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Discount[] discounts;
}
