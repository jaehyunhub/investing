package shareAlbum.shareAlbum.domain.member.query.mainPage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class MyGroupDto {
    private Long id;
    private String groupTitle;
    private Long groupId;

    public MyGroupDto(Long id, String groupTitle) {
        this.id = id;
        this.groupTitle = groupTitle;
    }

    public MyGroupDto(Long id, String groupTitle, Long groupId) {
        this.id = id;
        this.groupTitle = groupTitle;
        this.groupId = groupId;
    }
}
