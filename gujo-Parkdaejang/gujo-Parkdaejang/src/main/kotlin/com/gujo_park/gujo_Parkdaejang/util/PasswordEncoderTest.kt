package com.gujo_park.gujo_Parkdaejang.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun main() {
    val encoder = BCryptPasswordEncoder()
    val encoded = encoder.encode("1234") // 원하는 비밀번호
    println("암호화된 비밀번호: $encoded")
}
