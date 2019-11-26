package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDevicePositionEntity is a Querydsl query type for DevicePositionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevicePositionEntity extends EntityPathBase<DevicePositionEntity> {

    private static final long serialVersionUID = 1795356599L;

    public static final QDevicePositionEntity devicePositionEntity = new QDevicePositionEntity("devicePositionEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> x = createNumber("x", Integer.class);

    public final NumberPath<Integer> y = createNumber("y", Integer.class);

    public QDevicePositionEntity(String variable) {
        super(DevicePositionEntity.class, forVariable(variable));
    }

    public QDevicePositionEntity(Path<? extends DevicePositionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDevicePositionEntity(PathMetadata metadata) {
        super(DevicePositionEntity.class, metadata);
    }

}

