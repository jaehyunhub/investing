package shareAlbum.shareAlbum.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class JwtToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
