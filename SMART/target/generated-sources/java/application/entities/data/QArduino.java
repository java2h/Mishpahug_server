package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArduino is a Querydsl query type for Arduino
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArduino extends EntityPathBase<Arduino> {

    private static final long serialVersionUID = 839787699L;

    public static final QArduino arduino = new QArduino("arduino");

    public final StringPath description = createString("description");

    public final SetPath<Device, QDevice> deviceSet = this.<Device, QDevice>createSet("deviceSet", Device.class, QDevice.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath ipaddress = createString("ipaddress");

    public final SetPath<Sensor, QSensor> sensorSet = this.<Sensor, QSensor>createSet("sensorSet", Sensor.class, QSensor.class, PathInits.DIRECT2);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QArduino(String variable) {
        super(Arduino.class, forVariable(variable));
    }

    public QArduino(Path<? extends Arduino> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArduino(PathMetadata metadata) {
        super(Arduino.class, metadata);
    }

}

