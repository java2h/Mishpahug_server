package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSensorPositionEntity is a Querydsl query type for SensorPositionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSensorPositionEntity extends EntityPathBase<SensorPositionEntity> {

    private static final long serialVersionUID = 579661339L;

    public static final QSensorPositionEntity sensorPositionEntity = new QSensorPositionEntity("sensorPositionEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> x = createNumber("x", Integer.class);

    public final NumberPath<Integer> y = createNumber("y", Integer.class);

    public QSensorPositionEntity(String variable) {
        super(SensorPositionEntity.class, forVariable(variable));
    }

    public QSensorPositionEntity(Path<? extends SensorPositionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSensorPositionEntity(PathMetadata metadata) {
        super(SensorPositionEntity.class, metadata);
    }

}

