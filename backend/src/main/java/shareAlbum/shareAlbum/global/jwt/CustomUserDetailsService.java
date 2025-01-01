package shareAlbum.shareAlbum.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member =  memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
        return  new org.springframework.security.core.userdetails.User(
                member.getLoginId(),
                member.getPassword(),
                member.getAuthorities()
        );
    }
}
