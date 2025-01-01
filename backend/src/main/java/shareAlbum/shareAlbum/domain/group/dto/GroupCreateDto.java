package shareAlbum.shareAlbum.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shareAlbum.shareAlbum.domain.group.entity.GroupCategory;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GroupCreateDto {


    private String loginId;
    @NotBlank(message = "그룹 명을 입력해주세요")
    private String groupTitle;
    private GroupCategory groupCategory;





}
