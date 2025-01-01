package shareAlbum.shareAlbum.domain.member.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shareAlbum.shareAlbum.global.baseEntity.BaseTimeEntity;
import shareAlbum.shareAlbum.domain.chat.entity.ChatMessage;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseTimeEntity  implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; //PK값

    @Column(name="login_id")
    private String loginId;

    private String name; //이름

    @Column(unique = true)
    private String nickname; //사용자 닉네임
    private String password; //비밀번호

    private String email; // E-mail
    private String birthday; //생년월일

    @Column(name="phone_num")
    private String phoneNum; //핸드폰번호

    private byte[] profile; //프로필 사진

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;//회원탈퇴여부

    @OneToMany(mappedBy = "member")
    private List<MyGroup> myGroupMember = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public Member(String loginId, Optional<String> email, Optional<String> phoneNum, String name, String nickname, String password, MemberStatus memberStatus) {
        this.loginId = loginId;
        this.email = email.orElse(null);
        this.phoneNum = phoneNum.orElse(null);
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.memberStatus = memberStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 기본 권한 설정
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
