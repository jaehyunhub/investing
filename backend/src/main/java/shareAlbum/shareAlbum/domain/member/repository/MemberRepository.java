package shareAlbum.shareAlbum.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shareAlbum.shareAlbum.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberReposiotryCustom {

    //회원가입시 휴대폰 번호 또는 이메일 주소로 회원가입
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPhoneNum(String phonenum);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByLoginId(String loginId);



}
