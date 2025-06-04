package com.gujo_park.gujo_Parkdaejang.entity

import jakarta.persistence.*


@Entity
@Table(name = "photos")
class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    private val fileName: String? = null // 원본 파일명

    private val filePath: String? = null // 서버에 저장된 경로

    // 업로드한 관리자 (옵션)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private val uploadedBy: UserEntity? = null // 생성자, getter, setter 등 생략
}
