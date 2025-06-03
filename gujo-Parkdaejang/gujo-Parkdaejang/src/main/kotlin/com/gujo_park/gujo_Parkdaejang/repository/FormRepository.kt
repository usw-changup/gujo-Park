package com.gujo_park.gujo_Parkdaejang.repository

import com.gujo_park.gujo_Parkdaejang.dto.FormDto
import com.gujo_park.gujo_Parkdaejang.entity.FormEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FormRepository: JpaRepository<FormEntity, Long> {
}