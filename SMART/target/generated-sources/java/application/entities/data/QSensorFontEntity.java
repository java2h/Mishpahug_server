package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSensorFontEntity is a Querydsl query type for SensorFontEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSensorFontEntity extends EntityPathBase<SensorFontEntity> {

    private static final long serialVersionUID = 1701977153L;

    public static final QSensorFontEntity sensorFontEntity = new QSensorFontEntity("sensorFontEntity");

    public final StringPath color = createString("color");

    public final StringPath font = createString("font");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public QSensorFontEntity(String variable) {
        super(SensorFontEntity.class, forVariable(variable));
    }

    public QSensorFontEntity(Path<? extends SensorFontEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSensorFontEntity(PathMetadata metadata) {
        super(SensorFontEntity.class, metadata);
    }

}

