package com.gujo_park.gujo_Parkdaejang.config

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
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

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .build()
    }

    // 👉 이건 한 번만 쓰고 주석처리 or 삭제
//    @Bean
//    fun generateAdminPasswordHash(): String {
//        val passwordEncoder = BCryptPasswordEncoder()
//        val rawPassword = "1234"
//        val encodedPassword = passwordEncoder.encode(rawPassword)
//        println("bcrypt 해시: $encodedPassword")
//        return encodedPassword
//    }

    // 보안 설정
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth // 스프링 시큐리티에서는 AuthController와 달리 이미 /login 경로 특별한 의미로 쓰이고 있음. -> /auth/login
                    .requestMatchers("/login", "/apply").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
            }
            .formLogin { it.disable() } // 기본 로그인 폼
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