package spring.board.oauth;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig {
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                // user주소에 대해서 인증을 요구한다
                .antMatchers("/manager/**").hasRole("ROLE_ADMIN")
                // manager주소는 ROLE_ADMIN권한이 있어야 접근할 수 있다.
                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                // admin주소는 ROLE_ADMIN권한이 있어야 접근할 수 있다.
                .anyRequest().permitAll()    // 나머지주소는 인증없이 접근 가능하다
                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .userInfoEndpoint()
                .userService(principalOauth2UserService); //사용자 정보 처리

        return http.build();
    }

}
