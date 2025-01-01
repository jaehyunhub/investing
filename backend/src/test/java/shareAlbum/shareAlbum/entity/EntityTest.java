package shareAlbum.shareAlbum.entity;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
//@Rollback(value = false)
public class EntityTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void saveMember() {
//        Member member1 = new Member("김말숙", "mal11", "aaa@gmail.com", "aaa111",
//                "2000-01-01", "010-1111-2222");
//        Member member2 = new Member("김숙말", "kim99", "bbb@gmail.com", "aaa222",
//                "2002-01-02", "010-3333-4444");
//        Member member3 = new Member("김캬캬", "effe99", "ccc@gmail.com", "aaa333",
//                "2003-01-07", "010-5555-6666");
//        Member member4 = new Member("김쿄쿄", "grgr99", "ddd@gmail.com", "aaa444",
//                "2004-01-08", "010-7777-8888");
//
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//        memberRepository.save(member3);
//        memberRepository.save(member4);
//
//        memberRepository.findAll().stream()
//                .map(member -> "member = " + member)
//                .forEach(System.out::println);
//
//        long memberCount = memberRepository.count();
//        Assertions.assertThat(memberCount).isEqualTo(4);
    }
}
