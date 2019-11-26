package application.entities.embeded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
@ToString
@EqualsAndHashCode
public class Discount {
    @NotNull
    private Integer count;

    @NotNull
    private Float percent;
}
