package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSensorEntity is a Querydsl query type for SensorEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSensorEntity extends EntityPathBase<SensorEntity> {

    private static final long serialVersionUID = -448083310L;

    public static final QSensorEntity sensorEntity = new QSensorEntity("sensorEntity");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SimplePath<java.net.InetAddress> ipaddress = createSimple("ipaddress", java.net.InetAddress.class);

    public final StringPath macAddress = createString("macAddress");

    public final StringPath nameSensor = createString("nameSensor");

    public final SetPath<OptionEntity, QOptionEntity> optionEntities = this.<OptionEntity, QOptionEntity>createSet("optionEntities", OptionEntity.class, QOptionEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> pin = createNumber("pin", Integer.class);

    public final ListPath<ValueEntity, QValueEntity> values = this.<ValueEntity, QValueEntity>createList("values", ValueEntity.class, QValueEntity.class, PathInits.DIRECT2);

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

