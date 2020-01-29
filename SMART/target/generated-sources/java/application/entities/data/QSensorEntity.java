package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSensorEntity is a Querydsl query type for SensorEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSensorEntity extends EntityPathBase<SensorEntity> {

    private static final long serialVersionUID = -448083310L;

    public static final QSensorEntity sensorEntity = new QSensorEntity("sensorEntity");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath ipaddress = createString("ipaddress");

    public final StringPath macAddress = createString("macAddress");

    public final StringPath nameSensor = createString("nameSensor");

    public final NumberPath<Integer> pin = createNumber("pin", Integer.class);

    public final NumberPath<Float> value = createNumber("value", Float.class);

    public QSensorEntity(String variable) {
        super(SensorEntity.class, forVariable(variable));
    }

    public QSensorEntity(Path<? extends SensorEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSensorEntity(PathMetadata metadata) {
        super(SensorEntity.class, metadata);
    }

}

