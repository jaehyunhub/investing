package shareAlbum.shareAlbum.domain.member.repository;

import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberReposiotryCustom {
    //로그인 후 멤버 정보전체 조회
    MemberInfoDto searchMemberAllInfo(Member member);

    Optional<List<Member>> findAllMembersByNickName(String nickname);

}
