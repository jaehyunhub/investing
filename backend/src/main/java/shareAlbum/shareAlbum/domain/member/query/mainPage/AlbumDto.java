package shareAlbum.shareAlbum.domain.member.query.mainPage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class AlbumDto {
    private Long id;
    private String imagePath;
    private Long groupListId;
    private String content;
    private Long commentCount;
    private Long myLikeCount;


}
