package shareAlbum.shareAlbum.domain.group.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvitation is a Querydsl query type for Invitation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInvitation extends EntityPathBase<Invitation> {

    private static final long serialVersionUID = -711039221L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvitation invitation = new QInvitation("invitation");

    public final QGroupList groupList;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<InvitationStatus> invitation_status = createEnum("invitation_status", InvitationStatus.class);

    public final StringPath inviterId = createString("inviterId");

    public final StringPath receiverId = createString("receiverId");

    public QInvitation(String variable) {
        this(Invitation.class, forVariable(variable), INITS);
    }

    public QInvitation(Path<? extends Invitation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvitation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvitation(PathMetadata metadata, PathInits inits) {
        this(Invitation.class, metadata, inits);
    }

    public QInvitation(Class<? extends Invitation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupList = inits.isInitialized("groupList") ? new QGroupList(forProperty("groupList")) : null;
    }

}

