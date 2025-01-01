package shareAlbum.shareAlbum.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shareAlbum.shareAlbum.domain.member.entity.Member;

import java.util.List;


@Getter
@ToString
public class MemberSearchResultDto{
    List<SearchResultsDto> members;

    public MemberSearchResultDto(List<SearchResultsDto> members) {
        this.members = members;
    }
}


