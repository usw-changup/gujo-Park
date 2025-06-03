package com.gujo_park.gujo_Parkdaejang.controller

import com.gujo_park.gujo_Parkdaejang.repository.UserRepository
import com.gujo_park.gujo_Parkdaejang.dto.LoginRequestDto
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.CrossOrigin

@RestController
class AuthController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @CrossOrigin(origins = ["http://127.0.0.1:5500"], allowCredentials = "true")
    @PostMapping("/auth/login")
    fun login(@RequestBody loginRequest: LoginRequestDto,
              request: HttpServletRequest
    ): ResponseEntity<String> {
        println("로그인 시도: ${loginRequest.username}")
        val user = userRepository.findByUsername(loginRequest.username)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 없음")

        if (!passwordEncoder.matches(loginRequest.password, user.password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호 불일치")
        }
        val authorities = listOf(SimpleGrantedAuthority(user.role)) // 예: "ROLE_ADMIN"
        val auth = UsernamePasswordAuthenticationToken(user.username, null, authorities)
        SecurityContextHolder.getContext().authentication = auth

        println("로그인 후 SecurityContext 권한: ${auth.authorities}")

        // 🚀 세션 직접 생성
        val session = request.getSession(true)
        session.setAttribute("username", user.username)
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext())

        return ResponseEntity.ok("로그인 성공")
    }
}