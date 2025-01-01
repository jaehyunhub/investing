package shareAlbum.shareAlbum.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    //redis회원정보 조회
    @GetMapping("redis/{nickname}")
    public ResponseEntity<MemberInfoDto> auth(@PathVariable("nickname") String nickname){
        try{
            MemberInfoDto memberInfoDto = redisService.findMemberInfoInRedis(nickname);
            System.out.println("========변경된 redisNickname=======");
            System.out.println("memberInfoDto.toString() = " + memberInfoDto.toString());
            System.out.println("=====================");

            return ResponseEntity.ok(memberInfoDto);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
