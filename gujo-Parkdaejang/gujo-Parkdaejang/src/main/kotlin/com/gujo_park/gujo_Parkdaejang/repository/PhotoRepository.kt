package com.gujo_park.gujo_Parkdaejang.repository

import com.gujo_park.gujo_Parkdaejang.entity.PhotoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PhotoRepository : JpaRepository<PhotoEntity, Long>