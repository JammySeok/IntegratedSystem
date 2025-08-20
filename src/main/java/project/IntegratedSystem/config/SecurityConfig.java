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
                // "/", "/login", "/signup", "/error/**" 등 정적 리소스나 시작 페이지는 모두에게 허용
                .requestMatchers("/", "/login", "/signup", "/css/**", "/js/**", "/images/**", "/error/**").permitAll()
                // 경로는 "ADMIN" 역할을 가진 사용자에게만 허용
                .requestMatchers("/admin/**", "/employeeList", "/employeeAdd", "/userList").hasRole("ADMIN")
                // 경로는 "USER" 또는 "ADMIN" 역할을 가진 사용자에게 허용
                .requestMatchers("/user/**", "/boardList").hasAnyRole("USER", "ADMIN")
                // 위에서 설정한 경로 외의 모든 요청은 인증된 사용자에게만 허용
                .anyRequest().authenticated()
        );

        // 폼 기반 로그인 설정
        http.formLogin(form -> form
                // 커스텀 로그인 페이지 경로 설정
                .loginPage("/login")
                // 로그인 form에서 action으로 지정할 URL. Spring Security가 이 요청을 가로채 인증을 처리합니다.
                .loginProcessingUrl("/login")
                // [중요] 로그인 폼에서 사용자 ID(username)에 해당하는 input의 name 속성 값
                // UserDetailsServiceImp의 loadUserByUsername(String userid) 메서드의 파라미터명과 일치시켜야 합니다.
                .usernameParameter("userid")
                // 로그인 성공 시 이동할 기본 URL. true로 설정 시 항상 이 URL로 이동합니다.
                .defaultSuccessUrl("/", true)
                // 로그인 실패 시 이동할 URL
                .failureUrl("/login?error=true")
                // 로그인 페이지는 모두에게 허용
                .permitAll()
        );

        // 로그아웃 설정
        http.logout(logout -> logout
                // 로그아웃을 처리할 URL
                .logoutUrl("/logout")
                // 로그아웃 성공 시 이동할 URL
                .logoutSuccessUrl("/")
                // 세션을 무효화
                .invalidateHttpSession(true)
                // 쿠키 삭제 (예: JSESSIONID)
                .deleteCookies("JSESSIONID")
        );

        // 접근 거부 예외 처리 설정
        http.exceptionHandling(exception -> exception
                // 접근 거부 시 이동할 페이지의 URL을 지정합니다.
                .accessDeniedPage("/error/denied")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화.
        // return new BCryptPasswordEncoder();

        // 임시 해결책 (DB도 평문, 비교도 평문으로 하겠다고 명시)
        // 경고: 이 설정은 오직 개발 및 학습용
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                // 인코딩을 안하고 받은 값 그대로 반환
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                // 저장된 비밀번호(encodedPassword)와 사용자가 입력한 비밀번호(rawPassword)를
                // 단순 문자열 비교
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}
