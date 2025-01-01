package shareAlbum.shareAlbum.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -283968389L;

    public static final QMember member = new QMember("member1");

    public final shareAlbum.shareAlbum.global.baseEntity.QBaseTimeEntity _super = new shareAlbum.shareAlbum.global.baseEntity.QBaseTimeEntity(this);

    public final StringPath birthday = createString("birthday");

    public final ListPath<shareAlbum.shareAlbum.domain.chat.entity.ChatMessage, shareAlbum.shareAlbum.domain.chat.entity.QChatMessage> chatMessages = this.<shareAlbum.shareAlbum.domain.chat.entity.ChatMessage, shareAlbum.shareAlbum.domain.chat.entity.QChatMessage>createList("chatMessages", shareAlbum.shareAlbum.domain.chat.entity.ChatMessage.class, shareAlbum.shareAlbum.domain.chat.entity.QChatMessage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath loginId = createString("loginId");

    public final EnumPath<MemberStatus> memberStatus = createEnum("memberStatus", MemberStatus.class);

    public final ListPath<shareAlbum.shareAlbum.domain.group.entity.MyGroup, shareAlbum.shareAlbum.domain.group.entity.QMyGroup> myGroupMember = this.<shareAlbum.shareAlbum.domain.group.entity.MyGroup, shareAlbum.shareAlbum.domain.group.entity.QMyGroup>createList("myGroupMember", shareAlbum.shareAlbum.domain.group.entity.MyGroup.class, shareAlbum.shareAlbum.domain.group.entity.QMyGroup.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNum = createString("phoneNum");

    public final ArrayPath<byte[], Byte> profile = createArray("profile", byte[].class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

