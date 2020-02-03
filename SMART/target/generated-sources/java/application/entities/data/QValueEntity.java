package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QValueEntity is a Querydsl query type for ValueEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QValueEntity extends EntityPathBase<ValueEntity> {

    private static final long serialVersionUID = 114610111L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QValueEntity valueEntity = new QValueEntity("valueEntity");

    public final DatePath<java.time.LocalDate> dateUpdate = createDate("dateUpdate", java.time.LocalDate.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QSensorEntity sensor;

    public final TimePath<java.time.LocalTime> timeUpdate = createTime("timeUpdate", java.time.LocalTime.class);

    public final NumberPath<Double> value = createNumber("value", Double.class);

    public QValueEntity(String variable) {
        this(ValueEntity.class, forVariable(variable), INITS);
    }

    public QValueEntity(Path<? extends ValueEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QValueEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QValueEntity(PathMetadata metadata, PathInits inits) {
        this(ValueEntity.class, metadata, inits);
    }

    public QValueEntity(Class<? extends ValueEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sensor = inits.isInitialized("sensor") ? new QSensorEntity(forProperty("sensor")) : null;
    }

}

