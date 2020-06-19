package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QValueEntity is a Querydsl query type for ValueEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QValueEntity extends BeanPath<ValueEntity> {

    private static final long serialVersionUID = 114610111L;

    public static final QValueEntity valueEntity = new QValueEntity("valueEntity");

    public final DatePath<java.time.LocalDate> dateUpdate = createDate("dateUpdate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> timeUpdate = createTime("timeUpdate", java.time.LocalTime.class);

    public final NumberPath<Double> value = createNumber("value", Double.class);

    public QValueEntity(String variable) {
        super(ValueEntity.class, forVariable(variable));
    }

    public QValueEntity(Path<? extends ValueEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QValueEntity(PathMetadata metadata) {
        super(ValueEntity.class, metadata);
    }

}

