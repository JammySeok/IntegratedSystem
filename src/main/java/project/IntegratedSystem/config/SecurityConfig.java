package project.IntegratedSystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // HTTP 요청에 대한 접근 권한 설정
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/signup", "/css/**", "/js/**", "/images/**", "/error/**").permitAll()
                .requestMatchers("/admin/**", "/employeeList", "/employeeAdd", "/userList").hasRole("ADMIN")
                .requestMatchers("/user/**", "/boardList").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        );

        // 폼 기반 로그인 설정
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("userid")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
        );

        // 로그아웃 설정
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        );

        // 접근 거부 예외 처리 설정
        http.exceptionHandling(exception -> exception
                .accessDeniedPage("/error/denied")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화.
         return new BCryptPasswordEncoder();

        // DB도 평문, 비교도 평문으로 하겠다고 명시 - 이 설정은 오직 개발 및 학습용
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                // 인코딩을 안하고 받은 값 그대로 반환
//                return rawPassword.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                // 저장된 비밀번호(encodedPassword)와 사용자가 입력한 비밀번호(rawPassword)를
//                // 단순 문자열 비교
//                return rawPassword.toString().equals(encodedPassword);
//            }
//        };
    }
}
