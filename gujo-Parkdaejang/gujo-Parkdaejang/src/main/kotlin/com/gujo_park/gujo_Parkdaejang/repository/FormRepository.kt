package com.gujo_park.gujo_Parkdaejang.repository

import com.gujo_park.gujo_Parkdaejang.dto.FormDto
import org.springframework.data.jpa.repository.JpaRepository

interface FormRepository: JpaRepository<FormDto, Long> {
}