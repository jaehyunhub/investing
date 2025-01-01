package shareAlbum.shareAlbum.domain.album.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyLike is a Querydsl query type for MyLike
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMyLike extends EntityPathBase<MyLike> {

    private static final long serialVersionUID = -1002055739L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyLike myLike = new QMyLike("myLike");

    public final QAlbum album;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath writer = createString("writer");

    public QMyLike(String variable) {
        this(MyLike.class, forVariable(variable), INITS);
    }

    public QMyLike(Path<? extends MyLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyLike(PathMetadata metadata, PathInits inits) {
        this(MyLike.class, metadata, inits);
    }

    public QMyLike(Class<? extends MyLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.album = inits.isInitialized("album") ? new QAlbum(forProperty("album"), inits.get("album")) : null;
    }

}

