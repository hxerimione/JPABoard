package spring.board.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable().authorizeRequests()
                .antMatchers("/", "/auth/**","/posts","/login").permitAll()
                .antMatchers("/posts/new").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/users").access("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
          .and()
                .logout()					// logout??? ??????
                .logoutUrl("/logout")			// ??????????????? ????????? URL ??????
                .logoutSuccessUrl("/")		// ???????????? ?????? ??? "/"?????? ??????
          .and()
            .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/")

                .userInfoEndpoint()
                .userService(principalOauth2UserService); //????????? ?????? ??????



        return http.build();
    }

}
