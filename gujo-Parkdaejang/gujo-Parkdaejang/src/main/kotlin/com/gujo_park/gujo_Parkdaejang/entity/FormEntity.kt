package com.gujo_park.gujo_Parkdaejang.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "forms")
class FormEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = null, // 문의 신청자 이름
    var number: String? = null, // 연락처

    @Column(length = 1000)
    var content: String? = null, // 문의내용

    @CreatedDate
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null
) {
    @PrePersist
    fun onCreate() {
        this.createdAt = LocalDateTime.now()
    }
}
