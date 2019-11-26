package application.entities.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiscountConst is a Querydsl query type for DiscountConst
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiscountConst extends EntityPathBase<DiscountConst> {

    private static final long serialVersionUID = 1159226093L;

    public static final QDiscountConst discountConst = new QDiscountConst("discountConst");

    public final ArrayPath<application.entities.embeded.Discount[], application.entities.embeded.Discount> discounts = createArray("discounts", application.entities.embeded.Discount[].class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDiscountConst(String variable) {
        super(DiscountConst.class, forVariable(variable));
    }

    public QDiscountConst(Path<? extends DiscountConst> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiscountConst(PathMetadata metadata) {
        super(DiscountConst.class, metadata);
    }

}

