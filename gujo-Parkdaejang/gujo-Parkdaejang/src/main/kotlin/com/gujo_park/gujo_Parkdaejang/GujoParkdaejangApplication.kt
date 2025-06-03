package com.gujo_park.gujo_Parkdaejang

import jakarta.persistence.EntityListeners
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(AuditingEntityListener::class)
@SpringBootApplication
class GujoParkdaejangApplication

fun main(args: Array<String>) {
	runApplication<GujoParkdaejangApplication>(*args)
}
