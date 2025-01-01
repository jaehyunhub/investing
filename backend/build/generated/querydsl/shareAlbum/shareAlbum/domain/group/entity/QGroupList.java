package shareAlbum.shareAlbum.domain.group.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupList is a Querydsl query type for GroupList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGroupList extends EntityPathBase<GroupList> {

    private static final long serialVersionUID = -20701685L;

    public static final QGroupList groupList = new QGroupList("groupList");

    public final shareAlbum.shareAlbum.global.baseEntity.QBaseEntity _super = new shareAlbum.shareAlbum.global.baseEntity.QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final EnumPath<GroupCategory> groupCategory = createEnum("groupCategory", GroupCategory.class);

    public final StringPath groupTitle = createString("groupTitle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Invitation, QInvitation> invitation = this.<Invitation, QInvitation>createList("invitation", Invitation.class, QInvitation.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final StringPath lastModifyBy = _super.lastModifyBy;

    public final ListPath<MyGroup, QMyGroup> myGroup = this.<MyGroup, QMyGroup>createList("myGroup", MyGroup.class, QMyGroup.class, PathInits.DIRECT2);

    public QGroupList(String variable) {
        super(GroupList.class, forVariable(variable));
    }

    public QGroupList(Path<? extends GroupList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroupList(PathMetadata metadata) {
        super(GroupList.class, metadata);
    }

}

