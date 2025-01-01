package shareAlbum.shareAlbum.domain.member.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import shareAlbum.shareAlbum.domain.group.dto.GroupCreateDto;
import shareAlbum.shareAlbum.domain.group.entity.GroupCategory;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.domain.group.service.GroupService;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.dto.MemberLoginDto;
import shareAlbum.shareAlbum.domain.member.dto.MemberSearchResultDto;
import shareAlbum.shareAlbum.domain.member.dto.SearchResultsDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.entity.MemberStatus;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;
import shareAlbum.shareAlbum.global.jwt.JwtToken;
import shareAlbum.shareAlbum.global.jwt.JwtTokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final GroupService groupService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public HashMap<String,String> vaildateSignUp(BindingResult result,MemberDto memberDto) {
        HashMap<String, String> check = new HashMap<>();

        //입력 값이 잘못되었으면
        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                check.put(fieldName, errorMessage);
            }
            return check;
        //입력 값은 제대로 들어왔으면
        }else{
            //로그인 아이디가 이메일인지 핸드폰번호인지 체크
            memberDto.checkEmailOrPhone(memberDto.getLoginId());
            String phoneNum = memberDto.getPhoneNum();
            String nickname = memberDto.getNickname();
            System.out.println("memberDto = " + memberDto);

            if (phoneNum != null) {
                HashMap<String, String> phoneAndNicknameCheck = validatePhoneNumAndNickName(phoneNum, nickname);
                if (phoneAndNicknameCheck.containsKey("error")) {
                    return phoneAndNicknameCheck;
                }
            }else{
                String email = memberDto.getEmail();
                if (email != null) {
                    HashMap<String, String> emailAndNicknameCheck = validateEmailAndNickName(email, nickname);
                    if (emailAndNicknameCheck.containsKey("error")) {
                        return emailAndNicknameCheck;
                    }
                }
            }
            HashMap<String, String> success = new HashMap<>();
            success.put("success", "success");
            return success;
        }
    }

    @Override
    public HashMap<String, String> validateEmailAndNickName(String email,String nickName) {
        HashMap<String, String> result = new HashMap<>();
        Optional<Member> emailValidateCheck = memberRepository.findByEmail(email);
        Optional<Member> nickNameValidateCheck = memberRepository.findByNickname(nickName);
        if(emailValidateCheck.isPresent()){
            System.out.println("memberRepository = " + memberRepository.findByEmail(email));
            result.put("error", "이메일이 중복되었습니다.");
            return result;
        }else{
            if (nickNameValidateCheck.isPresent()) {
                System.out.println("memberRepository = " + memberRepository.findByNickname(nickName));
                result.put("error","닉네임이 중복되었습니다");
                return result;
            }
        }
        return result;
    }

    @Override
    public HashMap<String, String> validatePhoneNumAndNickName(String phoneNum, String nickName) {
        HashMap<String, String> result = new HashMap<>();
        Optional<Member> phoneValidateCheck = memberRepository.findByPhoneNum(phoneNum);
        Optional<Member> nickNameValidateCheck = memberRepository.findByNickname(nickName);

        if(phoneValidateCheck.isPresent()){
            System.out.println("memberRepository = " + memberRepository.findByPhoneNum(phoneNum));
            result.put("error", "핸드폰 번호가 중복되었습니다.");
            return result;
        }else{
            if (nickNameValidateCheck.isPresent()) {
                result.put("error","닉네임이 중복되었습니다");
                return result;

            }
        }
        return result;
    }

    @Override
    @Transactional
    public void signUp(MemberDto memberDto) throws Exception {
        try {
            Member member = Member.builder()
                    .loginId(memberDto.getLoginId())
                    .email(Optional.ofNullable(memberDto.getEmail()))
                    .phoneNum(Optional.ofNullable(memberDto.getPhoneNum()))
                    .name(memberDto.getName())
                    .nickname(memberDto.getNickname())
                    .password(passwordEncoder.encode(memberDto.getPassword()))
                    .memberStatus(MemberStatus.ACTIVE)
                    .build();
            memberRepository.save(member);
            groupService.createGroup(new GroupCreateDto(memberDto.getLoginId(), "Main",GroupCategory.DEFAULT));
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public MemberInfoDto logIn(MemberLoginDto memberLoginDto, HttpServletResponse response) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberLoginDto.getLoginId(), memberLoginDto.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
            Member member = memberRepository.findByLoginId(memberLoginDto.getLoginId()).orElse(null);
            MemberInfoDto memberInfoDto = memberRepository.searchMemberAllInfo(member);

            //JWT 토큰을 쿠키에 저장
            Cookie accessTokenCookie = new Cookie("accessToken", jwtToken.getAccessToken());
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setPath("/");
            accessTokenCookie.setMaxAge((int) jwtTokenProvider.getAccessTokenValidityInMilliseconds() / 1000); // 초 단위로 설정
            response.addCookie(accessTokenCookie);

            Cookie refreshTokenCookie = new Cookie("refreshToken", jwtToken.getRefreshToken());
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge((int) jwtTokenProvider.getRefreshTokenValidityInMilliseconds() / 1000); // 10분
            response.addCookie(refreshTokenCookie);


            String redisKey = memberInfoDto.getNickname();
            redisTemplate.opsForValue().set(redisKey,memberInfoDto);
            System.out.println("redisTemplate.opsForValue().get(redisKey) = " + redisTemplate.opsForValue().get(redisKey));
            return memberInfoDto;

        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", memberLoginDto.getLoginId(), e);
            throw new IllegalStateException("자격 증명에 실패하였습니다", e);
        }


    }

    @Override
    public String logout(String nickname,HttpServletResponse response) {
        try{
            // Access Token 쿠키 삭제
            Cookie accessTokenCookie = new Cookie("accessToken", null);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setPath("/");
            accessTokenCookie.setMaxAge(0); // 쿠키를 즉시 삭제
            response.addCookie(accessTokenCookie);

            // Refresh Token 쿠키 삭제
            Cookie refreshTokenCookie = new Cookie("refreshToken", null);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge(0);
            response.addCookie(refreshTokenCookie);


            String redisKey = nickname;
            redisTemplate.delete(redisKey);
            // 삭제 확인
            Object value = redisTemplate.opsForValue().get(redisKey);
            if (value == null) {
                System.out.println("Redis key 삭제 성공: " + redisKey);
            } else {
                System.out.println("Redis key 삭제 실패: " + redisKey + ", 값: " + value);
            }
            return "Success";
        }catch (Exception e){
            e.printStackTrace();
            return "False";
        }
    }

    @Override
    public MemberInfoDto authNickName(String nickname) {
        Member member = memberRepository.findByNickname(nickname).orElseThrow();
        MemberInfoDto memberInfoDto = memberRepository.searchMemberAllInfo(member);
        String redisKey = "memberInfo" + member.getNickname();
        redisTemplate.delete(redisKey);
        redisTemplate.opsForValue().set(redisKey,memberInfoDto);
        return memberInfoDto;
    }

    @Override
    public List<SearchResultsDto> searchAllNickname(String nickname,String data) {
        Optional<List<Member>> optionalMembers = memberRepository.findAllMembersByNickName(nickname);
        List<Member> members = optionalMembers.orElseThrow(() -> new NoSuchElementException("No members found with nickname: " + nickname));
        List<SearchResultsDto> searchResults = members.stream()
                .filter(member -> !member.getNickname().equals(data)) //자기 자신의 nickname
                .map(member -> new SearchResultsDto(member.getNickname(),member.getId()))
                .collect(Collectors.toList());
        System.out.println("searchResults = " + searchResults);
        return searchResults;


    }
}
