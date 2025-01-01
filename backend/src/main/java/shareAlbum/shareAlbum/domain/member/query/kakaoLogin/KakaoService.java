package shareAlbum.shareAlbum.domain.member.query.kakaoLogin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import shareAlbum.shareAlbum.domain.member.query.kakaoLogin.dto.KakaoTokenResponseDto;
import shareAlbum.shareAlbum.domain.member.query.kakaoLogin.dto.KakaoUserResponseDto;

@Service
public class KakaoService {

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoApiClient kakaoApiClient;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    public KakaoService(KakaoAuthClient kakaoAuthClient, KakaoApiClient kakaoApiClient) {
        this.kakaoAuthClient = kakaoAuthClient;
        this.kakaoApiClient = kakaoApiClient;
    }


    public KakaoUserResponseDto getUserInfo(String code) {
        KakaoTokenResponseDto tokenResponse = kakaoAuthClient.getToken(
                "authorization_code",
                clientId,
                redirectUri,
                code,
                clientSecret
        );

        return kakaoApiClient.getUserInfo("Bearer " + tokenResponse.getAccessToken());
    }
}