package com.gujo_park.gujo_Parkdaejang.config

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    // 비밀번호 암호화를 위한 Bean
    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // 보안 설정
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/login", "/apply").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
            }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .logout { logout ->
                // ① 로그아웃 URL을 /logout 으로 지정
                logout
                    .logoutUrl("/logout")
                    // ② 로그아웃 성공 후 리다이렉트할 페이지 (여기서는 / 로 보냄)
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)    // 세션 무효화
                    .deleteCookies("JSESSIONID")    // 쿠키 삭제
                    .permitAll()
            }
            .sessionManagement { session ->
                session
                    .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED)
            }

        return http.build()
    }

}