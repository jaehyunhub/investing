package shareAlbum.shareAlbum.domain.group.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shareAlbum.shareAlbum.domain.group.entity.InvitationStatus;

@NoArgsConstructor
@Getter
public class GroupAcceptionDto {

    private Long groupId;
    private Long invitationId;
    private String receiverId;
    private InvitationStatus invitationStatus;

}
