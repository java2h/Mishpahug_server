package application.entities.embeded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiscount is a Querydsl query type for Discount
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QDiscount extends BeanPath<Discount> {

    private static final long serialVersionUID = -2085779258L;

    public static final QDiscount discount = new QDiscount("discount");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Float> percent = createNumber("percent", Float.class);

    public QDiscount(String variable) {
        super(Discount.class, forVariable(variable));
    }

    public QDiscount(Path<? extends Discount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiscount(PathMetadata metadata) {
        super(Discount.class, metadata);
    }

}

