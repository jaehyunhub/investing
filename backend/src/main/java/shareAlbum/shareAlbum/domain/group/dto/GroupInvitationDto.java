package shareAlbum.shareAlbum.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import shareAlbum.shareAlbum.domain.group.entity.InvitationStatus;

@NoArgsConstructor
@Getter
@ToString
public class GroupInvitationDto {

    private Long invitationId;
    private Long groupId;
    private String groupTitle;
    private String inviterId;
    private String receiverId;
    private InvitationStatus invitationStatus;
}
