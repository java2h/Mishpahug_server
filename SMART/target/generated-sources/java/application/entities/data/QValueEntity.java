package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QValueEntity is a Querydsl query type for ValueEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QValueEntity extends EntityPathBase<ValueEntity> {

    private static final long serialVersionUID = 114610111L;

    public static final QValueEntity valueEntity = new QValueEntity("valueEntity");

    public final StringPath data = createString("data");

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public QValueEntity(String variable) {
        super(ValueEntity.class, forVariable(variable));
    }

    public QValueEntity(Path<? extends ValueEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QValueEntity(PathMetadata metadata) {
        super(ValueEntity.class, metadata);
    }

}

