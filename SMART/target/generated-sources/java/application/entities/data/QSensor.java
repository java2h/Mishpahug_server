package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSensor is a Querydsl query type for Sensor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSensor extends EntityPathBase<Sensor> {

    private static final long serialVersionUID = -1824599601L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSensor sensor = new QSensor("sensor");

    public final QArduino arduino;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath macAddress = createString("macAddress");

    public final StringPath name = createString("name");

    public final SetPath<Option, QOption> optionSet = this.<Option, QOption>createSet("optionSet", Option.class, QOption.class, PathInits.DIRECT2);

    public final NumberPath<Integer> pin = createNumber("pin", Integer.class);

    public final QValue value;

    public QSensor(String variable) {
        this(Sensor.class, forVariable(variable), INITS);
    }

    public QSensor(Path<? extends Sensor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSensor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSensor(PathMetadata metadata, PathInits inits) {
        this(Sensor.class, metadata, inits);
    }

    public QSensor(Class<? extends Sensor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.arduino = inits.isInitialized("arduino") ? new QArduino(forProperty("arduino")) : null;
        this.value = inits.isInitialized("value") ? new QValue(forProperty("value"), inits.get("value")) : null;
    }

}

