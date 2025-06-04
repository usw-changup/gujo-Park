package com.gujo_park.gujo_Parkdaejang.entity

import jakarta.persistence.*


@Entity
@Table(name = "users") // "user"는 예약어라 보통 "users"로 지정
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var username: String? = null // 로그인 ID

    @Column(nullable = false)
    var password: String? = null // 암호화된 비밀번호

    @Column(nullable = false)
    var role: String? = null // "ADMIN" 또는 "USER"
    // 생성자, getter, setter 등 생략 가능 (Lombok으로 @Data 붙여도 됨)
}
