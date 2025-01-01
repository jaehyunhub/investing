package shareAlbum.shareAlbum.domain.group.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import shareAlbum.shareAlbum.domain.group.dto.GroupCreateDto;
import shareAlbum.shareAlbum.domain.group.entity.GroupCategory;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.domain.group.repository.GroupRepository;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.entity.MemberStatus;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class GroupServiceImplTest {

    @Autowired GroupService groupService;
    @Autowired GroupRepository groupRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 그룹생성() throws Exception{
        Member member = Member.builder()
                .loginId("fejio@gamil.com")
                .password("12345")
                .name("김재현")
                .phoneNum(Optional.of("01023456666"))
                .email(Optional.of("fejio@gamil.com"))
                .nickname("kim3008m")
                .memberStatus(MemberStatus.ACTIVE)
                .build();
        memberRepository.save(member);
        //given
        GroupCreateDto groupCreateDto = new GroupCreateDto("그룹테스트","fejio@gamil.com" ,GroupCategory.FRIEND);
        System.out.println("member = " + memberRepository.findByLoginId(groupCreateDto.getLoginId()));

        //WHEN
        groupService.createGroup(groupCreateDto);
        List groupList1 = groupRepository.findAll();

        //THEN
        assertThat(groupList1.size()).isEqualTo(1);
    }

}