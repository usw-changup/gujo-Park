package com.gujo_park.gujo_Parkdaejang.dto

import com.gujo_park.gujo_Parkdaejang.entity.FormEntity
import java.time.LocalDateTime

data class  FormDto (
    val name: String,
    val number: String,
    val content: String,
    val createdAt: LocalDateTime? = null
) {
    fun toEntity(): FormEntity {
        return FormEntity(
            name = this.name,
            number = this.number,
            content = this.content,
            createdAt = this.createdAt
        )
    }
    companion object {
        fun fromEntity(entity: FormEntity): FormDto {
            return FormDto(
                name = entity.name ?: "",
                number = entity.number ?: "",
                content = entity.content ?: "",
                createdAt = entity.createdAt
            )
        }
    }

}