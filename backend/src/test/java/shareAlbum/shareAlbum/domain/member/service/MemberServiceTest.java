package shareAlbum.shareAlbum.domain.member.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shareAlbum.shareAlbum.domain.group.repository.MyGroupRepository;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.query.mainPage.AlbumDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MyGroupDto;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static shareAlbum.shareAlbum.domain.album.entity.QAlbum.album;
import static shareAlbum.shareAlbum.domain.group.entity.QGroupList.groupList;
import static shareAlbum.shareAlbum.domain.group.entity.QMyGroup.myGroup;
import static shareAlbum.shareAlbum.domain.member.entity.QMember.member;

@SpringBootTest
@RunWith(SpringRunner.class)
@Rollback(value = false)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MyGroupRepository myGroupRepository;

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void setUp() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void 회원가입중복체크() throws Exception{
        //given
        MemberDto phoneCheck = MemberDto.builder()
                .loginId("01011111111")
                .name("김사자")
                .nickname("ki123")
                .phoneNum("01011111111")
                .password("123456")
                .email("bbbb@bbb.com")
                .build();

        MemberDto emailCheck = MemberDto.builder()
                .loginId("abcd@abcd.com")
                .name("김판다")
                .nickname("ki123")
                .email("abcd@abcd.com")
                .phoneNum("01069343123")
                .password("123456")
                .build();
        MemberDto nicknameCheck = MemberDto.builder()
                .loginId("zzz@abd.com")
                .name("김꿩")
                .nickname("abc1234")
                .password("123456")
                .phoneNum("01055662044")
                .email("zzz@abd.com")
                .build();

        //WHEN
        HashMap<String, String> result1 = memberService.validatePhoneNumAndNickName(phoneCheck.getPhoneNum(), phoneCheck.getNickname());
        HashMap<String, String> result2 = memberService.validateEmailAndNickName(emailCheck.getEmail(), emailCheck.getNickname());
        HashMap<String, String> result3 = memberService.validateEmailAndNickName(nicknameCheck.getEmail(), nicknameCheck.getNickname());

        //THEN
        Assertions.assertThat(result1.get("error")).isEqualTo("핸드폰 번호가 중복되었습니다.");
        Assertions.assertThat(result2.get("error")).isEqualTo("이메일이 중복되었습니다.");
        Assertions.assertThat(result3.get("error")).isEqualTo("닉네임이 중복되었습니다");
    }

    @Test
    public void 회원등록테스트() throws Exception{
        //given
        MemberDto member1 = MemberDto.builder()
                .loginId("01012356788")
                .name("김사자")
                .nickname("ki123")
                .phoneNum("01012356788")
                .password("kim3009!")
                .email("bbbb@bbb.com")
                .build();

        MemberDto member2 = MemberDto.builder()
                .loginId("zzdd@abcd.com")
                .name("김판다")
                .nickname("ki123")
                .email("zzdd@abcd.com")
                .phoneNum("01069343123")
                .password("123456")
                .build();
        MemberDto member3 = MemberDto.builder()
                .loginId("zzz@abd.com")
                .name("김꿩")
                .nickname("kkkk1234")
                .password("123456")
                .phoneNum("01055662044")
                .email("zzz@abd.com")
                .build();

        //WHEN
        memberService.signUp(member1);
        memberService.signUp(member2);
        memberService.signUp(member3);
        List<Member> result = memberRepository.findAll();
        //THEN
        Assertions.assertThat(result.size()).isEqualTo(6);
    }
    @Test
    public void 로그인아이디로그룹조회() throws Exception{
        List<MyGroupDto> myGroupList = queryFactory
                .select(Projections.fields(MyGroupDto.class,
                        groupList.id.as("id"),
                        groupList.groupTitle.as("groupTitle")))
                .from(myGroup)
                .join(myGroup.member,member)
                .join(myGroup.groupList,groupList)
                .where(member.loginId.eq("abc123@gmail.com"))
                .fetch();
        for (MyGroupDto myGroupDto : myGroupList) {
            System.out.println("myGroupDto.toString() = " + myGroupDto.toString());
        }
    }
    @Test
    public void 회원메인페이지정보조회() throws Exception{
        List<MyGroupDto> myGroupList = myGroupRepository.findByMemberId(1L);
        System.out.println("myGroupList.toString() = " + myGroupList.toString());
        List<Long> myGroupIdList = myGroupList.stream().map(o->o.getId()).collect(Collectors.toList());
        System.out.println("myGroupIdList.toString() = " + myGroupIdList.toString());

        List<AlbumDto> myAlbumList = queryFactory
                .select(Projections.constructor(AlbumDto.class,
                        album.id,
                        album.imagePath))
                .from(album)
                .where(album.groupList.id.in(myGroupIdList))
                .fetch();

        Map<Long, List<AlbumDto>> myAlbumMap = myAlbumList.stream()
                .collect(Collectors.groupingBy(albumDto -> albumDto.getId()));

        System.out.println("myAlbumMap.toString() = " + myAlbumMap.toString());
    }






}