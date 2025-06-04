package com.gujo_park.gujo_Parkdaejang.repository

import com.gujo_park.gujo_Parkdaejang.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}