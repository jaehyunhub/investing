package shareAlbum.shareAlbum.domain.group.entity;

import lombok.*;
import shareAlbum.shareAlbum.domain.member.entity.Member;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invitation {

    @Id
    @GeneratedValue
    @Column(name = "invitation_id")
    private Long id;

    private String inviterId;
    private String receiverId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private GroupList groupList;

    @Enumerated(EnumType.STRING)
    private InvitationStatus invitation_status;

    @Builder
    public Invitation(String inviterId, String receiverId, GroupList groupList, InvitationStatus invitation_status) {
        this.inviterId = inviterId;
        this.receiverId = receiverId;
        this.groupList = groupList;
        this.invitation_status = invitation_status;
    }

    public void changeStatus(InvitationStatus invitation_status) {
        this.invitation_status = invitation_status;
    }
}
