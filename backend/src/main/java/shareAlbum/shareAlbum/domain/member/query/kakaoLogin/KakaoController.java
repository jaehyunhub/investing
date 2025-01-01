package shareAlbum.shareAlbum.domain.member.query.kakaoLogin;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shareAlbum.shareAlbum.domain.member.query.kakaoLogin.dto.KakaoUserResponseDto;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    public ResponseEntity<KakaoUserResponseDto> kakaoLogin(@RequestParam("code") String code, HttpSession session, RedirectAttributes redirectAttributes) {
        KakaoUserResponseDto kakaoUserResponseDto = kakaoService.getUserInfo(code);
        System.out.println("kakaoUserResponseDto = " + kakaoUserResponseDto.toString());
        return ResponseEntity.ok().body(kakaoUserResponseDto);
    }

}
