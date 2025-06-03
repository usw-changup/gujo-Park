package com.gujo_park.gujo_Parkdaejang.config

import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.session.web.http.DefaultCookieSerializer


@Configuration
class CookieConfig {

    @Bean
    fun cookieSameSiteSupplier(): CookieSameSiteSupplier {
        // SameSite=None 으로 설정 (HTTPS 환경 필요!)
        return CookieSameSiteSupplier.ofNone()
    }

    @Bean
    fun defaultCookieSerializer(): DefaultCookieSerializer {
        val serializer = DefaultCookieSerializer()
        serializer.setSameSite("None")
        serializer.setUseSecureCookie(false) // 개발환경에서는 Secure false
        return serializer
    }

}
// 배포할 땐 HTTPS 환경 꼭 맞춰줘야 함.