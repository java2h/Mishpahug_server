package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceTypeEntity is a Querydsl query type for DeviceTypeEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceTypeEntity extends EntityPathBase<DeviceTypeEntity> {

    private static final long serialVersionUID = -591742328L;

    public static final QDeviceTypeEntity deviceTypeEntity = new QDeviceTypeEntity("deviceTypeEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QDeviceTypeEntity(String variable) {
        super(DeviceTypeEntity.class, forVariable(variable));
    }

    public QDeviceTypeEntity(Path<? extends DeviceTypeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceTypeEntity(PathMetadata metadata) {
        super(DeviceTypeEntity.class, metadata);
    }

}

