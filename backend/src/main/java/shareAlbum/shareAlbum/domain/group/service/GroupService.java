package shareAlbum.shareAlbum.domain.group.service;

import shareAlbum.shareAlbum.domain.group.dto.GroupAcceptionDto;
import shareAlbum.shareAlbum.domain.group.dto.GroupCreateDto;
import shareAlbum.shareAlbum.domain.group.dto.GroupInvitationDto;

public interface GroupService {

    void createGroup(GroupCreateDto groupCreateDto);

    //그룹 초대 보내기
    void inviteGroup(GroupInvitationDto groupInvitation);

    //그룹 초대 승인
    void acceptGroupInvitation(GroupInvitationDto groupInvitationDto);

    void rejectGroupInvitation(GroupInvitationDto groupInvitationDto);

}
