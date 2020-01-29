package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOptionEntity is a Querydsl query type for OptionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOptionEntity extends EntityPathBase<OptionEntity> {

    private static final long serialVersionUID = 1204504333L;

    public static final QOptionEntity optionEntity = new QOptionEntity("optionEntity");

    public final DatePath<java.time.LocalDate> dateS = createDate("dateS", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final StringPath device = createString("device");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> ifType = createNumber("ifType", Integer.class);

    public final StringPath nameOption = createString("nameOption");

    public final StringPath sensor = createString("sensor");

    public final TimePath<java.time.LocalTime> timeS = createTime("timeS", java.time.LocalTime.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QOptionEntity(String variable) {
        super(OptionEntity.class, forVariable(variable));
    }

    public QOptionEntity(Path<? extends OptionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOptionEntity(PathMetadata metadata) {
        super(OptionEntity.class, metadata);
    }

}

