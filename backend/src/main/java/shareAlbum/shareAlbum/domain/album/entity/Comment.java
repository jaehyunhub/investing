package shareAlbum.shareAlbum.domain.album.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String writer;
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name ="album_id")
    private Album album;

}
