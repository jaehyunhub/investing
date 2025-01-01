package shareAlbum.shareAlbum.domain.member.query.kakaoLogin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import shareAlbum.shareAlbum.domain.member.query.kakaoLogin.dto.KakaoUserResponseDto;

@FeignClient(name = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface KakaoApiClient {
    @GetMapping("/v2/user/me")
    KakaoUserResponseDto getUserInfo(@RequestHeader("Authorization") String accessToken);
}