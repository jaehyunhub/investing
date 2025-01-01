package shareAlbum.shareAlbum.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shareAlbum.shareAlbum.global.jwt.CustomUserDetailsService;
import shareAlbum.shareAlbum.global.jwt.JwtAuthenticationFilter;
import shareAlbum.shareAlbum.global.jwt.JwtTokenProvider;


@EnableWebSecurity    // 기본적인 Web 보안을 활성화한다
@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors().and()
                //token을 사용하는 방식이기 때문에 csrf,basic auth를 disable
                .httpBasic().disable()
                .csrf().disable()

                //JWT를 사용하기 때문에 세션을 사용하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers("/","/login/oauth2/code/kakao","/callback").permitAll()
                .antMatchers("/signup","/login","/logout/**").permitAll()
                .antMatchers("/search/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/createGroup","/inviteGroup").permitAll()
                .antMatchers("/acceptGroupInvitation","/rejectGroupInvitation").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/uploadAlbum").permitAll()
                .antMatchers("/searchResults/**").permitAll()
                .antMatchers("/redis/**").permitAll()
                // 이 밖에 모든 요청에 대해서 인증을 필요로 한다는 설정
                .anyRequest().authenticated()
                .and()

                .logout()
                .logoutUrl("logout")
                .logoutSuccessUrl("/")
                .and()

                // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsConfig.corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt Encoder 사용
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
    }



}
