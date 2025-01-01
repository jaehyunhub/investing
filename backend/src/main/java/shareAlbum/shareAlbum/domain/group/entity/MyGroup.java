package shareAlbum.shareAlbum.domain.group.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shareAlbum.shareAlbum.domain.member.entity.Member;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyGroup {
    @Id
    @GeneratedValue
    @Column(name = "myGroup_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "groupList_id")
    private GroupList groupList;


    //초대된 날짜정도?
    @Builder
    public MyGroup(Member member, GroupList groupList) {
        this.member = member;
        this.groupList = groupList;
    }
}
