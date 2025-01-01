package shareAlbum.shareAlbum.domain.group.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyGroup is a Querydsl query type for MyGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMyGroup extends EntityPathBase<MyGroup> {

    private static final long serialVersionUID = -1541785087L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyGroup myGroup = new QMyGroup("myGroup");

    public final QGroupList groupList;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final shareAlbum.shareAlbum.domain.member.entity.QMember member;

    public QMyGroup(String variable) {
        this(MyGroup.class, forVariable(variable), INITS);
    }

    public QMyGroup(Path<? extends MyGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyGroup(PathMetadata metadata, PathInits inits) {
        this(MyGroup.class, metadata, inits);
    }

    public QMyGroup(Class<? extends MyGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupList = inits.isInitialized("groupList") ? new QGroupList(forProperty("groupList")) : null;
        this.member = inits.isInitialized("member") ? new shareAlbum.shareAlbum.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

