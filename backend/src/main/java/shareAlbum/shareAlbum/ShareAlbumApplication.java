package shareAlbum.shareAlbum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
@PropertySource("classpath:application.yml")
@EnableFeignClients(basePackages = "shareAlbum.shareAlbum.domain.member.query.kakaoLogin")
public class ShareAlbumApplication {

	public static void main(String[] args) {SpringApplication.run(ShareAlbumApplication.class, args);}

	//세션이나 header에서 값을 가져와야한다.
//	@Bean
//	public AuditorAware<String> auditorProvider(){
//		//스프링 시큐어티 jwt 로그인 검증 후 로직 변경
//		String loginId = "abc1234@gamil.com";
//		return () -> Optional.of(loginId);
//	}
}
