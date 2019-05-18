package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCityEntity is a Querydsl query type for CityEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCityEntity extends EntityPathBase<CityEntity> {

    private static final long serialVersionUID = -735629315L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCityEntity cityEntity = new QCityEntity("cityEntity");

    public final SetPath<AddressEntity, QAddressEntity> addressEntities = this.<AddressEntity, QAddressEntity>createSet("addressEntities", AddressEntity.class, QAddressEntity.class, PathInits.DIRECT2);

    public final QCountryEntity countryEntity;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QCityEntity(String variable) {
        this(CityEntity.class, forVariable(variable), INITS);
    }

    public QCityEntity(Path<? extends CityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCityEntity(PathMetadata metadata, PathInits inits) {
        this(CityEntity.class, metadata, inits);
    }

    public QCityEntity(Class<? extends CityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.countryEntity = inits.isInitialized("countryEntity") ? new QCountryEntity(forProperty("countryEntity")) : null;
    }

}

