package shareAlbum.shareAlbum.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import shareAlbum.shareAlbum.domain.album.entity.Album;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MyGroupDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public MemberInfoDto findMemberInfoInRedis(String nickname) {
        MemberInfoDto memberInfoDto = (MemberInfoDto) redisTemplate.opsForValue().get(nickname);
        return memberInfoDto;
    }

    @Override
    public void addNewGroupToRedis(String nickname, GroupList groupList) {
        MemberInfoDto memberInfo = (MemberInfoDto) redisTemplate.opsForValue().get(nickname);

        if (memberInfo == null) {
            throw new RuntimeException("Redis에 저장된 회원 정보가 없습니다.");
        }
        System.out.println("====redis변경전======" );
        System.out.println("memberInfo = " + memberInfo.toString());
        System.out.println("====redis변전======" );


        System.out.println("====redis변경후======" );
        List<MyGroupDto> myGroupList = memberInfo.getMyGroupList();
        MyGroupDto newMyGroupDto = new MyGroupDto(groupList.getId(), groupList.getGroupTitle());
        myGroupList.add(newMyGroupDto);
        redisTemplate.opsForValue().set(nickname,memberInfo);

        System.out.println("memberInfo = " + memberInfo.toString());
        System.out.println("====redis변경후======" );

    }
    @Override
    public void addAlbumToRedis(String nickname, Album album) {

    }

    @Override
    public void acceptInvitationToRedis(String nickname, MyGroup myGroup) {

    }
}
