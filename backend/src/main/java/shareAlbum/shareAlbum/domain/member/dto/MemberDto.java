package shareAlbum.shareAlbum.domain.member.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Getter
@ToString
@NoArgsConstructor
public class MemberDto {

    @NotBlank(message = "이메일 혹은 휴대폰번호를 입력해주세요")
    private String loginId;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "사용하실 닉네임을 입력해주세요")
    @Size(min=2, message="닉네임이 너무 짧습니다.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{1,10}$",
            message = "비밀번호는 1~10 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    private String email;
    private String phoneNum;

    public void checkEmailOrPhone(String loginId) {
        if (loginId.contains("@") && loginId.contains(".")) {
            this.email = loginId;
        } else {
            String replacedId = loginId.replace("-", "");
            this.phoneNum = replacedId;
            this.loginId = replacedId;
        }
    }

    @Builder
    public MemberDto(String loginId, String name, String nickname, String password,String phoneNum, String email) {
        this.loginId = loginId;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }
}
