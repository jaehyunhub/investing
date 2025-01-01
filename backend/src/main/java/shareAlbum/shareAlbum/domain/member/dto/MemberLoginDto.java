package shareAlbum.shareAlbum.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@NoArgsConstructor
public class MemberLoginDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public MemberLoginDto(String loginId, String password) {
        if (loginId.contains("-")) {this.loginId = loginId.replace("-","");}
        this.password = password;
    }
}
