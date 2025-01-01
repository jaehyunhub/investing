package shareAlbum.shareAlbum.domain.group.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import shareAlbum.shareAlbum.global.baseEntity.BaseEntity;
import shareAlbum.shareAlbum.global.baseEntity.BaseTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class GroupList extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "groupList_id")
    private Long id;

    @NotBlank(message = "그룹 명을 입력해주세요")
    private String groupTitle;

    @OneToMany(mappedBy = "groupList")
    private List<MyGroup> myGroup = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    //@NotBlank(message = "그룹 분류를 선택해주세요")
    private GroupCategory groupCategory;

    @OneToMany(mappedBy = "groupList")
    private List<Invitation> invitation = new ArrayList<>();


    @Builder
    public GroupList(String groupTitle, GroupCategory groupCategory,String createBy) {
        this.groupTitle = groupTitle;
        this.groupCategory = groupCategory;
        this.setCreateBy(createBy);
    }
}
