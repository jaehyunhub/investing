package shareAlbum.shareAlbum.domain.member.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SearchResultsDto {
    String nickname;
    Long id;

    public SearchResultsDto(String nickname,Long id) {
        this.id = id;
        this.nickname = nickname;
    }
}
