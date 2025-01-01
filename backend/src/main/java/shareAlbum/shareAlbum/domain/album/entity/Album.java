package shareAlbum.shareAlbum.domain.album.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.global.baseEntity.BaseEntity;
import shareAlbum.shareAlbum.global.baseEntity.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Album extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "album_id")
    private Long id;

    private byte[] pictures;
    private String content;
    private String imagePath;

//    @Enumerated(EnumType.STRING)
//    private AlbumStatus albumStatus;

    @OneToOne
    @JoinColumn(name = "groupList_id")
    private GroupList groupList;

    @OneToMany(mappedBy = "album")
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    private List<MyLike> like = new ArrayList<>();

    @Builder
    public Album(String content, String imagePath, GroupList groupList,String createBy) {
        this.content = content;
        this.imagePath = imagePath;
        this.groupList = groupList;
        setCreateBy(createBy);

    }
}
