package shareAlbum.shareAlbum.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.dto.MemberLoginDto;
import shareAlbum.shareAlbum.domain.member.dto.MemberSearchResultDto;
import shareAlbum.shareAlbum.domain.member.dto.SearchResultsDto;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;
import shareAlbum.shareAlbum.domain.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signUp(@Valid @RequestBody MemberDto memberDto, BindingResult result) {
        try {
            HashMap<String, String> check = memberService.vaildateSignUp(result, memberDto);
            if (check.containsKey("success")) {
                memberService.signUp(memberDto);
                return ResponseEntity.ok().build();
            }
            //실패한 경우
            return ResponseEntity.badRequest().body(check);

        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<MemberInfoDto> login(@RequestBody @Valid MemberLoginDto memberLoginDto, BindingResult result
                                                ,HttpServletResponse response) {
        //아이디 정보 체크
        try {
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            MemberInfoDto memberInfoDto = memberService.logIn(memberLoginDto,response);
            //회원정보가 있는지 한번 더 체크
            if (memberInfoDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            System.out.println("memberInfoDto = " + memberInfoDto);
            return ResponseEntity.ok().body(memberInfoDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    //logout
    @PostMapping("/logout/{nickname}")
    public ResponseEntity<String> logout(@PathVariable("nickname") String nickname,HttpServletResponse response){
        try{
            System.out.println("nickname = " + nickname);
            String check = memberService.logout(nickname,response);
            System.out.println("check = " + check);
            if(check =="Success"){
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        
    }

    @GetMapping("/auth/{nickname}")
    public ResponseEntity<MemberInfoDto> auth(@PathVariable("nickname") String nickname){
        try{
            MemberInfoDto memberInfoDto = memberService.authNickName(nickname);
            return ResponseEntity.ok(memberInfoDto);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/search/{nickname}")
    public ResponseEntity<List<SearchResultsDto>> searchMembers(@PathVariable("nickname") String nickname,
                                                                @RequestParam String data){
        try{
            System.out.println("======================");
            System.out.println("nickname = " + nickname);
            System.out.println(" = " + data);
            System.out.println("======================");
            List<SearchResultsDto> memberSearchResultDto = memberService.searchAllNickname(nickname,data);
            return ResponseEntity.ok().body(memberSearchResultDto);
        }catch (NoSuchElementException e){
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/searchResults/{nickname}")
    public ResponseEntity<MemberInfoDto> searchResultsMemberInfo(@PathVariable("nickname") String nickname){
        try{
            Member searchResultMember = memberRepository.findByNickname(nickname).orElseThrow(()-> new NoSuchElementException(nickname+"의 회원정보가 없습니다"));
            MemberInfoDto memberSearchResultDto = memberRepository.searchMemberAllInfo(searchResultMember);
            return ResponseEntity.ok().body(memberSearchResultDto);
        }catch (NoSuchElementException e){
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
