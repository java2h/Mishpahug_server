package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOptionEntity is a Querydsl query type for OptionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOptionEntity extends EntityPathBase<OptionEntity> {

    private static final long serialVersionUID = 1204504333L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOptionEntity optionEntity = new QOptionEntity("optionEntity");

    public final NumberPath<Integer> command = createNumber("command", Integer.class);

    public final NumberPath<Integer> data = createNumber("data", Integer.class);

    public final DatePath<java.time.LocalDate> dateS = createDate("dateS", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final QDeviceEntity device;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> ifType = createNumber("ifType", Integer.class);

    public final StringPath nameOption = createString("nameOption");

    public final QSensorEntity sensor;

    public final TimePath<java.time.LocalTime> timeS = createTime("timeS", java.time.LocalTime.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QOptionEntity(String variable) {
        this(OptionEntity.class, forVariable(variable), INITS);
    }

    public QOptionEntity(Path<? extends OptionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOptionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOptionEntity(PathMetadata metadata, PathInits inits) {
        this(OptionEntity.class, metadata, inits);
    }

    public QOptionEntity(Class<? extends OptionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.device = inits.isInitialized("device") ? new QDeviceEntity(forProperty("device")) : null;
        this.sensor = inits.isInitialized("sensor") ? new QSensorEntity(forProperty("sensor")) : null;
    }

}

