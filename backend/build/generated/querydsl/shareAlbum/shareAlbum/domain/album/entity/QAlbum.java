package shareAlbum.shareAlbum.domain.album.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlbum is a Querydsl query type for Album
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAlbum extends EntityPathBase<Album> {

    private static final long serialVersionUID = 2034437581L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlbum album = new QAlbum("album");

    public final shareAlbum.shareAlbum.global.baseEntity.QBaseEntity _super = new shareAlbum.shareAlbum.global.baseEntity.QBaseEntity(this);

    public final ListPath<Comment, QComment> comment = this.<Comment, QComment>createList("comment", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final shareAlbum.shareAlbum.domain.group.entity.QGroupList groupList;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final StringPath lastModifyBy = _super.lastModifyBy;

    public final ListPath<MyLike, QMyLike> like = this.<MyLike, QMyLike>createList("like", MyLike.class, QMyLike.class, PathInits.DIRECT2);

    public final ArrayPath<byte[], Byte> pictures = createArray("pictures", byte[].class);

    public QAlbum(String variable) {
        this(Album.class, forVariable(variable), INITS);
    }

    public QAlbum(Path<? extends Album> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlbum(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlbum(PathMetadata metadata, PathInits inits) {
        this(Album.class, metadata, inits);
    }

    public QAlbum(Class<? extends Album> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupList = inits.isInitialized("groupList") ? new shareAlbum.shareAlbum.domain.group.entity.QGroupList(forProperty("groupList")) : null;
    }

}

