package shareAlbum.shareAlbum.global.redis;

import shareAlbum.shareAlbum.domain.album.entity.Album;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;

public interface RedisService {

    //전체조회
    MemberInfoDto findMemberInfoInRedis(String nickname);

    //그룹추가
    void addNewGroupToRedis(String nickname, GroupList groupList);
    //앨범추가
    void addAlbumToRedis(String nickname,Album album);
    //그룹초대시
    void acceptInvitationToRedis(String nickname,MyGroup myGroup);
    //

}
