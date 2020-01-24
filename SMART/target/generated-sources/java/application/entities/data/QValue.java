package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QValue is a Querydsl query type for Value
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QValue extends EntityPathBase<Value> {

    private static final long serialVersionUID = -1857323844L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QValue value = new QValue("value1");

    public final StringPath data = createString("data");

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QSensor sensor;

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public QValue(String variable) {
        this(Value.class, forVariable(variable), INITS);
    }

    public QValue(Path<? extends Value> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QValue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QValue(PathMetadata metadata, PathInits inits) {
        this(Value.class, metadata, inits);
    }

    public QValue(Class<? extends Value> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sensor = inits.isInitialized("sensor") ? new QSensor(forProperty("sensor"), inits.get("sensor")) : null;
    }

}

