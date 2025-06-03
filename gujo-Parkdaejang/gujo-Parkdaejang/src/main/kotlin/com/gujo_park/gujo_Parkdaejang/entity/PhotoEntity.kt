package com.gujo_park.gujo_Parkdaejang.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "photos")
class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var fileName: String? = null // 원본 파일명

    @Column(nullable = false)
    var filePath: String? = null // 서버에 저장된 경로

    @Column(nullable = false)
    var createAt: LocalDateTime = LocalDateTime.now()


    // 업로드한 관리자 (옵션)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private val uploadedBy: UserEntity? = null // 생성자, getter, setter 등 생략
}
