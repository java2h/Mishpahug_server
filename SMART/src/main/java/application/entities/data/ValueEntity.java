package application.entities.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Embeddable
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ValueEntity {
    private Double value;
    private LocalDate dateUpdate;
    private LocalTime timeUpdate;

}
