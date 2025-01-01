package shareAlbum.shareAlbum.domain.member.query.kakaoLogin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shareAlbum.shareAlbum.domain.member.query.kakaoLogin.dto.KakaoTokenResponseDto;

@FeignClient(name = "kakaoAuthClient", url = "https://kauth.kakao.com")
public interface KakaoAuthClient {
    @PostMapping("/oauth/token")
    KakaoTokenResponseDto getToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String code,
            @RequestParam("client_secret") String clientSecret
    );

}