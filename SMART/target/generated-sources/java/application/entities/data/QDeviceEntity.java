package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeviceEntity is a Querydsl query type for DeviceEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceEntity extends EntityPathBase<DeviceEntity> {

    private static final long serialVersionUID = -1153290194L;

    public static final QDeviceEntity deviceEntity = new QDeviceEntity("deviceEntity");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SimplePath<java.net.InetAddress> ipaddress = createSimple("ipaddress", java.net.InetAddress.class);

    public final StringPath nameDevice = createString("nameDevice");

    public final SetPath<OptionEntity, QOptionEntity> optionEntities = this.<OptionEntity, QOptionEntity>createSet("optionEntities", OptionEntity.class, QOptionEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> pin = createNumber("pin", Integer.class);

    public QDeviceEntity(String variable) {
        super(DeviceEntity.class, forVariable(variable));
    }

    public QDeviceEntity(Path<? extends DeviceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceEntity(PathMetadata metadata) {
        super(DeviceEntity.class, metadata);
    }

}

